<?php

namespace WorkWechatBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * WorkWechatVerifyDomainFile 企业微信可信域名校验文件
 *
 * @ORM\Table(name="work_wechat_verify_domain_file", options={"comment":"企业微信可信域名校验文件"},
 *    uniqueConstraints={
 *         @ORM\UniqueConstraint(name="ix_name", columns={"name"}),
 *     },
 * )
 * @ORM\Entity(repositoryClass="WorkWechatBundle\Repositories\WorkWechatVerifyDomainFileRepository")
 */
class WorkWechatVerifyDomainFile
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint", options={"comment":"id"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="operator_id", type="bigint", options={"comment":"授权操作者id"})
     */
    private $operator_id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string",  options={"comment":"验证文件名", "default": ""})
     */
    private $name;

    /**
     * @var string
     *
     * @ORM\Column(name="contents", type="text",  options={"comment":"验证文件内容", "default": ""})
     */
    private $contents;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer")
     */
    protected $created;

    /**
     * Get id.
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return WorkWechatVerifyDomainFile
     */
    public function setCompanyId($companyId)
    {
        $this->company_id = $companyId;

        return $this;
    }

    /**
     * Get companyId.
     *
     * @return int
     */
    public function getCompanyId()
    {
        return $this->company_id;
    }

    /**
     * Set operatorId.
     *
     * @param int $operatorId
     *
     * @return WorkWechatVerifyDomainFile
     */
    public function setOperatorId($operatorId)
    {
        $this->operator_id = $operatorId;

        return $this;
    }

    /**
     * Get operatorId.
     *
     * @return int
     */
    public function getOperatorId()
    {
        return $this->operator_id;
    }

    /**
     * Set name.
     *
     * @param string $name
     *
     * @return WorkWechatVerifyDomainFile
     */
    public function setName($name)
    {
        $this->name = $name;

        return $this;
    }

    /**
     * Get name.
     *
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set contents.
     *
     * @param string $contents
     *
     * @return WorkWechatVerifyDomainFile
     */
    public function setContents($contents)
    {
        $this->contents = $contents;

        return $this;
    }

    /**
     * Get contents.
     *
     * @return string
     */
    public function getContents()
    {
        return $this->contents;
    }

    /**
     * Set created.
     *
     * @param int $created
     *
     * @return WorkWechatVerifyDomainFile
     */
    public function setCreated($created)
    {
        $this->created = $created;

        return $this;
    }

    /**
     * Get created.
     *
     * @return int
     */
    public function getCreated()
    {
        return $this->created;
    }
}
