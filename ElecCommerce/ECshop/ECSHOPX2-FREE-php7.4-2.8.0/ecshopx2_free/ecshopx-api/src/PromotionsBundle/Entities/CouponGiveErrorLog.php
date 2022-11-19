<?php

namespace PromotionsBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * CouponGiveErrorLog 优惠券赠送失败记录表
 *
 * @ORM\Table(name="coupon_give_error_log", options={"comment":"优惠券赠送失败记录表"},
 *     indexes={
 *        @ORM\Index(name="idx_companyid", columns={"company_id"}),
 *        @ORM\Index(name="idx_giveid", columns={"give_id"}),
 *     }
 * )
 * @ORM\Entity(repositoryClass="PromotionsBundle\Repositories\CouponGiveErrorLogRepository")
 */
class CouponGiveErrorLog
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="give_log_id", type="bigint", options={"comment":"优惠券赠送失败记录id"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $give_log_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="give_id", type="bigint", options={"comment":"优惠券赠送失败记录id"})
     */
    private $give_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="uid", type="bigint", options={"comment":"赠送用户id"})
     */
    private $uid;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"商户id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="card_id", type="bigint", options={"comment":"赠送优惠券id"})
     */
    private $card_id;

    /**
     * @var string
     *
     * @ORM\Column(name="note", type="string", options={"comment":"失败原因记录"})
     */
    private $note;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer")
     */
    protected $created;


    /**
     * Get giveLogId
     *
     * @return integer
     */
    public function getGiveLogId()
    {
        return $this->give_log_id;
    }

    /**
     * Set giveId
     *
     * @param integer $giveId
     *
     * @return CouponGiveErrorLog
     */
    public function setGiveId($giveId)
    {
        $this->give_id = $giveId;

        return $this;
    }

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
     * Set uid
     *
     * @param integer $uid
     *
     * @return CouponGiveErrorLog
     */
    public function setUid($uid)
    {
        $this->uid = $uid;

        return $this;
    }

    /**
     * Get uid
     *
     * @return integer
     */
    public function getUid()
    {
        return $this->uid;
    }

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return CouponGiveErrorLog
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
     * Set cardId
     *
     * @param integer $cardId
     *
     * @return CouponGiveErrorLog
     */
    public function setCardId($cardId)
    {
        $this->card_id = $cardId;

        return $this;
    }

    /**
     * Get cardId
     *
     * @return integer
     */
    public function getCardId()
    {
        return $this->card_id;
    }

    /**
     * Set note
     *
     * @param string $note
     *
     * @return CouponGiveErrorLog
     */
    public function setNote($note)
    {
        $this->note = $note;

        return $this;
    }

    /**
     * Get note
     *
     * @return string
     */
    public function getNote()
    {
        return $this->note;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return CouponGiveErrorLog
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
}
