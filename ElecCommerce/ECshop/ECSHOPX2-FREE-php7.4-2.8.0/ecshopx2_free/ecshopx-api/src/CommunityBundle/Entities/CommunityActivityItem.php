<?php

namespace CommunityBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * community_activity_item 社区拼团活动商品表
 *
 * @ORM\Table(name="community_activity_item", options={"comment"="社区拼团活动商品表"}, indexes={
 *    @ORM\Index(name="ix_activity_id", columns={"activity_id"}),
 *    @ORM\Index(name="ix_item_id", columns={"item_id"})
 * })
 * @ORM\Entity(repositoryClass="CommunityBundle\Repositories\CommunityActivityItemRepository")
 */
class CommunityActivityItem
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
     * @ORM\Column(name="activity_id", type="bigint", options={"comment":"活动ID"})
     */
    private $activity_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="goods_id", type="bigint", options={"comment":"商品spu ID"})
     */
    private $goods_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="item_id", type="bigint", options={"comment":"商品sku ID"})
     */
    private $item_id;

    /**
     * @var string
     *
     * @ORM\Column(name="item_name", type="string", options={"comment":"商品名称"})
     */
    private $item_name;

    /**
     * @var string
     *
     * @ORM\Column(name="item_spec_desc", type="string", nullable=true, options={"comment":"商品规格描述"})
     */
    private $item_spec_desc;

    /**
     * @var string
     *
     * @ORM\Column(name="item_brief", type="string", nullable=true, length=255, options={"comment":"商品描述"})
     */
    private $item_brief;

    /**
     * @var string
     *
     * @ORM\Column(name="item_pics", type="text", nullable=true,  options={"comment":"商品图片"})
     */
    private $item_pics;

    /**
     * @var integer
     *
     * @ORM\Column(name="price", type="integer", options={"comment":"销售价,单位为‘分’"})
     */
    private $price;

    /**
     * @var integer
     *
     * @ORM\Column(name="cost_price", type="integer", nullable=true, options={"comment":"成本价,单位为‘分’", "default": 0})
     */
    private $cost_price = 0;

    /**
     * @var integer
     *
     * @ORM\Column(name="market_price", type="integer", options={"comment":"原价,单位为‘分’"})
     */
    private $market_price;

    /**
     * @var integer
     *
     * @ORM\Column(name="store", type="integer", options={"comment":"库存数量"})
     */
    private $store;

    /**
     * @var \DateTime $created_at
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer")
     */
    protected $created_at;

    /**
     * @var \DateTime $updated_at
     *
     * @Gedmo\Timestampable(on="update")
     * @ORM\Column(type="integer", nullable=true)
     */
    protected $updated_at;

    /**
     * get Id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * get ActivityId
     *
     * @return int
     */
    public function getActivityId()
    {
        return $this->activity_id;
    }

    /**
     * set ActivityId
     *
     * @param int $activity_id
     *
     * @return self
     */
    public function setActivityId($activity_id)
    {
        $this->activity_id = $activity_id;
        return $this;
    }

    /**
     * get GoodsId
     *
     * @return int
     */
    public function getGoodsId()
    {
        return $this->goods_id;
    }

    /**
     * set GoodsId
     *
     * @param int $goods_id
     *
     * @return self
     */
    public function setGoodsId($goods_id)
    {
        $this->goods_id = $goods_id;
        return $this;
    }

    /**
     * get ItemId
     *
     * @return int
     */
    public function getItemId()
    {
        return $this->item_id;
    }

    /**
     * set ItemId
     *
     * @param int $item_id
     *
     * @return self
     */
    public function setItemId($item_id)
    {
        $this->item_id = $item_id;
        return $this;
    }

    /**
     * get ItemName
     *
     * @return string
     */
    public function getItemName()
    {
        return $this->item_name;
    }

    /**
     * set ItemName
     *
     * @param string $item_name
     *
     * @return self
     */
    public function setItemName($item_name)
    {
        $this->item_name = $item_name;
        return $this;
    }

    /**
     * get ItemBrief
     *
     * @return string
     */
    public function getItemBrief()
    {
        return $this->item_brief;
    }

    /**
     * set ItemBrief
     *
     * @param string $item_brief
     *
     * @return self
     */
    public function setItemBrief($item_brief)
    {
        $this->item_brief = $item_brief;
        return $this;
    }

    /**
     * get ItemPics
     *
     * @return string
     */
    public function getItemPics()
    {
        return $this->item_pics;
    }

    /**
     * set ItemPics
     *
     * @param string $item_pics
     *
     * @return self
     */
    public function setItemPics($item_pics)
    {
        $this->item_pics = $item_pics;
        return $this;
    }

    /**
     * get Price
     *
     * @return int
     */
    public function getPrice()
    {
        return $this->price;
    }

    /**
     * set Price
     *
     * @param int $price
     *
     * @return self
     */
    public function setPrice($price)
    {
        $this->price = $price;
        return $this;
    }

    /**
     * get CostPrice
     *
     * @return int
     */
    public function getCostPrice()
    {
        return $this->cost_price;
    }

    /**
     * set CostPrice
     *
     * @param int $cost_price
     *
     * @return self
     */
    public function setCostPrice($cost_price)
    {
        $this->cost_price = $cost_price;
        return $this;
    }

    /**
     * get MarketPrice
     *
     * @return int
     */
    public function getMarketPrice()
    {
        return $this->market_price;
    }

    /**
     * set MarketPrice
     *
     * @param int $market_price
     *
     * @return self
     */
    public function setMarketPrice($market_price)
    {
        $this->market_price = $market_price;
        return $this;
    }

    /**
     * get Store
     *
     * @return int
     */
    public function getStore()
    {
        return $this->store;
    }

    /**
     * set Store
     *
     * @param int $store
     *
     * @return self
     */
    public function setStore($store)
    {
        $this->store = $store;
        return $this;
    }
}
