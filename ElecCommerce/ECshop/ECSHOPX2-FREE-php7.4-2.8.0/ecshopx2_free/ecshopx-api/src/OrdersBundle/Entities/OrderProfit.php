<?php

namespace OrdersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * OrderProfit  订单分润表
 *
 * @ORM\Table(name="orders_rel_profit", options={"comment":"订单分润表"})
 * @ORM\Entity(repositoryClass="OrdersBundle\Repositories\OrderProfitRepository")
 */
class OrderProfit
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint", options={"comment":"ID"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="order_id", type="bigint", length=64, options={"comment":"订单id"})
     */
    private $order_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="order_profit_status", type="bigint", length=64, options={"comment":"0 无效分润 1 冻结分润 2 分润成功"})
     */
    private $order_profit_status;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="total_fee", type="bigint", nullable=true, options={"comment":"分润完成之后金额，以分为单位"})
     */
    private $total_fee;

    /**
     * @var integer
     *
     * @ORM\Column(name="pay_fee", type="bigint", nullable=true, options={"comment":"支付订单金额，以分为单位"})
     */
    private $pay_fee;

    /**
     * @var integer
     *
     * @ORM\Column(name="profit_type", type="smallint", options={"comment":"分润类型 1 总部分润 2 自营门店分润 3 加盟门店分润"})
     */
    private $profit_type;

    /**
     * @var integer
     *
     * @ORM\Column(name="user_id", type="bigint", options={"comment":"购买用户id", "default": 0})
     */
    private $user_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="dealer_id", type="bigint", options={"comment":"区域经销商id", "default": 0})
     */
    private $dealer_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="distributor_id", type="bigint", options={"comment":"拉新门店id", "default": 0})
     */
    private $distributor_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="order_distributor_id", type="bigint", options={"comment":"下单当前所在门店id", "default": 0})
     */
    private $order_distributor_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="distributor_nid", type="bigint", options={"comment":"拉新导购当前所在门店id", "default": 0})
     */
    private $distributor_nid;

    /**
     * @var integer
     *
     * @ORM\Column(name="seller_id", type="bigint", options={"comment":"拉新导购id", "default": 0})
     */
    private $seller_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="popularize_distributor_id", type="bigint", options={"comment":"推广门店id", "default": 0})
     */
    private $popularize_distributor_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="popularize_seller_id", type="bigint", options={"comment":"推广导购id", "default": 0})
     */
    private $popularize_seller_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="proprietary", type="bigint", options={"comment":"判断拉新门店 0 无门店 1 自营门店 2 加盟门店", "default": 0})
     */
    private $proprietary;

    /**
     * @var integer
     *
     * @ORM\Column(name="popularize_proprietary", type="bigint", options={"comment":"判断推广门店 0 无门店 1 自营门店 2 加盟门店", "default": 0})
     */
    private $popularize_proprietary;

    /**
     * @var integer
     *
     * @ORM\Column(name="dealers", type="bigint", options={"comment":"区域经销商分成", "default": 0})
     */
    private $dealers;

    /**
     * @var integer
     *
     * @ORM\Column(name="distributor", type="bigint", options={"comment":"拉新门店分成", "default": 0})
     */
    private $distributor;

    /**
     * @var integer
     *
     * @ORM\Column(name="seller", type="bigint", options={"comment":"拉新导购分成（分给门店）", "default": 0})
     */
    private $seller;

    /**
     * @var integer
     *
     * @ORM\Column(name="popularize_distributor", type="bigint", options={"comment":"推广门店分成", "default": 0})
     */
    private $popularize_distributor;

    /**
     * @var integer
     *
     * @ORM\Column(name="popularize_seller", type="bigint", options={"comment":"推广导购分成（分给门店）", "default": 0})
     */
    private $popularize_seller;

    /**
     * @var integer
     *
     * @ORM\Column(name="commission", type="bigint", options={"comment":"总部手续费", "default": 0})
     */
    private $commission;

    /**
     * @var json_array
     *
     * @ORM\Column(name="rule", type="json_array", nullable=true, options={"comment":"分润规则"})
     */
    private $rule;

    /**
     * @var integer
     *
     * @ORM\Column(name="plan_close_time", type="bigint", nullable=true, options={"comment":"计划结算时间", "default": 0})
     */
    private $plan_close_time;

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
     * Set orderId.
     *
     * @param int $orderId
     *
     * @return OrderProfit
     */
    public function setOrderId($orderId)
    {
        $this->order_id = $orderId;

        return $this;
    }

    /**
     * Get orderId.
     *
     * @return int
     */
    public function getOrderId()
    {
        return $this->order_id;
    }

    /**
     * Set orderProfitStatus.
     *
     * @param int $orderProfitStatus
     *
     * @return OrderProfit
     */
    public function setOrderProfitStatus($orderProfitStatus)
    {
        $this->order_profit_status = $orderProfitStatus;

        return $this;
    }

    /**
     * Get orderProfitStatus.
     *
     * @return int
     */
    public function getOrderProfitStatus()
    {
        return $this->order_profit_status;
    }

    /**
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return OrderProfit
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
     * Set totalFee.
     *
     * @param int|null $totalFee
     *
     * @return OrderProfit
     */
    public function setTotalFee($totalFee = null)
    {
        $this->total_fee = $totalFee;

        return $this;
    }

    /**
     * Get totalFee.
     *
     * @return int|null
     */
    public function getTotalFee()
    {
        return $this->total_fee;
    }

    /**
     * Set profitType.
     *
     * @param int $profitType
     *
     * @return OrderProfit
     */
    public function setProfitType($profitType)
    {
        $this->profit_type = $profitType;

        return $this;
    }

    /**
     * Get profitType.
     *
     * @return int
     */
    public function getProfitType()
    {
        return $this->profit_type;
    }

    /**
     * Set userId.
     *
     * @param int $userId
     *
     * @return OrderProfit
     */
    public function setUserId($userId)
    {
        $this->user_id = $userId;

        return $this;
    }

    /**
     * Get userId.
     *
     * @return int
     */
    public function getUserId()
    {
        return $this->user_id;
    }

    /**
     * Set dealerId.
     *
     * @param int $dealerId
     *
     * @return OrderProfit
     */
    public function setDealerId($dealerId)
    {
        $this->dealer_id = $dealerId;

        return $this;
    }

    /**
     * Get dealerId.
     *
     * @return int
     */
    public function getDealerId()
    {
        return $this->dealer_id;
    }

    /**
     * Set distributorId.
     *
     * @param int $distributorId
     *
     * @return OrderProfit
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

    /**
     * Set orderDistributorId.
     *
     * @param int $orderDistributorId
     *
     * @return OrderProfit
     */
    public function setOrderDistributorId($orderDistributorId)
    {
        $this->order_distributor_id = $orderDistributorId;

        return $this;
    }

    /**
     * Get orderDistributorId.
     *
     * @return int
     */
    public function getOrderDistributorId()
    {
        return $this->order_distributor_id;
    }

    /**
     * Set distributorNid.
     *
     * @param int $distributorNid
     *
     * @return OrderProfit
     */
    public function setDistributorNid($distributorNid)
    {
        $this->distributor_nid = $distributorNid;

        return $this;
    }

    /**
     * Get distributorNid.
     *
     * @return int
     */
    public function getDistributorNid()
    {
        return $this->distributor_nid;
    }

    /**
     * Set sellerId.
     *
     * @param int $sellerId
     *
     * @return OrderProfit
     */
    public function setSellerId($sellerId)
    {
        $this->seller_id = $sellerId;

        return $this;
    }

    /**
     * Get sellerId.
     *
     * @return int
     */
    public function getSellerId()
    {
        return $this->seller_id;
    }

    /**
     * Set popularizeDistributorId.
     *
     * @param int $popularizeDistributorId
     *
     * @return OrderProfit
     */
    public function setPopularizeDistributorId($popularizeDistributorId)
    {
        $this->popularize_distributor_id = $popularizeDistributorId;

        return $this;
    }

    /**
     * Get popularizeDistributorId.
     *
     * @return int
     */
    public function getPopularizeDistributorId()
    {
        return $this->popularize_distributor_id;
    }

    /**
     * Set popularizeSellerId.
     *
     * @param int $popularizeSellerId
     *
     * @return OrderProfit
     */
    public function setPopularizeSellerId($popularizeSellerId)
    {
        $this->popularize_seller_id = $popularizeSellerId;

        return $this;
    }

    /**
     * Get popularizeSellerId.
     *
     * @return int
     */
    public function getPopularizeSellerId()
    {
        return $this->popularize_seller_id;
    }

    /**
     * Set proprietary.
     *
     * @param int $proprietary
     *
     * @return OrderProfit
     */
    public function setProprietary($proprietary)
    {
        $this->proprietary = $proprietary;

        return $this;
    }

    /**
     * Get proprietary.
     *
     * @return int
     */
    public function getProprietary()
    {
        return $this->proprietary;
    }

    /**
     * Set popularizeProprietary.
     *
     * @param int $popularizeProprietary
     *
     * @return OrderProfit
     */
    public function setPopularizeProprietary($popularizeProprietary)
    {
        $this->popularize_proprietary = $popularizeProprietary;

        return $this;
    }

    /**
     * Get popularizeProprietary.
     *
     * @return int
     */
    public function getPopularizeProprietary()
    {
        return $this->popularize_proprietary;
    }

    /**
     * Set dealers.
     *
     * @param int $dealers
     *
     * @return OrderProfit
     */
    public function setDealers($dealers)
    {
        $this->dealers = $dealers;

        return $this;
    }

    /**
     * Get dealers.
     *
     * @return int
     */
    public function getDealers()
    {
        return $this->dealers;
    }

    /**
     * Set distributor.
     *
     * @param int $distributor
     *
     * @return OrderProfit
     */
    public function setDistributor($distributor)
    {
        $this->distributor = $distributor;

        return $this;
    }

    /**
     * Get distributor.
     *
     * @return int
     */
    public function getDistributor()
    {
        return $this->distributor;
    }

    /**
     * Set seller.
     *
     * @param int $seller
     *
     * @return OrderProfit
     */
    public function setSeller($seller)
    {
        $this->seller = $seller;

        return $this;
    }

    /**
     * Get seller.
     *
     * @return int
     */
    public function getSeller()
    {
        return $this->seller;
    }

    /**
     * Set popularizeDistributor.
     *
     * @param int $popularizeDistributor
     *
     * @return OrderProfit
     */
    public function setPopularizeDistributor($popularizeDistributor)
    {
        $this->popularize_distributor = $popularizeDistributor;

        return $this;
    }

    /**
     * Get popularizeDistributor.
     *
     * @return int
     */
    public function getPopularizeDistributor()
    {
        return $this->popularize_distributor;
    }

    /**
     * Set popularizeSeller.
     *
     * @param int $popularizeSeller
     *
     * @return OrderProfit
     */
    public function setPopularizeSeller($popularizeSeller)
    {
        $this->popularize_seller = $popularizeSeller;

        return $this;
    }

    /**
     * Get popularizeSeller.
     *
     * @return int
     */
    public function getPopularizeSeller()
    {
        return $this->popularize_seller;
    }

    /**
     * Set commission.
     *
     * @param int $commission
     *
     * @return OrderProfit
     */
    public function setCommission($commission)
    {
        $this->commission = $commission;

        return $this;
    }

    /**
     * Get commission.
     *
     * @return int
     */
    public function getCommission()
    {
        return $this->commission;
    }

    /**
     * Set rule.
     *
     * @param array|null $rule
     *
     * @return OrderProfit
     */
    public function setRule($rule = null)
    {
        $this->rule = $rule;

        return $this;
    }

    /**
     * Get rule.
     *
     * @return array|null
     */
    public function getRule()
    {
        return $this->rule;
    }

    /**
     * Set payFee.
     *
     * @param int|null $payFee
     *
     * @return OrderProfit
     */
    public function setPayFee($payFee = null)
    {
        $this->pay_fee = $payFee;

        return $this;
    }

    /**
     * Get payFee.
     *
     * @return int|null
     */
    public function getPayFee()
    {
        return $this->pay_fee;
    }

    /**
     * Set created.
     *
     * @param int $created
     *
     * @return OrderProfit
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
     * @return OrderProfit
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
     * Set planCloseTime.
     *
     * @param int|null $planCloseTime
     *
     * @return OrderProfit
     */
    public function setPlanCloseTime($planCloseTime = null)
    {
        $this->plan_close_time = $planCloseTime;

        return $this;
    }

    /**
     * Get planCloseTime.
     *
     * @return int|null
     */
    public function getPlanCloseTime()
    {
        return $this->plan_close_time;
    }
}
