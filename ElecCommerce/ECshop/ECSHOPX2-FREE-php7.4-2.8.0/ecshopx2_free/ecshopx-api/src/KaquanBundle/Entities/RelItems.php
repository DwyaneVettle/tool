<?php

namespace KaquanBundle\Entities;

use Doctrine\ORM\Mapping as ORM;

/**
 * RelItems 卡券关联商品表
 *
 * @ORM\Table(name="kaquan_rel_items", options={"comment":"卡券关联商品表"}, indexes={
 *    @ORM\Index(name="idx_card_id", columns={"card_id"}),
 *    @ORM\Index(name="idx_company_id", columns={"company_id"}),
 * })
 * @ORM\Entity(repositoryClass="KaquanBundle\Repositories\DiscountRelItemsRepository")
 */
class RelItems
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="item_id", type="bigint", length=64, options={"comment":"筛选id(按item_type区分为商品ID,标签ID等)"})
     */
    private $item_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_show", type="boolean", options={"comment":"是否为列表默认展示", "default" : true})
     */
    private $is_show = true;

    /**
     * @var bigint
     *
     * @ORM\Id
     * @ORM\Column(name="card_id", type="bigint", options={"comment":"优惠券id"})
     */
    private $card_id;

    /**
     * @var string
     *
     * @ORM\Id
     * @ORM\Column(name="item_type", type="string", options={"comment":"筛选类型,可选值有normal:普通商品,tag:标签,brand:品牌,category:主类目","default":"normal"})
     */
    private $item_type = 'normal';

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", nullable=true, options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="use_limit", type="integer", options={"comment":"兑换上限", "default": 0})
     */
    private $use_limit = 0;

    /**
     * Set itemId
     *
     * @param integer $itemId
     *
     * @return RelItems
     */
    public function setItemId($itemId)
    {
        $this->item_id = $itemId;

        return $this;
    }

    /**
     * Get itemId
     *
     * @return integer
     */
    public function getItemId()
    {
        return $this->item_id;
    }

    /**
     * Set cardId
     *
     * @param integer $cardId
     *
     * @return RelItems
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
     * Set itemType
     *
     * @param string $itemType
     *
     * @return RelItems
     */
    public function setItemType($itemType)
    {
        $this->item_type = $itemType;

        return $this;
    }

    /**
     * Get itemType
     *
     * @return string
     */
    public function getItemType()
    {
        return $this->item_type;
    }

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return RelItems
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
     * Set isShow
     *
     * @param boolean $isShow
     *
     * @return RelItems
     */
    public function setIsShow($isShow)
    {
        $this->is_show = $isShow;

        return $this;
    }

    /**
     * Get isShow
     *
     * @return boolean
     */
    public function getIsShow()
    {
        return $this->is_show;
    }

    /**
     * Set useLimit.
     *
     * @param int $useLimit
     *
     * @return RelItems
     */
    public function setUseLimit($useLimit)
    {
        $this->use_limit = $useLimit;

        return $this;
    }

    /**
     * Get useLimit.
     *
     * @return int
     */
    public function getUseLimit()
    {
        return $this->use_limit;
    }
}
