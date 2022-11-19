<?php

namespace SalespersonBundle\Entities;

use Doctrine\ORM\Mapping as ORM;

/**
 * ProfitStatistics 分润记录表
 *
 * @ORM\Table(name="companys_profit_statistics", options={"comment":"分润记录表"})
 * @ORM\Entity(repositoryClass="SalespersonBundle\Repositories\ProfitStatisticsRepository")
 */
class ProfitStatistics
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
     * @var string
     *
     * @ORM\Column(name="date", type="string", length=20, options={"comment":"分润月份"})
     */
    private $date;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="profit_user_id", type="bigint", options={"comment":"分润类型id"})
     */
    private $profit_user_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="profit_user_type", type="bigint", options={"comment":"1 用户 2 店铺 3 区域经销商 4 总部"})
     */
    private $profit_user_type;

    /**
     * @var integer
     *
     * @ORM\Column(name="withdrawals_fee", type="bigint", nullable=true, options={"default": 0, "comment":"提现金额，以分为单位"})
     */
    private $withdrawals_fee;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", nullable=true, length=100, options={"default": "", "comment":"提现对象名称"})
     */
    private $name;

    /**
     * @var json_array
     *
     * @ORM\Column(name="params", type="json_array", nullable=true, options={"comment":"提现对象名称"})
     */
    private $params;


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
     * Set date.
     *
     * @param string $date
     *
     * @return ProfitStatistics
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date.
     *
     * @return string
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return ProfitStatistics
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
     * Set profitUserId.
     *
     * @param int $profitUserId
     *
     * @return ProfitStatistics
     */
    public function setProfitUserId($profitUserId)
    {
        $this->profit_user_id = $profitUserId;

        return $this;
    }

    /**
     * Get profitUserId.
     *
     * @return int
     */
    public function getProfitUserId()
    {
        return $this->profit_user_id;
    }

    /**
     * Set profitUserType.
     *
     * @param int $profitUserType
     *
     * @return ProfitStatistics
     */
    public function setProfitUserType($profitUserType)
    {
        $this->profit_user_type = $profitUserType;

        return $this;
    }

    /**
     * Get profitUserType.
     *
     * @return int
     */
    public function getProfitUserType()
    {
        return $this->profit_user_type;
    }

    /**
     * Set withdrawalsFee.
     *
     * @param int|null $withdrawalsFee
     *
     * @return ProfitStatistics
     */
    public function setWithdrawalsFee($withdrawalsFee = null)
    {
        $this->withdrawals_fee = $withdrawalsFee;

        return $this;
    }

    /**
     * Get withdrawalsFee.
     *
     * @return int|null
     */
    public function getWithdrawalsFee()
    {
        return $this->withdrawals_fee;
    }

    /**
     * Set name.
     *
     * @param string|null $name
     *
     * @return ProfitStatistics
     */
    public function setName($name = null)
    {
        $this->name = $name;

        return $this;
    }

    /**
     * Get name.
     *
     * @return string|null
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set params.
     *
     * @param array|null $params
     *
     * @return ProfitStatistics
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
}
