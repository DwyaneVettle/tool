<?php

namespace MembersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * MemberOperateLog 管理员修改员工操作日志表
 *
 * @ORM\Table(name="members_operate_log", options={"comment"="管理员修改员工操作日志表"})
 * @ORM\Entity(repositoryClass="MembersBundle\Repositories\MemberOperateLogRepository")
 */
class MemberOperateLog
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint")
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment"="公司id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="user_id", type="bigint", options={"comment"="用户id"})
     */
    private $user_id;

    /**
     * @var string
     *
     * @ORM\Column(name="operate_type", type="string", options={"comment":"log类型，mobile：修改手机号,grade_id:修改会员等级"})
     */
    private $operate_type = 'mobile';

    /**
     * @var string
     *
     * @ORM\Column(name="remarks", type="string", nullable=true, options={"comment":"操作备注"})
     */
    private $remarks ;

    /**
     * @var string
     *
     * @ORM\Column(name="old_data", type="text", nullable=true, options={"comment"="修改前历史数据"})
     */
    private $old_data;

    /**
     * @var string
     *
     * @ORM\Column(name="new_data", type="text", options={"comment"="新修改的数据"})
     */
    private $new_data;

    /**
     * @var string
     *
     * @ORM\Column(name="operater", type="text", options={"comment"="管理员描述"})
     */
    private $operater;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer", columnDefinition="bigint NOT NULL")
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
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return MemberOperateLog
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
     * Set operateType
     *
     * @param string $operateType
     *
     * @return MemberOperateLog
     */
    public function setOperateType($operateType)
    {
        $this->operate_type = $operateType;

        return $this;
    }

    /**
     * Get operateType
     *
     * @return string
     */
    public function getOperateType()
    {
        return $this->operate_type;
    }

    /**
     * Set oldData
     *
     * @param string $oldData
     *
     * @return MemberOperateLog
     */
    public function setOldData($oldData)
    {
        $this->old_data = $oldData;

        return $this;
    }

    /**
     * Get oldData
     *
     * @return string
     */
    public function getOldData()
    {
        return $this->old_data;
    }

    /**
     * Set newData
     *
     * @param string $newData
     *
     * @return MemberOperateLog
     */
    public function setNewData($newData)
    {
        $this->new_data = $newData;

        return $this;
    }

    /**
     * Get newData
     *
     * @return string
     */
    public function getNewData()
    {
        return $this->new_data;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return MemberOperateLog
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
     * Set operater
     *
     * @param string $operater
     *
     * @return MemberOperateLog
     */
    public function setOperater($operater)
    {
        $this->operater = $operater;

        return $this;
    }

    /**
     * Get operater
     *
     * @return string
     */
    public function getOperater()
    {
        return $this->operater;
    }

    /**
     * Set userId
     *
     * @param integer $userId
     *
     * @return MemberOperateLog
     */
    public function setUserId($userId)
    {
        $this->user_id = $userId;

        return $this;
    }

    /**
     * Get userId
     *
     * @return integer
     */
    public function getUserId()
    {
        return $this->user_id;
    }

    /**
     * Set remarks
     *
     * @param string $remarks
     *
     * @return MemberOperateLog
     */
    public function setRemarks($remarks)
    {
        $this->remarks = $remarks;

        return $this;
    }

    /**
     * Get remarks
     *
     * @return string
     */
    public function getRemarks()
    {
        return $this->remarks;
    }
}
