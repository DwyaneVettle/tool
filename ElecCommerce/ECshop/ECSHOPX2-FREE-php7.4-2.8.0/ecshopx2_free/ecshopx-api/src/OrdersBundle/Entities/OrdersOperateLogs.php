<?php

namespace OrdersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * OrderOperateLogs 订单操作日志表
 *
 * @ORM\Table(name="orders_operate_logs", options={"comment":"订单操作日志表"},
 *     indexes={
 *         @ORM\Index(name="order_id", columns={"order_id"}),
 *         @ORM\Index(name="operator_id", columns={"operator_id"}),
 *         @ORM\Index(name="company_id", columns={"company_id"}),
 *     },
 * )
 * @ORM\Entity(repositoryClass="OrdersBundle\Repositories\OrdersOperateLogsRepository")
 */
class OrdersOperateLogs
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint", options={"comment":"订单操作日志"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var integer
     *
     *
     * @ORM\Column(name="order_id", type="bigint", length=64, options={"comment":"订单号"})
     */
    private $order_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司Id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="operator_id", type="bigint", nullable=true, options={"comment":"操作员Id"})
     */
    private $operator_id;

    /**
     * @var string
     *
     * @ORM\Column(name="operator", type="string", nullable=true, options={"comment":"操作员"})
     */
    private $operator;

    /**
     * @var string
     *
     * @ORM\Column(name="operator_role", type="string", nullable=true,length=100,  options={"comment":"操作角色,buyer:购买者;seller:卖家;shopadmin:平台操作员;system:系统"})
     */
    private $operator_role;

    /**
     * @var string
     *
     * @ORM\Column(name="behavior", type="string", nullable=true,length=100,  options={"comment":"操作行为,create:创建;update:修改;payed:支付;delivery:发货；confirm:收货；cancel:取消；refund:退款；reship:退货；exchange:换货；mark:修改备注；finish:完成；"})
     */
    private $behavior;

    /**
     * @var string
     *
     * @ORM\Column(name="log_text", type="text", nullable=true, options={"comment"="操作内容"})
     */
    private $log_text;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer")
     */
    protected $created;

    /**
     * Get id
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set orderId
     *
     * @param integer $orderId
     *
     * @return OrderOperateLogs
     */
    public function setOrderId($orderId)
    {
        $this->order_id = $orderId;

        return $this;
    }

    /**
     * Get orderId
     *
     * @return integer
     */
    public function getOrderId()
    {
        return $this->order_id;
    }

    /**
     * Set operatorId
     *
     * @param integer $operatorId
     *
     * @return OrderOperateLogs
     */
    public function setOperatorId($operatorId)
    {
        $this->operator_id = $operatorId;

        return $this;
    }

    /**
     * Get operatorId
     *
     * @return integer
     */
    public function getOperatorId()
    {
        return $this->operator_id;
    }

    /**
     * Set operator
     *
     * @param string $operator
     *
     * @return OrderOperateLogs
     */
    public function setOperator($operator)
    {
        $this->operator = $operator;

        return $this;
    }

    /**
     * Get operator
     *
     * @return string
     */
    public function getOperator()
    {
        return $this->operator;
    }

    /**
     * Set operatorRole
     *
     * @param string $operatorRole
     *
     * @return OrderOperateLogs
     */
    public function setOperatorRole($operatorRole)
    {
        $this->operator_role = $operatorRole;

        return $this;
    }

    /**
     * Get operatorRole
     *
     * @return string
     */
    public function getOperatorRole()
    {
        return $this->operator_role;
    }

    /**
     * Set behavior
     *
     * @param string $behavior
     *
     * @return OrderOperateLogs
     */
    public function setBehavior($behavior)
    {
        $this->behavior = $behavior;

        return $this;
    }

    /**
     * Get behavior
     *
     * @return string
     */
    public function getBehavior()
    {
        return $this->behavior;
    }

    /**
     * Set logText
     *
     * @param string $logText
     *
     * @return OrderOperateLogs
     */
    public function setLogText($logText)
    {
        $this->log_text = $logText;

        return $this;
    }

    /**
     * Get logText
     *
     * @return string
     */
    public function getLogText()
    {
        return $this->log_text;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return OrderOperateLogs
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
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return OrdersOperateLogs
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
}
