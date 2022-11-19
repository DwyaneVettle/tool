<?php

namespace OrdersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;

/**
 * CompanyRelLogistics 商城物流启用表
 *
 * @ORM\Table(name="company_rel_logistics", options={"comment":"商城物流启用表"})
 * @ORM\Entity(repositoryClass="OrdersBundle\Repositories\CompanyRelLogisticsRepository")
 */
class CompanyRelLogistics
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="corp_id", type="smallint", options={"comment":"物流公司ID"})
     *
     *
     */

    private $corp_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="smallint", options={"comment":"公司ID"})
     *
     *
     */

    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="corp_code", type="string", options={"comment":"物流公司代码"})
     *
     */

    private $corp_code;

    /**
     * @var string
     *
     * @ORM\Column(name="kuaidi_code", type="string", options={"comment":"快递100代码"})
     *
     */
    private $kuaidi_code;

    /**
     * @var string
     *
     * @ORM\Column(name="corp_name", type="string", options={"comment":"物流公司简称"})
     */
    private $corp_name;


    /**
     * Get id
     *
     * @return \int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set corpId
     *
     * @param integer $corpId
     *
     * @return ShopRelLogistics
     */
    public function setCorpId($corpId)
    {
        $this->corp_id = $corpId;

        return $this;
    }

    /**
     * Get corpId
     *
     * @return integer
     */
    public function getCorpId()
    {
        return $this->corp_id;
    }


    /**
     * Set corpCode
     *
     * @param string $corpCode
     *
     * @return ShopRelLogistics
     */
    public function setCorpCode($corpCode)
    {
        $this->corp_code = $corpCode;

        return $this;
    }

    /**
     * Get corpCode
     *
     * @return string
     */
    public function getCorpCode()
    {
        return $this->corp_code;
    }

    /**
     * Set corpName
     *
     * @param string $corpName
     *
     * @return ShopRelLogistics
     */
    public function setCorpName($corpName)
    {
        $this->corp_name = $corpName;

        return $this;
    }

    /**
     * Get corpName
     *
     * @return string
     */
    public function getCorpName()
    {
        return $this->corp_name;
    }

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return CompanyRelLogistics
     */
    public function setCompanyId($companyId)
    {
        $this->company_id = $companyId;

        return $this;
    }

    /**
     * Get companyId
     *
     * @return integer
     */
    public function getCompanyId()
    {
        return $this->company_id;
    }

    /**
     * Set kuaidiCode
     *
     * @param string $kuaidiCode
     *
     * @return CompanyRelLogistics
     */
    public function setKuaidiCode($kuaidiCode)
    {
        $this->kuaidi_code = $kuaidiCode;

        return $this;
    }

    /**
     * Get kuaidiCode
     *
     * @return string
     */
    public function getKuaidiCode()
    {
        return $this->kuaidi_code;
    }
}
