<?php

namespace DistributionBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * DistributorAftersalesAddress 店铺售后地址
 *
 * @ORM\Table(name="distributor_aftersales_address", options={"comment":"店铺售后地址"})
 * @ORM\Entity(repositoryClass="DistributionBundle\Repositories\DistributorAftersalesAddressRepository")
 */
class DistributorAftersalesAddress
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="address_id", type="bigint")
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $address_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="distributor_id", type="bigint", options={"comment":"店铺id"})
     */
    private $distributor_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"企业id"})
     */
    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="province", type="string", options={"comment":"省"})
     */
    private $province;

    /**
     * @var string
     *
     * @ORM\Column(name="city", type="string", options={"comment":"市"})
     */
    private $city;

    /**
     * @var string
     *
     * @ORM\Column(name="area", type="string", options={"comment":"区/县"})
     */
    private $area;

    /**
     * @var string
     *
     * @ORM\Column(name="regions_id", type="text", options={"comment":""})
     */
    private $regions_id;

    /**
     * @var string
     *
     * @ORM\Column(name="regions", type="text", options={"comment":""})
     */
    private $regions;

    /**
     * @var string
     *
     * @ORM\Column(name="address", type="string", options={"comment":"地址"})
     */
    private $address;

    /**
     * @var string
     *
     * @ORM\Column(name="contact", length=500, type="string", options={"comment":"联系人"})
     */
    private $contact;

    /**
     * @var string
     *
     * @ORM\Column(name="mobile", type="string", options={"comment":"联系人手机号"})
     */
    private $mobile;

    /**
     * @var int
     *
     * @ORM\Column(name="post_code", type="integer", nullable=true, options={"comment":"邮政编码"})
     */
    private $post_code;

    /**
     * @var int
     *
     * @ORM\Column(name="is_default", type="integer", options={"comment":"默认地址, 1:是。2:不是", "default": 2})
     */
    private $is_default = 2;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer", columnDefinition="bigint NOT NULL")
     */
    private $created;

    /**
     * @var \DateTime $updated
     *
     * @Gedmo\Timestampable(on="update")
     * @ORM\Column(type="integer", columnDefinition="bigint NOT NULL")
     */
    private $updated;
    /**
     * @var integer
     *
     * @ORM\Column(name="merchant_id", type="bigint", options={"comment":"商户id", "default": 0})
     */
    private $merchant_id;

    /**
     * Get addressId.
     *
     * @return int
     */
    public function getAddressId()
    {
        return $this->address_id;
    }

    /**
     * Set distributorId.
     *
     * @param int $distributorId
     *
     * @return DistributorAftersalesAddress
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
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return DistributorAftersalesAddress
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
     * Set province.
     *
     * @param string $province
     *
     * @return DistributorAftersalesAddress
     */
    public function setProvince($province)
    {
        $this->province = $province;

        return $this;
    }

    /**
     * Get province.
     *
     * @return string
     */
    public function getProvince()
    {
        return $this->province;
    }

    /**
     * Set city.
     *
     * @param string $city
     *
     * @return DistributorAftersalesAddress
     */
    public function setCity($city)
    {
        $this->city = $city;

        return $this;
    }

    /**
     * Get city.
     *
     * @return string
     */
    public function getCity()
    {
        return $this->city;
    }

    /**
     * Set area.
     *
     * @param string $area
     *
     * @return DistributorAftersalesAddress
     */
    public function setArea($area)
    {
        $this->area = $area;

        return $this;
    }

    /**
     * Get area.
     *
     * @return string
     */
    public function getArea()
    {
        return $this->area;
    }

    /**
     * Set regionsId.
     *
     * @param string $regionsId
     *
     * @return DistributorAftersalesAddress
     */
    public function setRegionsId($regionsId)
    {
        $this->regions_id = $regionsId;

        return $this;
    }

    /**
     * Get regionsId.
     *
     * @return string
     */
    public function getRegionsId()
    {
        return $this->regions_id;
    }

    /**
     * Set regions.
     *
     * @param string $regions
     *
     * @return DistributorAftersalesAddress
     */
    public function setRegions($regions)
    {
        $this->regions = $regions;

        return $this;
    }

    /**
     * Get regions.
     *
     * @return string
     */
    public function getRegions()
    {
        return $this->regions;
    }

    /**
     * Set address.
     *
     * @param string $address
     *
     * @return DistributorAftersalesAddress
     */
    public function setAddress($address)
    {
        $this->address = $address;

        return $this;
    }

    /**
     * Get address.
     *
     * @return string
     */
    public function getAddress()
    {
        return $this->address;
    }

    /**
     * Set contact.
     *
     * @param string $contact
     *
     * @return DistributorAftersalesAddress
     */
    public function setContact($contact)
    {
        $this->contact = fixedencrypt($contact);

        return $this;
    }

    /**
     * Get contact.
     *
     * @return string
     */
    public function getContact()
    {
        return fixeddecrypt($this->contact);
    }

    /**
     * Set mobile.
     *
     * @param string $mobile
     *
     * @return DistributorAftersalesAddress
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
     * Set postCode.
     *
     * @param int|null $postCode
     *
     * @return DistributorAftersalesAddress
     */
    public function setPostCode($postCode = null)
    {
        $this->post_code = $postCode;

        return $this;
    }

    /**
     * Get postCode.
     *
     * @return int|null
     */
    public function getPostCode()
    {
        return $this->post_code;
    }

    /**
     * Set created.
     *
     * @param int $created
     *
     * @return DistributorAftersalesAddress
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
     * @param int $updated
     *
     * @return DistributorAftersalesAddress
     */
    public function setUpdated($updated)
    {
        $this->updated = $updated;

        return $this;
    }

    /**
     * Get updated.
     *
     * @return int
     */
    public function getUpdated()
    {
        return $this->updated;
    }

    /**
     * Set isDefault.
     *
     * @param int $isDefault
     *
     * @return DistributorAftersalesAddress
     */
    public function setIsDefault($isDefault)
    {
        $this->is_default = $isDefault;

        return $this;
    }

    /**
     * Get isDefault.
     *
     * @return int
     */
    public function getIsDefault()
    {
        return $this->is_default;
    }

    /**
     * Set merchantId.
     *
     * @param int $merchantId
     *
     * @return DistributorAftersalesAddress
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
}
