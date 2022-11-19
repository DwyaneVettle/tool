<?php

namespace PointsmallBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * PointsmallItemsCategory 积分商品属性表
 *
 * @ORM\Table(name="pointsmall_items_attributes",options={"comment"="积分商品属性表"}, indexes={
 *    @ORM\Index(name="ix_company_id", columns={"company_id"}),
 *    @ORM\Index(name="ix_attribute_type", columns={"attribute_type"}),
 *    @ORM\Index(name="ix_attribute_name", columns={"attribute_name"}),
 * })
 * @ORM\Entity(repositoryClass="PointsmallBundle\Repositories\PointsmallItemsAttributesRepository")
 */
class PointsmallItemsAttributes
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="attribute_id", type="bigint", options={"comment":"商品属性id"})
     * @ORM\GeneratedValue(strategy="AUTO")
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
     * @ORM\Column(name="attribute_type", type="string", length=15, options={"comment":"商品属性类型 unit单位，brand品牌，item_params商品参数, item_spec规格"})
     */
    private $attribute_type;

    /**
     * @var string
     *
     * @ORM\Column(name="attribute_name", type="string", options={"comment":"商品属性名称"})
     */
    private $attribute_name;

    /**
     * @var string
     *
     * @ORM\Column(name="attribute_memo", nullable=true, type="string", options={"comment":"商品属性备注"})
     */
    private $attribute_memo;

    /**
     * @var string
     *
     * @ORM\Column(name="attribute_sort", type="string", length=15, options={"comment":"商品属性排序，越大越在前"})
     */
    private $attribute_sort;

    /**
     * @var string
     *
     * @ORM\Column(name="is_show", type="string", length=15, options={"comment":"是否用于筛选"})
     */
    private $is_show;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_image", type="string", options={"comment":"属性是否需要配置图片"})
     */
    private $is_image;

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
     * @return PointsmallItemsAttributes
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
     * Set attributeType.
     *
     * @param string $attributeType
     *
     * @return PointsmallItemsAttributes
     */
    public function setAttributeType($attributeType)
    {
        $this->attribute_type = $attributeType;

        return $this;
    }

    /**
     * Get attributeType.
     *
     * @return string
     */
    public function getAttributeType()
    {
        return $this->attribute_type;
    }

    /**
     * Set attributeName.
     *
     * @param string $attributeName
     *
     * @return PointsmallItemsAttributes
     */
    public function setAttributeName($attributeName)
    {
        $this->attribute_name = $attributeName;

        return $this;
    }

    /**
     * Get attributeName.
     *
     * @return string
     */
    public function getAttributeName()
    {
        return $this->attribute_name;
    }

    /**
     * Set attributeMemo.
     *
     * @param string|null $attributeMemo
     *
     * @return PointsmallItemsAttributes
     */
    public function setAttributeMemo($attributeMemo = null)
    {
        $this->attribute_memo = $attributeMemo;

        return $this;
    }

    /**
     * Get attributeMemo.
     *
     * @return string|null
     */
    public function getAttributeMemo()
    {
        return $this->attribute_memo;
    }

    /**
     * Set attributeSort.
     *
     * @param string $attributeSort
     *
     * @return PointsmallItemsAttributes
     */
    public function setAttributeSort($attributeSort)
    {
        $this->attribute_sort = $attributeSort;

        return $this;
    }

    /**
     * Get attributeSort.
     *
     * @return string
     */
    public function getAttributeSort()
    {
        return $this->attribute_sort;
    }

    /**
     * Set isShow.
     *
     * @param string $isShow
     *
     * @return PointsmallItemsAttributes
     */
    public function setIsShow($isShow)
    {
        $this->is_show = $isShow;

        return $this;
    }

    /**
     * Get isShow.
     *
     * @return string
     */
    public function getIsShow()
    {
        return $this->is_show;
    }

    /**
     * Set isImage.
     *
     * @param string $isImage
     *
     * @return PointsmallItemsAttributes
     */
    public function setIsImage($isImage)
    {
        $this->is_image = $isImage;

        return $this;
    }

    /**
     * Get isImage.
     *
     * @return string
     */
    public function getIsImage()
    {
        return $this->is_image;
    }

    /**
     * Set imageUrl.
     *
     * @param string|null $imageUrl
     *
     * @return PointsmallItemsAttributes
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
     * @return PointsmallItemsAttributes
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
     * @return PointsmallItemsAttributes
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
