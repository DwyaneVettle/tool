<?php

namespace MembersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * Members 会员手机号注册记录表
 *
 * @ORM\Table(name="members_delete_record", options={"comment"="会员删除记录"}, indexes={
 *    @ORM\Index(name="idx_company_id", columns={"company_id"}),
 *    @ORM\Index(name="idx_mobile",     columns={"mobile"}),
 *    @ORM\Index(name="idx_user_id",     columns={"user_id"})
 * })
 * @ORM\Entity(repositoryClass="MembersBundle\Repositories\MembersDeleteRecordRepository")
 */
class MembersDeleteRecord
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint", options={"comment"="主键id"})
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
     * @ORM\Column(name="mobile", type="string", length=255, options={"comment"="手机号"})
     */
    private $mobile;
    /**
     * @var integer
     * @Gedmo\Timestampable(on="update")
     * @ORM\Column(name="created", type="integer", nullable=true, options={"comment":"创建时间"})
     */
    private $created;

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
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return MembersRegisterRecord
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
     * Set mobile.
     *
     * @param string $mobile
     *
     * @return MembersRegisterRecord
     */
    public function setMobile($mobile)
    {
        $this->mobile = fixedencrypt($mobile);

        return $this;
    }

    /**
     * Get mobile.
     *
     * @return string
     */
    public function getMobile()
    {
        return fixeddecrypt($this->mobile);
    }

    /**
     * Set created.
     *
     * @param int|null $created
     *
     * @return MembersRegisterRecord
     */
    public function setCreated($created = null)
    {
        $this->created = $created;

        return $this;
    }

    /**
     * Get created.
     *
     * @return int|null
     */
    public function getCreated()
    {
        return $this->created;
    }

    /**
     * Set userId.
     *
     * @param int $userId
     *
     * @return MembersDeleteRecord
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
}
