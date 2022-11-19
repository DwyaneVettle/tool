<?php

namespace MembersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * WechatUsers 微信会员表
 *
 * @ORM\Table(name="members_wechatusers", options={"comment"="微信会员表"}, indexes={
 *    @ORM\Index(name="idx_unionid", columns={"unionid"}),
 *    @ORM\Index(name="idx_openid", columns={"open_id"}),
 * })
 * @ORM\Entity(repositoryClass="MembersBundle\Repositories\WechatUsersRepository")
 */
class WechatUsers
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var string
     * @ORM\Id
     * @ORM\Column(name="authorizer_appid", length=64, type="string", options={"comment":"公众号appid"}))
     */
    private $authorizer_appid;

    /**
     * @var string
     *
     * @ORM\Id
     * @ORM\Column(name="open_id", type="string", length=40, options={"comment":"open_id"}))
     */
    private $open_id;

    /**
     * @var string
     *
     * @ORM\Id
     * @ORM\Column(name="unionid", type="string", length=40, options={"comment":"union_id"}))
     */
    private $unionid;

    /**
    * @var int
    *
    * @ORM\Column(name="inviter_id", type="integer", nullable=true, options={"comment":"来源id", "default":0})
    */
    private $inviter_id = 0;

    /**
     * @var int
     *
     * @ORM\Column(name="source_from", type="string", nullable=true, options={"comment":"来源类型 default默认", "default":"default"})
     */
    private $source_from = 'default';

    /**
     * @var bool
     *
     * @ORM\Column(name="need_transfer", type="boolean", options={"comment":"是否禁用。0:不用迁移或迁移完成；1:需要迁移", "default": 0})
     */
    private $need_transfer = 0;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer", columnDefinition="bigint NOT NULL")
     */
    protected $created;

    /**
     * @var \DateTime $updated
     *
     * @Gedmo\Timestampable(on="update")
     * @ORM\Column(type="integer", columnDefinition="bigint NOT NULL")
     */
    protected $updated;


    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return WechatUsers
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
     * Set authorizerAppid
     *
     * @param string $authorizerAppid
     *
     * @return WechatUsers
     */
    public function setAuthorizerAppid($authorizerAppid)
    {
        $this->authorizer_appid = $authorizerAppid;

        return $this;
    }

    /**
     * Get authorizerAppid
     *
     * @return string
     */
    public function getAuthorizerAppid()
    {
        return $this->authorizer_appid;
    }

    /**
     * Set openId
     *
     * @param string $openId
     *
     * @return WechatUsers
     */
    public function setOpenId($openId)
    {
        $this->open_id = $openId;

        return $this;
    }

    /**
     * Get openId
     *
     * @return string
     */
    public function getOpenId()
    {
        return $this->open_id;
    }

    /**
     * Set unionid
     *
     * @param string $unionid
     *
     * @return WechatUsers
     */
    public function setUnionid($unionid)
    {
        $this->unionid = $unionid;

        return $this;
    }

    /**
     * Get unionid
     *
     * @return string
     */
    public function getUnionid()
    {
        return $this->unionid;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return WechatUsers
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
     * Set updated
     *
     * @param integer $updated
     *
     * @return WechatUsers
     */
    public function setUpdated($updated)
    {
        $this->updated = $updated;

        return $this;
    }

    /**
     * Get updated
     *
     * @return integer
     */
    public function getUpdated()
    {
        return $this->updated;
    }

    /**
     * Set inviterId
     *
     * @param integer $inviterId
     *
     * @return WechatUsers
     */
    public function setInviterId($inviterId)
    {
        $this->inviter_id = $inviterId;

        return $this;
    }

    /**
     * Get inviterId
     *
     * @return integer
     */
    public function getInviterId()
    {
        return $this->inviter_id;
    }

    /**
     * Set sourceFrom
     *
     * @param string $sourceFrom
     *
     * @return WechatUsers
     */
    public function setSourceFrom($sourceFrom)
    {
        $this->source_from = $sourceFrom;

        return $this;
    }

    /**
     * Get sourceFrom
     *
     * @return string
     */
    public function getSourceFrom()
    {
        return $this->source_from;
    }

    /**
     * Set needTransfer.
     *
     * @param bool $needTransfer
     *
     * @return WechatUsers
     */
    public function setNeedTransfer($needTransfer)
    {
        $this->need_transfer = $needTransfer;

        return $this;
    }

    /**
     * Get needTransfer.
     *
     * @return bool
     */
    public function getNeedTransfer()
    {
        return $this->need_transfer;
    }
}
