<?php

namespace DistributionBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * Distributor
 *
 * @ORM\Table(name="distribution_distributor",options={"comment":"店铺表"},
 *     indexes={
 *         @ORM\Index(name="ix_is_distributor", columns={"is_distributor"}),
 *         @ORM\Index(name="ix_shop_id", columns={"shop_id"}),
 *         @ORM\Index(name="ix_company_id_shop_code", columns={"company_id", "shop_code"}),
 *         @ORM\Index(name="ix_is_ziti", columns={"is_ziti"}),
 *         @ORM\Index(name="idx_merchant_id", columns={"merchant_id"}),
 *     },)
 * @ORM\Entity(repositoryClass="DistributionBundle\Repositories\DistributorRepository")
 */
class Distributor
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="distributor_id", type="bigint")
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $distributor_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_distributor", type="boolean", options={"comment":"是否是主店铺", "default": true})
     */
    private $is_distributor = true;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="mobile", type="string", length=255, options={"comment":"店铺手机号"})
     */
    private $mobile;

    /**
     * @var string
     *
     * 店铺地址
     *
     * @ORM\Column(name="address", nullable=true, type="string", options={"comment":"店铺地址"})
     */
    private $address;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", options={"comment":"店铺名称"})
     */
    private $name;

    /**
     * @var string
     *
     * @ORM\Column(name="auto_sync_goods", type="boolean", options={"comment":"自动同步总部商品", "default"=false})
     */
    private $auto_sync_goods = false;

    /**
     * @var string
     *
     * @ORM\Column(name="logo", nullable=true, type="string", options={"comment":"店铺logo"})
     */
    private $logo;

    /**
     * @var string
     *
     * @ORM\Column(name="contract_phone", type="string", length=20, options={"comment":"其他联系方式", "default": "0"})
     */
    private $contract_phone = '0';

    /**
     * @var string
     *
     * @ORM\Column(name="banner", nullable=true, type="string", options={"comment":"店铺banner"})
     */
    private $banner;

    /**
     * @var string
     *
     * @ORM\Column(name="contact", nullable=true, length=500, type="string", options={"comment":"联系人名称"})
     */
    private $contact;

    /**
     * @var string
     *
     * @ORM\Column(name="is_valid", type="string", options={"comment":"店铺是否有效","default":"true"})
     */
    private $is_valid = "true";

    /**
     * @var string
     *
     * @ORM\Column(name="lng", type="string", nullable=true, options={"comment":"腾讯地图纬度"})
     */
    private $lng;

    /**
     * @var string
     *
     * @ORM\Column(name="lat", type="string", nullable=true, options={"comment":"腾讯地图经度"})
     */
    private $lat;

    /**
     * @var string
     *
     * @ORM\Column(name="province", type="string", nullable=true)
     */
    private $province;

    /**
     * @var string
     *
     * @ORM\Column(name="city", type="string", nullable=true)
     */
    private $city;

    /**
     * @var string
     *
     * @ORM\Column(name="hour", type="string", length=150, nullable=true, options={"comment":"营业时间"})
     */
    private $hour;

    /**
     * @var string
     *
     * @ORM\Column(name="area", type="string", nullable=true)
     */
    private $area;

    /**
     * @var string
     *
     * @ORM\Column(name="regions_id", type="text", nullable=true, options={"comment":"国家行政区划编码组合，逗号隔开"})
     */
    private $regions_id;

    /**
     * @var string
     *
     * @ORM\Column(name="is_domestic", type="smallint", length=1, nullable=true, options={"comment":"是否是中国国内门店 1:国内(包含港澳台),2:非国内", "default": 1})
     */
    private $is_domestic = 1;

    /**
     * @var string
     *
     *
     * @ORM\Column(name="is_direct_store", type="smallint", length=1, nullable=true, options={"comment":"是否为直营店 1:直营店,2:非直营店", "default": 1})
     */
    private $is_direct_store = 1;

    /**
     * @var integer
     *
     * @ORM\Column(name="child_count", type="integer", nullable=true)
     */
    private $child_count = 0;

    /**
     * @var integer
     *
     * @ORM\Column(name="shop_id", type="bigint", nullable=true, options={"comment":"门店id", "default": 0})
     */
    private $shop_id = 0;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_default", type="integer", nullable=true, options={"comment":"门店id", "default": 0})
     */
    private $is_default = 0;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_audit_goods", type="boolean", nullable=true, options={"comment":"是否审核店铺商品", "default": false})
     */
    private $is_audit_goods = false;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_ziti", type="boolean", nullable=true, options={"comment":"是否支持自提", "default": false})
     */
    private $is_ziti = false;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_delivery", type="boolean", nullable=true, options={"comment":"是否支持配送", "default": true})
     */
    private $is_delivery = true;

    /**
     * @var string
     *
     * @ORM\Column(name="regions", type="text", nullable=true, options={"comment":"地区名称组合。json格式"})
     */
    private $regions;

    /**
     * @var integer
     *
     * @ORM\Column(name="review_status", type="boolean", nullable=true, options={"comment":"入驻审核状态，0未审核，1已审核", "default": false})
     */
    private $review_status = 0;

    /**
     * @var string
     *
     * @ORM\Column(name="source_from", type="integer", nullable=true, options={"comment":"店铺来源，1管理端添加，2小程序申请入驻，3外部开放接口添加", "default": 1})
     */
    private $source_from = 1;

    /**
     * @var string
     *
     * @ORM\Column(name="dealer_id", type="integer", nullable=true, options={"comment":"经销商ID", "default": 0})
     */
    private $dealer_id = 0;

    /**
     * @var string
     *
     * @ORM\Column(name="split_ledger_info", type="string", nullable=true, options={"comment":"分账信息"})
     */
    private $split_ledger_info;

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
     * @var string
     *
     * @ORM\Column(name="shop_code", nullable=true, type="string", options={"comment":"店铺号"})
     */
    private $shop_code;

    /**
     * @var integer
     *
     * @ORM\Column(name="distributor_self", type="integer", nullable=true, options={"comment":"是否是总店配置", "default": 0})
     */
    private $distributor_self = 0;

    /**
     * @var integer
     *
     * @ORM\Column(name="wechat_work_department_id", type="integer", nullable=true, options={"comment":"企业微信的部门ID", "default": 0})
     */
    private $wechat_work_department_id = 0;

    /**
     * @var integer
     *
     * @ORM\Column(name="regionauth_id", type="bigint", options={"comment":"区域id", "default": 0})
     */
    private $regionauth_id = 0;

    /**
     * @var boolean
     *
     * @ORM\Column(name="is_open", type="string", options={"comment":"是否开启分账","default":"false"})
     */
    private $is_open = 'false';

    /**
     * @var integer
     *
     * @ORM\Column(name="rate", type="integer", nullable=true, options={"unsigned":true, "comment":"平台服务费率"})
     */
    private $rate;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_dada", type="boolean", options={"default":0, "comment":"是否开启达达同城配,0:未开启,1:已开启"})
     */
    private $is_dada;

    /**
     * @var integer
     *
     * @ORM\Column(name="business", type="smallint", nullable=true,  options={"comment":"业务类型(食品小吃-1,饮料-2,鲜花绿植-3,文印票务-8,便利店-9,水果生鲜-13,同城电商-19, 医药-20,蛋糕-21,酒品-24,小商品市场-25,服装-26,汽修零配-27,数码家电-28,小龙虾-29,个人-50,火锅-51,个护美妆-53、母婴-55,家居家纺-57,手机-59,家装-61,其他-5)"})
     */
    private $business;

    /**
     * @var integer
     *
     * @ORM\Column(name="dada_shop_create", type="boolean", options={"default":0, "comment":"该门店在达达是否已创建,0:未创建,1:已创建"})
     */
    private $dada_shop_create;

    /**
     * @var string
     *
     * @ORM\Column(name="introduce", type="text", nullable=true, options={"comment":"店铺介绍"})
     */
    private $introduce;

    /**
     * @var integer
     *
     * @ORM\Column(name="merchant_id", type="bigint", options={"comment":"商户id", "default": 0})
     */
    private $merchant_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="distribution_type", type="smallint", options={"comment":"店铺类型:0:自营,1:加盟","default":"0"})
     */
    private $distribution_type = 0;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_require_subdistrict", type="boolean", nullable=true, options={"comment":"下单是否需要选择街道社区", "default": false})
     */
    private $is_require_subdistrict = false;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_require_building", type="boolean", nullable=true, options={"comment":"下单是否需要填写楼栋门牌号", "default": false})
     */
    private $is_require_building = false;

    /**
     * Get distributorId
     *
     * @return integer
     */
    public function getDistributorId()
    {
        return $this->distributor_id;
    }

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return Distributor
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
     * Set mobile
     *
     * @param string $mobile
     *
     * @return Distributor
     */
    public function setMobile($mobile)
    {
        $this->mobile = fixedencrypt($mobile);

        return $this;
    }

    /**
     * Get mobile
     *
     * @return string
     */
    public function getMobile()
    {
        if ($this->mobile) {
            return fixeddecrypt($this->mobile);
        } else {
            return $this->mobile;
        }
    }

    /**
     * Set address
     *
     * @param string $address
     *
     * @return Distributor
     */
    public function setAddress($address)
    {
        $this->address = $address;

        return $this;
    }

    /**
     * Get address
     *
     * @return string
     */
    public function getAddress()
    {
        return $this->address;
    }

    /**
     * Set name
     *
     * @param string $name
     *
     * @return Distributor
     */
    public function setName($name)
    {
        $this->name = $name;

        return $this;
    }

    /**
     * Get name
     *
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return Distributor
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
     * @return Distributor
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
     * Set isValid
     *
     * @param string $isValid
     *
     * @return Distributor
     */
    public function setIsValid($isValid)
    {
        $this->is_valid = $isValid;

        return $this;
    }

    /**
     * Get isValid
     *
     * @return string
     */
    public function getIsValid()
    {
        return $this->is_valid;
    }

    /**
     * Set province
     *
     * @param string $province
     *
     * @return Distributor
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
     * Set city
     *
     * @param string $city
     *
     * @return Distributor
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
     * Set area
     *
     * @param string $area
     *
     * @return Distributor
     */
    public function setArea($area)
    {
        $this->area = $area;

        return $this;
    }

    /**
     * Get area
     *
     * @return string
     */
    public function getArea()
    {
        return $this->area;
    }

    /**
     * Set regionsId
     *
     * @param string $regionsId
     *
     * @return Distributor
     */
    public function setRegionsId($regionsId)
    {
        $this->regions_id = $regionsId;

        return $this;
    }

    /**
     * Get regionsId
     *
     * @return string
     */
    public function getRegionsId()
    {
        return $this->regions_id;
    }

    /**
     * Set regions
     *
     * @param string $regions
     *
     * @return Distributor
     */
    public function setRegions($regions)
    {
        $this->regions = $regions;

        return $this;
    }

    /**
     * Get regions
     *
     * @return string
     */
    public function getRegions()
    {
        return $this->regions;
    }

    /**
     * Set childCount
     *
     * @param integer $childCount
     *
     * @return Distributor
     */
    public function setChildCount($childCount)
    {
        $this->child_count = $childCount;

        return $this;
    }

    /**
     * Get childCount
     *
     * @return integer
     */
    public function getChildCount()
    {
        return $this->child_count;
    }

    /**
     * Set contact
     *
     * @param string $contact
     *
     * @return Distributor
     */
    public function setContact($contact)
    {
        $this->contact = fixedencrypt($contact);

        return $this;
    }

    /**
     * Get contact
     *
     * @return string
     */
    public function getContact()
    {
        return fixeddecrypt($this->contact);
    }

    /**
     * Set shopId
     *
     * @param integer $shopId
     *
     * @return Distributor
     */
    public function setShopId($shopId)
    {
        $this->shop_id = $shopId;

        return $this;
    }

    /**
     * Get shopId
     *
     * @return integer
     */
    public function getShopId()
    {
        return $this->shop_id;
    }

    /**
     * Set isZiti
     *
     * @param boolean $isZiti
     *
     * @return Distributor
     */
    public function setIsZiti($isZiti)
    {
        $this->is_ziti = $isZiti;

        return $this;
    }

    /**
     * Get isZiti
     *
     * @return boolean
     */
    public function getIsZiti()
    {
        return $this->is_ziti;
    }

    /**
     * Set isDefault
     *
     * @param integer $isDefault
     *
     * @return Distributor
     */
    public function setIsDefault($isDefault)
    {
        $this->is_default = $isDefault;

        return $this;
    }

    /**
     * Get isDefault
     *
     * @return integer
     */
    public function getIsDefault()
    {
        return $this->is_default;
    }

    /**
     * Set lng
     *
     * @param string $lng
     *
     * @return Distributor
     */
    public function setLng($lng)
    {
        $this->lng = $lng;

        return $this;
    }

    /**
     * Get lng
     *
     * @return string
     */
    public function getLng()
    {
        return $this->lng;
    }

    /**
     * Set lat
     *
     * @param string $lat
     *
     * @return Distributor
     */
    public function setLat($lat)
    {
        $this->lat = $lat;

        return $this;
    }

    /**
     * Get lat
     *
     * @return string
     */
    public function getLat()
    {
        return $this->lat;
    }

    /**
     * Set hour
     *
     * @param string $hour
     *
     * @return Distributor
     */
    public function setHour($hour)
    {
        $this->hour = $hour;

        return $this;
    }

    /**
     * Get hour
     *
     * @return string
     */
    public function getHour()
    {
        return $this->hour;
    }

    /**
     * Set autoSyncGoods
     *
     * @param boolean $autoSyncGoods
     *
     * @return Distributor
     */
    public function setAutoSyncGoods($autoSyncGoods)
    {
        $this->auto_sync_goods = $autoSyncGoods;

        return $this;
    }

    /**
     * Get autoSyncGoods
     *
     * @return boolean
     */
    public function getAutoSyncGoods()
    {
        return $this->auto_sync_goods;
    }

    /**
     * Set logo
     *
     * @param string $logo
     *
     * @return Distributor
     */
    public function setLogo($logo)
    {
        $this->logo = $logo;

        return $this;
    }

    /**
     * Get logo
     *
     * @return string
     */
    public function getLogo()
    {
        return $this->logo;
    }

    /**
     * Set banner
     *
     * @param string $banner
     *
     * @return Distributor
     */
    public function setBanner($banner)
    {
        $this->banner = $banner;

        return $this;
    }

    /**
     * Get banner
     *
     * @return string
     */
    public function getBanner()
    {
        return $this->banner;
    }

    /**
     * Set isAuditGoods
     *
     * @param boolean $isAuditGoods
     *
     * @return Distributor
     */
    public function setIsAuditGoods($isAuditGoods)
    {
        $this->is_audit_goods = $isAuditGoods;

        return $this;
    }

    /**
     * Get isAuditGoods
     *
     * @return boolean
     */
    public function getIsAuditGoods()
    {
        return $this->is_audit_goods;
    }

    /**
     * Set isDelivery
     *
     * @param boolean $isDelivery
     *
     * @return Distributor
     */
    public function setIsDelivery($isDelivery)
    {
        $this->is_delivery = $isDelivery;

        return $this;
    }

    /**
     * Get isDelivery
     *
     * @return boolean
     */
    public function getIsDelivery()
    {
        return $this->is_delivery;
    }

    /**
     * Set shopCode.
     *
     * @param string|null $shopCode
     *
     * @return Distributor
     */
    public function setShopCode($shopCode = null)
    {
        $this->shop_code = $shopCode;

        return $this;
    }

    /**
     * Get shopCode.
     *
     * @return string|null
     */
    public function getShopCode()
    {
        return $this->shop_code;
    }

    /**
     * Set reviewStatus.
     *
     * @param int $reviewStatus
     *
     * @return Distributor
     */
    public function setReviewStatus($reviewStatus)
    {
        $this->review_status = $reviewStatus;

        return $this;
    }

    /**
     * Get reviewStatus.
     *
     * @return int
     */
    public function getReviewStatus()
    {
        return $this->review_status;
    }

    /**
     * Set sourceFrom.
     *
     * @param int|null $sourceFrom
     *
     * @return Distributor
     */
    public function setSourceFrom($sourceFrom = null)
    {
        $this->source_from = $sourceFrom;

        return $this;
    }

    /**
     * Get sourceFrom.
     *
     * @return int|null
     */
    public function getSourceFrom()
    {
        return $this->source_from;
    }

    /**
     * Set distributorSelf.
     *
     * @param int|null $distributorSelf
     *
     * @return Distributor
     */
    public function setDistributorSelf($distributorSelf = null)
    {
        $this->distributor_self = $distributorSelf;

        return $this;
    }

    /**
     * Get distributorSelf.
     *
     * @return int|null
     */
    public function getDistributorSelf()
    {
        return $this->distributor_self;
    }

    /**
     * Set isDistributor.
     *
     * @param bool $isDistributor
     *
     * @return Distributor
     */
    public function setIsDistributor($isDistributor)
    {
        $this->is_distributor = $isDistributor;

        return $this;
    }

    /**
     * Get isDistributor.
     *
     * @return bool
     */
    public function getIsDistributor()
    {
        return $this->is_distributor;
    }

    /**
     * Set contractPhone.
     *
     * @param string $contractPhone
     *
     * @return Distributor
     */
    public function setContractPhone($contractPhone)
    {
        $this->contract_phone = $contractPhone;

        return $this;
    }

    /**
     * Get contractPhone.
     *
     * @return string
     */
    public function getContractPhone()
    {
        return $this->contract_phone;
    }

    /**
     * Set isDomestic.
     *
     * @param int|null $isDomestic
     *
     * @return Distributor
     */
    public function setIsDomestic($isDomestic = null)
    {
        $this->is_domestic = $isDomestic;

        return $this;
    }

    /**
     * Get isDomestic.
     *
     * @return int|null
     */
    public function getIsDomestic()
    {
        return $this->is_domestic;
    }

    /**
     * Set isDirectStore.
     *
     * @param int|null $isDirectStore
     *
     * @return Distributor
     */
    public function setIsDirectStore($isDirectStore = null)
    {
        $this->is_direct_store = $isDirectStore;

        return $this;
    }

    /**
     * Get isDirectStore.
     *
     * @return int|null
     */
    public function getIsDirectStore()
    {
        return $this->is_direct_store;
    }

    /**
     * Set wechatWorkDepartmentId.
     *
     * @param int|null $wechatWorkDepartmentId
     *
     * @return Distributor
     */
    public function setWechatWorkDepartmentId($wechatWorkDepartmentId = null)
    {
        $this->wechat_work_department_id = $wechatWorkDepartmentId;

        return $this;
    }

    /**
     * Get wechatWorkDepartmentId.
     *
     * @return int|null
     */
    public function getWechatWorkDepartmentId()
    {
        return $this->wechat_work_department_id;
    }

    /**
     * Set regionauthId.
     *
     * @param int $regionauthId
     *
     * @return Distributor
     */
    public function setRegionauthId($regionauthId)
    {
        $this->regionauth_id = $regionauthId;

        return $this;
    }

    /**
     * Set isOPen.
     *
     * @param string $isOPen
     *
     * @return Distributor
     */
    public function setIsOpen($isOPen = null)
    {
        $this->is_open = $isOPen;

        return $this;
    }

    /**
     * Get regionauthId.
     *
     * @return int
     */
    public function getRegionauthId()
    {
        return $this->regionauth_id;
    }

    /**
     * Get isOpen.
     *
     * @return string
     */
    public function getIsOpen()
    {
        return $this->is_open;
    }

    /**
     * Set rate.
     *
     * @param int|null $rate
     *
     * @return Distributor
     */
    public function setRate($rate = null)
    {
        $this->rate = $rate;

        return $this;
    }

    /**
     * Get wechatWorkDepartmentId.
     *
     * @return int|null
     */
    public function getRate()
    {
        return $this->rate;
    }

    /**
     * Set isDada.
     *
     * @param boolean $isDada
     *
     * @return Distributor
     */
    public function setIsDada($isDada)
    {
        $this->is_dada = $isDada;

        return $this;
    }

    /**
     * Get isDada.
     *
     * @return boolean
     */
    public function getIsDada()
    {
        return $this->is_dada;
    }

    /**
     * Set business.
     *
     * @param int|null $business
     *
     * @return Distributor
     */
    public function setBusiness($business = null)
    {
        $this->business = $business;

        return $this;
    }

    /**
     * Get business.
     *
     * @return int|null
     */
    public function getBusiness()
    {
        return $this->business;
    }

    /**
     * Set dadaShopCreate.
     *
     * @param boolean $dadaShopCreate
     *
     * @return Distributor
     */
    public function setDadaShopCreate($dadaShopCreate)
    {
        $this->dada_shop_create = $dadaShopCreate;

        return $this;
    }

    /**
     * Get dadaShopCreate.
     *
     * @return boolean
     */
    public function getDadaShopCreate()
    {
        return $this->dada_shop_create;
    }


    /**
     * Set dealerId.
     *
     * @param int|null $dealerId
     *
     * @return Distributor
     */
    public function setDealerId($dealerId = null)
    {
        $this->dealer_id = $dealerId;

        return $this;
    }

    /**
     * Get dealerId.
     *
     * @return int|null
     */
    public function getDealerId()
    {
        return $this->dealer_id;
    }

    /**
     * Set splitLedgerInfo.
     *
     * @param string|null $splitLedgerInfo
     *
     * @return Distributor
     */
    public function setSplitLedgerInfo($splitLedgerInfo = null)
    {
        $this->split_ledger_info = $splitLedgerInfo;

        return $this;
    }

    /**
     * Get splitLedgerInfo.
     *
     * @return string|null
     */
    public function getSplitLedgerInfo()
    {
        return $this->split_ledger_info;
    }

    /**
     * Set introduce
     *
     * @param string $introduce
     *
     * @return Distributor
     */
    public function setIntroduce($introduce)
    {
        $this->introduce = $introduce;

        return $this;
    }

    /**
     * Get introduce
     *
     * @return string
     */
    public function getIntroduce()
    {
        return $this->introduce;
    }
    /**
     * Set distributionType.
     *
     * @param int $distributionType
     *
     * @return Distributor
     */
    public function setDistributionType($distributionType)
    {
        $this->distribution_type = $distributionType;

        return $this;
    }

    /**
     * Get distributionType.
     *
     * @return int
     */
    public function getDistributionType()
    {
        return $this->distribution_type;
    }

    /**
     * Set merchantId.
     *
     * @param int $merchantId
     *
     * @return Distributor
     */
    public function setMerchantId($merchantId)
    {
        $this->merchant_id = $merchantId;

        return $this;
    }

    /**
     * Get merchantId.
     *
     * @return int
     */
    public function getMerchantId()
    {
        return $this->merchant_id;
    }

    /**
     * Set isRequireSubdistrict
     *
     * @param string $isRequireSubdistrict
     *
     * @return Distributor
     */
    public function setIsRequireSubdistrict($isRequireSubdistrict)
    {
        $this->is_require_subdistrict = $isRequireSubdistrict;

        return $this;
    }

    /**
     * Get isRequireSubdistrict
     *
     * @return string
     */
    public function getIsRequireSubdistrict()
    {
        return $this->is_require_subdistrict;
    }

    /**
     * Set isRequireBuilding
     *
     * @param string $isRequireBuilding
     *
     * @return Distributor
     */
    public function setIsRequireBuilding($isRequireBuilding)
    {
        $this->is_require_building = $isRequireBuilding;

        return $this;
    }

    /**
     * Get isRequireBuilding
     *
     * @return string
     */
    public function getIsRequireBuilding()
    {
        return $this->is_require_building;
    }
}
