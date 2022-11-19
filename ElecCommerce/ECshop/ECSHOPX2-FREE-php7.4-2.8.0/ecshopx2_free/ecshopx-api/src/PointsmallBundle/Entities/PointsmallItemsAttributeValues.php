<?php

namespace PointsmallBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * PointsmallItemsCategory 积分商品属性表
 *
 * @ORM\Table(name="pointsmall_items_attribute_values", options={"comment"="积分商品属性值表"}, indexes={
 *    @ORM\Index(name="ix_company_id", columns={"company_id"}),
 *    @ORM\Index(name="ix_attribute_id", columns={"attribute_id"}),
 *    @ORM\Index(name="ix_attribuattribute_valuete_name", columns={"attribuattribute_valuete_name"}),
  * })
 * @ORM\Entity(repositoryClass="PointsmallBundle\Repositories\PointsmallItemsAttributeValuesRepository")
 */
class PointsmallItemsAttributeValues
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="attribute_value_id", type="bigint", options={"comment":"商品属性值id"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $attribute_value_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="attribute_id", type="bigint", options={"comment":"商品属性ID"})
     */
    private $attribute_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司ID"})
     */
    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="attribuattribute_valuete_name", type="string", options={"comment":"商品属性值"})
     */
    private $attribute_value;

    /**
     * @var string
     *
     * @ORM\Column(name="sort", type="string", length=15, options={"comment":"商品属性排序，越大越在前"})
     */
    private $sort;

    /**
     * @var integer
     *
     * @ORM\Column(name="image_url", nullable=true, type="text", options={"comment":"图片"})
     */
    private $image_url;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer")
     */
    protected $created;

    /**
     * @var \DateTime $updated
     *
     * @Gedmo\Timestampable(on="update")
     * @ORM\Column(type="integer", nullable=true)
     */
    protected $updated;


    /**
     * Get attributeValueId.
     *
     * @return int
     */
    public function getAttributeValueId()
    {
        return $this->attribute_value_id;
    }

    /**
     * Set attributeId.
     *
     * @param int $attributeId
     *
     * @return PointsmallItemsAttributeValues
     */
    public function setAttributeId($attributeId)
    {
        $this->attribute_id = $attributeId;

        return $this;
    }

    /**
     * Get attributeId.
     *
     * @return int
     */
    public function getAttributeId()
    {
        return $this->attribute_id;
    }

    /**
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return PointsmallItemsAttributeValues
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
     * Set attributeValue.
     *
     * @param string $attributeValue
     *
     * @return PointsmallItemsAttributeValues
     */
    public function setAttributeValue($attributeValue)
    {
        $this->attribute_value = $attributeValue;

        return $this;
    }

    /**
     * Get attributeValue.
     *
     * @return string
     */
    public function getAttributeValue()
    {
        return $this->attribute_value;
    }

    /**
     * Set sort.
     *
     * @param string $sort
     *
     * @return PointsmallItemsAttributeValues
     */
    public function setSort($sort)
    {
        $this->sort = $sort;

        return $this;
    }

    /**
     * Get sort.
     *
     * @return string
     */
    public function getSort()
    {
        return $this->sort;
    }

    /**
     * Set imageUrl.
     *
     * @param string|null $imageUrl
     *
     * @return PointsmallItemsAttributeValues
     */
    public function setImageUrl($imageUrl = null)
    {
        $this->image_url = $imageUrl;

        return $this;
    }

    /**
     * Get imageUrl.
     *
     * @return string|null
     */
    public function getImageUrl()
    {
        return $this->image_url;
    }

    /**
     * Set created.
     *
     * @param int $created
     *
     * @return PointsmallItemsAttributeValues
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
     * @param int|null $updated
     *
     * @return PointsmallItemsAttributeValues
     */
    public function setUpdated($updated = null)
    {
        $this->updated = $updated;

        return $this;
    }

    /**
     * Get updated.
     *
     * @return int|null
     */
    public function getUpdated()
    {
        return $this->updated;
    }
}
