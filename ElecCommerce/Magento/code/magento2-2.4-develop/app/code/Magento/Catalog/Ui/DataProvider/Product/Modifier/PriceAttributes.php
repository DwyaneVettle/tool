<?php
/**
 * Copyright © Magento, Inc. All rights reserved.
 * See COPYING.txt for license details.
 */
declare(strict_types=1);

namespace Magento\Catalog\Ui\DataProvider\Product\Modifier;

use Magento\Framework\Currency;
use Magento\Framework\Exception\NoSuchEntityException;
use Magento\Framework\Locale\CurrencyInterface;
use Magento\Framework\View\Element\UiComponent\ContextInterface;
use Magento\Store\Api\Data\StoreInterface;
use Magento\Store\Model\Store;
use Magento\Store\Model\StoreManagerInterface;
use Magento\Ui\DataProvider\Modifier\ModifierInterface;

/**
 * Modify product listing price attributes
 */
class PriceAttributes implements ModifierInterface
{
    /**
     * @var array
     */
    private $priceAttributeList;

    /**
     * @var StoreManagerInterface
     */
    private $storeManager;

    /**
     * @var CurrencyInterface
     */
    private $localeCurrency;

    /**
     * @var ContextInterface
     */
    private ContextInterface $context;

    /**
     * PriceAttributes constructor.
     *
     * @param ContextInterface $context
     * @param StoreManagerInterface $storeManager
     * @param CurrencyInterface $localeCurrency
     * @param array $priceAttributeList
     */
    public function __construct(
        ContextInterface $context,
        StoreManagerInterface $storeManager,
        CurrencyInterface $localeCurrency,
        array $priceAttributeList = []
    ) {
        $this->storeManager = $storeManager;
        $this->localeCurrency = $localeCurrency;
        $this->priceAttributeList = $priceAttributeList;
        $this->context = $context;
    }

    /**
     * @inheritdoc
     * @throws NoSuchEntityException
     * @throws \Zend_Currency_Exception
     */
    public function modifyData(array $data): array
    {
        if (empty($data) || empty($this->priceAttributeList)) {
            return $data;
        }

        foreach ($data['items'] as &$item) {
            foreach ($this->priceAttributeList as $priceAttribute) {
                if (isset($item[$priceAttribute])) {
                    $item[$priceAttribute] = $this->getCurrency()->toCurrency(sprintf("%f", $item[$priceAttribute]));
                }
            }
        }

        return $data;
    }

    /**
     * @inheritdoc
     */
    public function modifyMeta(array $meta): array
    {
        return $meta;
    }

    /**
     * Retrieve store
     *
     * @return StoreInterface
     * @throws NoSuchEntityException
     */
    private function getStore(): StoreInterface
    {
        return $this->storeManager->getStore(
            $this->context->getFilterParam('store_id', Store::DEFAULT_STORE_ID)
        );
    }

    /**
     * Retrieve currency
     *
     * @return Currency
     * @throws NoSuchEntityException
     */
    private function getCurrency(): Currency
    {
        $baseCurrencyCode = $this->getStore()->getBaseCurrencyCode();

        return $this->localeCurrency->getCurrency($baseCurrencyCode);
    }
}
