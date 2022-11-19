<?php

namespace CrossBorderBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * Set  跨境设置
 *
 * @ORM\Table(name="crossborder_set", options={"comment":"跨境-设置"}, indexes={
 *    @ORM\Index(name="ix_id", columns={"id"}),
 *    @ORM\Index(name="ix_company_id", columns={"company_id"}),
 * })
 * @ORM\Entity(repositoryClass="CrossBorderBundle\Repositories\CrossBorderSetRepository")
 */
class CrossBorderSet
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="bigint", options={"comment":"设置id"})
     * @ORM\Id
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
     * @var string
     *
     * @ORM\Column(name="tax_rate", type="string", length=10, nullable=false, options={"comment":"税率"})
     */
    private $tax_rate;

    /**
     * @var string
     *
     * @ORM\Column(name="quota_tip", type="text",length=1000, nullable=true, options={"comment":"额度提醒"})
     */
    private $quota_tip;

    /**
     * @var string
     *
     * @ORM\Column(name="logistics", type="string", length=10, nullable=false, options={"comment":"跨境物流"})
     */
    private $logistics;

    /**
     * @var string
     *
     * @ORM\Column(name="crossborder_show", type="integer",length=4, nullable=false, options={"comment":"跨境显示,0不显示，1显示", "default": 0})
     */
    private $crossborder_show = 0;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer")
     */
    protected $created;

    /**
     * @var \DateTime $updated
     *
     * @Gedmo\Timestampable(on="update")
     * @ORM\Column(type="integer", nullable=true)
     */
    protected $updated;



    /**
     * Get id.
     *
     * @return int
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
     * @return CrossBorderSet
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
     * Set taxRate.
     *
     * @param string $taxRate
     *
     * @return CrossBorderSet
     */
    public function setTaxRate($taxRate)
    {
        $this->tax_rate = $taxRate;

        return $this;
    }

    /**
     * Get taxRate.
     *
     * @return string
     */
    public function getTaxRate()
    {
        return $this->tax_rate;
    }

    /**
     * Set quotaTip.
     *
     * @param string|null $quotaTip
     *
     * @return CrossBorderSet
     */
    public function setQuotaTip($quotaTip = null)
    {
        $this->quota_tip = $quotaTip;

        return $this;
    }

    /**
     * Get quotaTip.
     *
     * @return string|null
     */
    public function getQuotaTip()
    {
        return $this->quota_tip;
    }

    /**
     * Set created.
     *
     * @param int $created
     *
     * @return CrossBorderSet
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

    /**
     * Set updated.
     *
     * @param int|null $updated
     *
     * @return CrossBorderSet
     */
    public function setUpdated($updated = null)
    {
        $this->updated = $updated;

        return $this;
    }

    /**
     * Get updated.
     *
     * @return int|null
     */
    public function getUpdated()
    {
        return $this->updated;
    }

    /**
     * Set crossborderShow.
     *
     * @param int $crossborderShow
     *
     * @return CrossBorderSet
     */
    public function setCrossborderShow($crossborderShow)
    {
        $this->crossborder_show = $crossborderShow;

        return $this;
    }

    /**
     * Get crossborderShow.
     *
     * @return int
     */
    public function getCrossborderShow()
    {
        return $this->crossborder_show;
    }

    /**
     * Set logistics.
     *
     * @param string $logistics
     *
     * @return CrossBorderSet
     */
    public function setLogistics($logistics)
    {
        $this->logistics = $logistics;

        return $this;
    }

    /**
     * Get logistics.
     *
     * @return string
     */
    public function getLogistics()
    {
        return $this->logistics;
    }
}
