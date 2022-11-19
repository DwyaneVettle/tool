<?php

namespace MembersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * WechatUsers 微信用户表
 *
 * @ORM\Table(name="members_wechatusers_info", options={"comment"="微信用户表", "collate"="utf8mb4_unicode_ci", "charset"="utf8mb4"},
 *     indexes={
 *         @ORM\Index(name="idx_unionid", columns={"unionid"}),
 *     },
 * )
 * @ORM\Entity(repositoryClass="MembersBundle\Repositories\WechatUserInfoRepository")
 */
class WechatUserInfo
{
    /**
     * @var integer
     * @ORM\Id
     * @ORM\Column(name="company_id", type="bigint", options={"comment"="公司id"})
     */
    private $company_id;

    /**
     * @var string
     * @ORM\Id
     * @ORM\Column(name="unionid", type="string", length=40, options={"comment"="微信unionid"})
     */
    private $unionid;

    /**
     * @var string
     *
     * @ORM\Column(name="nickname", type="string", length=500, nullable=true, options={"comment"="昵称"})
     */
    private $nickname;

    /**
     * @var integer
     *
     * 用户性别类型
     * 0 未知
     * 1 男
     * 2 女
     * @ORM\Column(name="sex", type="smallint", nullable=true)
     */
    private $sex;

    /**
     * @var string
     *
     * @ORM\Column(name="city", type="string", nullable=true)
     */
    private $city;

    /**
     * @var string
     *
     * @ORM\Column(name="country", type="string", nullable=true)
     */
    private $country;

    /**
     * @var string
     *
     * @ORM\Column(name="province", type="string", nullable=true)
     */
    private $province;

    /**
     * @var string
     *
     * @ORM\Column(name="language", type="string", nullable=true)
     */
    private $language;

    /**
     * @var string
     *
     * @ORM\Column(name="headimgurl", type="string", nullable=true, options={"comment"="头像url"})
     */
    private $headimgurl;

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
     * @return WechatUserInfo
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
     * Set unionid
     *
     * @param string $unionid
     *
     * @return WechatUserInfo
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
     * Set nickname
     *
     * @param string $nickname
     *
     * @return WechatUserInfo
     */
    public function setNickname($nickname)
    {
        $this->nickname = fixedencrypt($nickname);

        return $this;
    }

    /**
     * Get nickname
     *
     * @return string
     */
    public function getNickname()
    {
        return fixeddecrypt($this->nickname);
    }

    /**
     * Set sex
     *
     * @param integer $sex
     *
     * @return WechatUserInfo
     */
    public function setSex($sex)
    {
        $this->sex = $sex;

        return $this;
    }

    /**
     * Get sex
     *
     * @return integer
     */
    public function getSex()
    {
        return $this->sex;
    }

    /**
     * Set city
     *
     * @param string $city
     *
     * @return WechatUserInfo
     */
    public function setCity($city)
    {
        $this->city = $city;

        return $this;
    }

    /**
     * Get city
     *
     * @return string
     */
    public function getCity()
    {
        return $this->city;
    }

    /**
     * Set country
     *
     * @param string $country
     *
     * @return WechatUserInfo
     */
    public function setCountry($country)
    {
        $this->country = $country;

        return $this;
    }

    /**
     * Get country
     *
     * @return string
     */
    public function getCountry()
    {
        return $this->country;
    }

    /**
     * Set province
     *
     * @param string $province
     *
     * @return WechatUserInfo
     */
    public function setProvince($province)
    {
        $this->province = $province;

        return $this;
    }

    /**
     * Get province
     *
     * @return string
     */
    public function getProvince()
    {
        return $this->province;
    }

    /**
     * Set language
     *
     * @param string $language
     *
     * @return WechatUserInfo
     */
    public function setLanguage($language)
    {
        $this->language = $language;

        return $this;
    }

    /**
     * Get language
     *
     * @return string
     */
    public function getLanguage()
    {
        return $this->language;
    }

    /**
     * Set headimgurl
     *
     * @param string $headimgurl
     *
     * @return WechatUserInfo
     */
    public function setHeadimgurl($headimgurl)
    {
        $this->headimgurl = $headimgurl;

        return $this;
    }

    /**
     * Get headimgurl
     *
     * @return string
     */
    public function getHeadimgurl()
    {
        return $this->headimgurl;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return WechatUserInfo
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
     * @return WechatUserInfo
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
}
