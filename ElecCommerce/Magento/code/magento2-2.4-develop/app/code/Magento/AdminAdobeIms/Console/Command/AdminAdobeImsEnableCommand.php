<?php
/**
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */
declare(strict_types=1);

namespace Magento\AdminAdobeIms\Console\Command;

use Magento\AdminAdobeIms\Service\ImsCommandOptionService;
use Magento\AdminAdobeIms\Service\ImsConfig;
use Magento\AdminAdobeIms\Service\UpdateTokensService;
use Magento\AdobeImsApi\Api\AuthorizationInterface;
use Magento\Authorization\Model\Acl\Role\Group;
use Magento\Authorization\Model\ResourceModel\Role\CollectionFactory;
use Magento\Authorization\Model\Role;
use Magento\Authorization\Model\UserContextInterface;
use Magento\Framework\App\Cache\Type\Config;
use Magento\Framework\App\Cache\TypeListInterface;
use Magento\Framework\App\ObjectManager;
use Magento\Framework\Console\Cli;
use Magento\Framework\Exception\InvalidArgumentException;
use Magento\Framework\Exception\LocalizedException;
use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Input\InputInterface;
use Symfony\Component\Console\Input\InputOption;
use Symfony\Component\Console\Output\OutputInterface;

/**
 * Command to set Admin Adobe IMS Module mode
 * @SuppressWarnings(PHPMD.CouplingBetweenObjects)
 */
class AdminAdobeImsEnableCommand extends Command
{
    /**
     * Name of "organization-id" input option
     */
    private const ORGANIZATION_ID_ARGUMENT = 'organization-id';

    /**
     * Name of "client-id" input option
     */
    private const CLIENT_ID_ARGUMENT = 'client-id';

    /**
     * Name of "client-secret" input option
     */
    private const CLIENT_SECRET_ARGUMENT = 'client-secret';

    /**
     * Name of "two-factor-auth" input option
     */
    private const TWO_FACTOR_AUTH_ARGUMENT = '2fa';

    /**
     * @var ImsConfig
     */
    private ImsConfig $adminImsConfig;

    /**
     * @var ImsCommandOptionService
     */
    private ImsCommandOptionService $imsCommandOptionService;

    /**
     * @var TypeListInterface
     */
    private TypeListInterface $cacheTypeList;

    /**
     * @var UpdateTokensService
     */
    private UpdateTokensService $updateTokensService;

    /**
     * @var Role
     */
    private Role $role;

    /**
     * @var CollectionFactory
     */
    private CollectionFactory $roleCollection;

    /**
     * @var AuthorizationInterface
     */
    private AuthorizationInterface $authorization;

    /**
     * @param ImsConfig $adminImsConfig
     * @param ImsCommandOptionService $imsCommandOptionService
     * @param TypeListInterface $cacheTypeList
     * @param UpdateTokensService $updateTokensService
     * @param AuthorizationInterface $authorization
     * @param Role|null $role
     * @param CollectionFactory|null $roleCollection
     */
    public function __construct(
        ImsConfig $adminImsConfig,
        ImsCommandOptionService $imsCommandOptionService,
        TypeListInterface $cacheTypeList,
        UpdateTokensService $updateTokensService,
        AuthorizationInterface $authorization,
        Role $role = null,
        CollectionFactory $roleCollection = null
    ) {
        parent::__construct();
        $this->adminImsConfig = $adminImsConfig;
        $this->imsCommandOptionService = $imsCommandOptionService;
        $this->cacheTypeList = $cacheTypeList;
        $this->updateTokensService = $updateTokensService;
        $this->authorization = $authorization;
        $this->role = $role ?: ObjectManager::getInstance()->get(Role::class);
        $this->roleCollection = $roleCollection ?: ObjectManager::getInstance()->get(CollectionFactory::class);

        $this->setName('admin:adobe-ims:enable')
            ->setDescription('Enable Adobe IMS Module.')
            ->setDefinition([
                new InputOption(
                    self::ORGANIZATION_ID_ARGUMENT,
                    'o',
                    InputOption::VALUE_OPTIONAL,
                    'Set Organization ID for Adobe IMS configuration. Required when enabling the module'
                ),
                new InputOption(
                    self::CLIENT_ID_ARGUMENT,
                    'c',
                    InputOption::VALUE_OPTIONAL,
                    'Set the client ID for Adobe IMS configuration. Required when enabling the module'
                ),
                new InputOption(
                    self::CLIENT_SECRET_ARGUMENT,
                    's',
                    InputOption::VALUE_OPTIONAL,
                    'Set the client Secret for Adobe IMS configuration. Required when enabling the module'
                ),
                new InputOption(
                    self::TWO_FACTOR_AUTH_ARGUMENT,
                    't',
                    InputOption::VALUE_OPTIONAL,
                    'Check if 2FA is enabled for Organization in Adobe Admin Console. ' .
                    'Required when enabling the module'
                )
            ]);
    }

    /**
     * @inheritdoc
     */
    protected function execute(InputInterface $input, OutputInterface $output): ?int
    {
        try {
            $helper = $this->getHelper('question');

            $organizationId = $this->imsCommandOptionService->getOrganizationId(
                $input,
                $output,
                $helper,
                self::ORGANIZATION_ID_ARGUMENT
            );

            $clientId = $this->imsCommandOptionService->getClientId(
                $input,
                $output,
                $helper,
                self::CLIENT_ID_ARGUMENT
            );

            $clientSecret = $this->imsCommandOptionService->getClientSecret(
                $input,
                $output,
                $helper,
                self::CLIENT_SECRET_ARGUMENT
            );

            $isTwoFactorAuthEnabled = $this->imsCommandOptionService->isTwoFactorAuthEnabled(
                $input,
                $output,
                $helper,
                self::TWO_FACTOR_AUTH_ARGUMENT
            );

            if ($clientId && $clientSecret && $organizationId && $isTwoFactorAuthEnabled) {
                $enabled = $this->enableModule($clientId, $clientSecret, $organizationId, $isTwoFactorAuthEnabled);
                if ($enabled) {
                    $this->saveImsAuthorizationRole();
                    $output->writeln(__('Admin Adobe IMS integration is enabled'));
                    return Cli::RETURN_SUCCESS;
                }
            }

            throw new LocalizedException(
                __('The Client ID, Client Secret, Organization ID and 2FA are required ' .
                    'when enabling the Admin Adobe IMS Module')
            );
        } catch (\Exception $e) {
            $output->writeln('<error>' . $e->getMessage() . '</error>');
            if ($output->getVerbosity() >= OutputInterface::VERBOSITY_VERBOSE) {
                $output->writeln($e->getTraceAsString());
            }
            return Cli::RETURN_FAILURE;
        }
    }

    /**
     * Save new Adobe IMS role
     *
     * @return bool
     * @throws \Exception
     */
    private function saveImsAuthorizationRole(): bool
    {
        $roleCollection = $this->roleCollection->create()->addFieldToFilter('role_name', 'Adobe Ims');
        if (!$roleCollection->getSize()) {
            $this->role->setRoleName('Adobe Ims')
                ->setUserType((string)UserContextInterface::USER_TYPE_ADMIN)
                ->setUserId(0)
                ->setRoleType(Group::ROLE_TYPE)
                ->setParentId(0)
                ->save();
        }

        return true;
    }

    /**
     * Enable Admin Adobe IMS Module when testConnection was successfully
     *
     * @param string $clientId
     * @param string $clientSecret
     * @param string $organizationId
     * @param bool $isTwoFactorAuthEnabled
     * @return bool
     * @throws LocalizedException
     * @throws InvalidArgumentException
     */
    private function enableModule(
        string $clientId,
        string $clientSecret,
        string $organizationId,
        bool $isTwoFactorAuthEnabled
    ): bool {
        $testAuth = $this->authorization->testAuth($clientId);
        if ($testAuth) {
            $this->adminImsConfig->enableModule($clientId, $clientSecret, $organizationId, $isTwoFactorAuthEnabled);
            $this->cacheTypeList->cleanType(Config::TYPE_IDENTIFIER);
            $this->updateTokensService->execute();

            return true;
        }

        return false;
    }
}
