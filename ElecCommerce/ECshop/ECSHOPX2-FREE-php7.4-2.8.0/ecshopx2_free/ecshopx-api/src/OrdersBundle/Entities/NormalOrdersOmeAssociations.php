<?php

namespace OrdersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;

/**
 * NormalOrdersOmeAssociations OME订单关联表
 *
 * @ORM\Table(name="orders_normal_orders_ome_associations", options={"comment":"OME订单关联表"})
 * @ORM\Entity(repositoryClass="OrdersBundle\Repositories\NormalOrdersOmeAssociationsRepository")
 */
class NormalOrdersOmeAssociations
{
    /**
     * @var integer
     *
     * @ORM\Column(name="order_id", type="bigint", length=64, options={"comment":"订单号"})
     */
    private $order_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="ome_order_id", type="bigint", length=64, options={"comment":"ome订单号"})
     */
    private $ome_order_id;

    /**
     * Set orderId
     *
     * @param integer $orderId
     *
     * @return OrderAssociations
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
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return OrderAssociations
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
     * Set omeOrderId
     *
     * @param integer $omeOrderId
     *
     * @return NormalOrdersItems
     */
    public function setOmeOrderId($omeOrderId)
    {
        $this->ome_order_id = $omeOrderId;

        return $this;
    }

    /**
     * Get omeOrderId
     *
     * @return integer
     */
    public function getOmeOrderId()
    {
        return $this->ome_order_id;
    }
}
