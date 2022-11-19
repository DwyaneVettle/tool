<?php

namespace OrdersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * OrderProcessLog  订单流程记录表
 *
 * @ORM\Table(name="orders_process_log", options={"comment":"订单流程记录表"},
 *     indexes={
 *         @ORM\Index(name="idx_order_id", columns={"order_id"}),
 *     },
 * )
 * @ORM\Entity(repositoryClass="OrdersBundle\Repositories\OrderProcessLogRepository")
 */
class OrderProcessLog
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
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="operator_type", type="string", length=20, options={"comment":"操作类型 用户:user 导购:salesperon 管理员:admin 系统:system"})
     */
    private $operator_type;

    /**
     * @var integer
     *
     * @ORM\Column(name="operator_id", nullable=true, type="bigint", options={"comment":"操作员id", "default": 0})
     */
    private $operator_id;

    /**
     * @var string
     *
     * @ORM\Column(name="operator_name", nullable=true, type="string", options={"comment":"操作员名字", "default": ""})
     */
    private $operator_name;

    /**
     * @var string
     *
     * @ORM\Column(name="remarks", type="string", length=30, options={"comment":"订单操作备注"})
     */
    private $remarks;

    /**
     * @var string
     *
     * @ORM\Column(name="detail", type="text", options={"comment":"订单操作detail"})
     */
    private $detail;

    /**
     * @var json_array
     *
     * @ORM\Column(name="params", type="json_array", nullable=true, options={"comment":"提交参数"})
     */
    private $params;

    /**
     * @var \DateTime $create_time
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer", options={"comment":"订单操作时间"})
     */
    private $create_time;

    /**
     * @var \DateTime $update_time
     *
     * @Gedmo\Timestampable(on="update")
     * @ORM\Column(type="integer", nullable=true, options={"comment":"订单操作"})
     */
    private $update_time;

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
     * @return OrderProcessLog
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
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return OrderProcessLog
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
     * Set operatorType.
     *
     * @param string $operatorType
     *
     * @return OrderProcessLog
     */
    public function setOperatorType($operatorType)
    {
        $this->operator_type = $operatorType;

        return $this;
    }

    /**
     * Get operatorType.
     *
     * @return string
     */
    public function getOperatorType()
    {
        return $this->operator_type;
    }

    /**
     * Set operatorId.
     *
     * @param int|null $operatorId
     *
     * @return OrderProcessLog
     */
    public function setOperatorId($operatorId = null)
    {
        $this->operator_id = $operatorId;

        return $this;
    }

    /**
     * Get operatorId.
     *
     * @return int|null
     */
    public function getOperatorId()
    {
        return $this->operator_id;
    }

    /**
     * Set operatorName.
     *
     * @param string|null $operatorName
     *
     * @return OrderProcessLog
     */
    public function setOperatorName($operatorName = null)
    {
        if (ismobile($operatorName)) {
            $operatorName = fixedencrypt($operatorName);
        }
        $this->operator_name = $operatorName;

        return $this;
    }

    /**
     * Get operatorName.
     *
     * @return string|null
     */
    public function getOperatorName()
    {
        return fixeddecrypt($this->operator_name);
    }

    /**
     * Set remarks.
     *
     * @param string $remarks
     *
     * @return OrderProcessLog
     */
    public function setRemarks($remarks)
    {
        $this->remarks = $remarks;

        return $this;
    }

    /**
     * Get remarks.
     *
     * @return string
     */
    public function getRemarks()
    {
        return $this->remarks;
    }

    /**
     * Set detail.
     *
     * @param string $detail
     *
     * @return OrderProcessLog
     */
    public function setDetail($detail)
    {
        $this->detail = $detail;

        return $this;
    }

    /**
     * Get detail.
     *
     * @return string
     */
    public function getDetail()
    {
        return $this->detail;
    }

    /**
     * Set params.
     *
     * @param array|null $params
     *
     * @return OrderProcessLog
     */
    public function setParams($params = null)
    {
        $this->params = $params;

        return $this;
    }

    /**
     * Get params.
     *
     * @return array|null
     */
    public function getParams()
    {
        return $this->params;
    }

    /**
     * Set createTime.
     *
     * @param int $createTime
     *
     * @return OrderProcessLog
     */
    public function setCreateTime($createTime)
    {
        $this->create_time = $createTime;

        return $this;
    }

    /**
     * Get createTime.
     *
     * @return int
     */
    public function getCreateTime()
    {
        return $this->create_time;
    }

    /**
     * Set updateTime.
     *
     * @param int|null $updateTime
     *
     * @return OrderProcessLog
     */
    public function setUpdateTime($updateTime = null)
    {
        $this->update_time = $updateTime;

        return $this;
    }

    /**
     * Get updateTime.
     *
     * @return int|null
     */
    public function getUpdateTime()
    {
        return $this->update_time;
    }
}
