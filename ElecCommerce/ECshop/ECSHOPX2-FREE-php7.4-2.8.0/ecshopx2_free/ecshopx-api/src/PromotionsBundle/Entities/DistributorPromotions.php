<?php

namespace PromotionsBundle\Entities;

use Doctrine\ORM\Mapping as ORM;

/**
 * DistributorPromotions 分销商营销
 *
 * @ORM\Table(name="promotions_distributor", options={"comment":"分销商营销"})
 * @ORM\Entity(repositoryClass="PromotionsBundle\Repositories\DistributorPromotionsRepository")
 */
class DistributorPromotions
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint", options={"comment":"自增ID"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"商户id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="promotion_type", type="string", options={"comment":"营销类型", "default":"register"})
     */
    private $promotion_type = 'register';

    /**
     * @var integer
     *
     * @ORM\Column(name="promotion_id", type="bigint", options={"comment":"营销id"})
     */
    private $promotion_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="distributor_id", type="bigint", options={"comment":"分销商id"})
     */
    private $distributor_id;

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return DistributorPromotions
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
     * Set promotionType
     *
     * @param string $promotionType
     *
     * @return DistributorPromotions
     */
    public function setPromotionType($promotionType)
    {
        $this->promotion_type = $promotionType;

        return $this;
    }

    /**
     * Get promotionType
     *
     * @return string
     */
    public function getPromotionType()
    {
        return $this->promotion_type;
    }

    /**
     * Set promotionId
     *
     * @param integer $promotionId
     *
     * @return DistributorPromotions
     */
    public function setPromotionId($promotionId)
    {
        $this->promotion_id = $promotionId;

        return $this;
    }

    /**
     * Get promotionId
     *
     * @return integer
     */
    public function getPromotionId()
    {
        return $this->promotion_id;
    }

    /**
     * Set distributorId
     *
     * @param integer $distributorId
     *
     * @return DistributorPromotions
     */
    public function setDistributorId($distributorId)
    {
        $this->distributor_id = $distributorId;

        return $this;
    }

    /**
     * Get distributorId
     *
     * @return integer
     */
    public function getDistributorId()
    {
        return $this->distributor_id;
    }

    /**
     * Get id
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }
}
