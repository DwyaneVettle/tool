<?php

namespace PromotionsBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * CouponGiveLog 优惠券赠送表
 *
 * @ORM\Table(name="coupon_give_log", options={"comment":"优惠券赠送表"})
 * @ORM\Entity(repositoryClass="PromotionsBundle\Repositories\CouponGiveLogRepository")
 */
class CouponGiveLog
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="give_log_id", type="bigint", options={"comment":"优惠券赠送失败记录id"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $give_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"商户id"})
     */
    private $company_id;
    /**
     * @var integer
     *
     * @ORM\Column(name="distributor_id", type="bigint", options={"unsigned":true, "default":0, "comment":"分销商id"})
     */
    private $distributor_id;

    /**
     * @var string
     *
     * @ORM\Column(name="sender", type="string", options={"comment":"发送者"})
     */
    private $sender;

    /**
     * @var integer
     *
     * @ORM\Column(name="number", type="bigint", options={"comment":"赠送数量"})
     */
    private $number;

    /**
     * @var integer
     *
     * @ORM\Column(name="error", type="bigint", options={"comment":"失败数量"})
     */
    private $error;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer")
     */
    protected $created;

    /**
     * Get giveId
     *
     * @return integer
     */
    public function getGiveId()
    {
        return $this->give_id;
    }

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return CouponGiveLog
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
     * Set sender
     *
     * @param string $sender
     *
     * @return CouponGiveLog
     */
    public function setSender($sender)
    {
        $this->sender = $sender;

        return $this;
    }

    /**
     * Get sender
     *
     * @return string
     */
    public function getSender()
    {
        return $this->sender;
    }

    /**
     * Set number
     *
     * @param integer $number
     *
     * @return CouponGiveLog
     */
    public function setNumber($number)
    {
        $this->number = $number;

        return $this;
    }

    /**
     * Get number
     *
     * @return integer
     */
    public function getNumber()
    {
        return $this->number;
    }

    /**
     * Set error
     *
     * @param integer $error
     *
     * @return CouponGiveLog
     */
    public function setError($error)
    {
        $this->error = $error;

        return $this;
    }

    /**
     * Get error
     *
     * @return integer
     */
    public function getError()
    {
        return $this->error;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return CouponGiveLog
     */
    public function setCreated($created)
    {
        $this->created = $created;

        return $this;
    }

    /**
     * Get created
     *
     * @return integer
     */
    public function getCreated()
    {
        return $this->created;
    }

    /**
     * Set distributorId.
     *
     * @param int $distributorId
     *
     * @return CouponGiveLog
     */
    public function setDistributorId($distributorId)
    {
        $this->distributor_id = $distributorId;

        return $this;
    }

    /**
     * Get distributorId.
     *
     * @return int
     */
    public function getDistributorId()
    {
        return $this->distributor_id;
    }
}
