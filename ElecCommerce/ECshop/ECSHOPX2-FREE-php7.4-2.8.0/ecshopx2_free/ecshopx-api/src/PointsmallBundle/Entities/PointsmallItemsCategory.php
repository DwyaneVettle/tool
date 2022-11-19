<?php

namespace PointsmallBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * PointsmallItemsCategory 积分商品分类表
 *
 * @ORM\Table(name="pointsmall_items_category", options={"comment"="积分商品分类表"}, indexes={
 *    @ORM\Index(name="ix_company_id", columns={"company_id"}),
 *    @ORM\Index(name="ix_parent_id", columns={"parent_id"}),
 *    @ORM\Index(name="ix_is_main_category", columns={"is_main_category"}),
 * })
 * @ORM\Entity(repositoryClass="PointsmallBundle\Repositories\PointsmallItemsCategoryRepository")
 */
class PointsmallItemsCategory
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="category_id", type="bigint", options={"comment":"商品分类id"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $category_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司ID"})
     */
    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="category_name", type="string", length=50, options={"comment":"分类名称"})
     */
    private $category_name;

    /**
     * @var integer
     *
     * @ORM\Column(name="parent_id", type="bigint", options={"comment":"父级id, 0为顶级", "default":"0"})
     */
    private $parent_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="category_level", nullable=true, type="integer", options={"comment":"商品分类等级", "default":1})
     */
    private $category_level;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_main_category", nullable=true, type="boolean", options={"comment":"是否为商品主类目", "default":false})
     */
    private $is_main_category;

    /**
     * @var string
     *
     * @ORM\Column(name="path", type="string", nullable=true, length=255, options={"comment":"路径", "default":"0"})
     */
    private $path;

    /**
     * @var integer
     *
     * @ORM\Column(name="sort", nullable=true, type="bigint", options={"comment":"排序", "default":"0"})
     */
    private $sort;

    /**
     * @var string
     *
     * @ORM\Column(name="goods_params", nullable=true, type="text", options={"comment":"商品参数"})
     */
    private $goods_params;

    /**
     * @var string
     *
     * @ORM\Column(name="goods_spec", nullable=true, type="text", options={"comment":"商品规格"})
     */
    private $goods_spec;

    /**
     * @var integer
     *
     * @ORM\Column(name="image_url", nullable=true, type="text", options={"comment":"分类图片链接"})
     */
    private $image_url;


    /**
     * @var string
     *
     * @ORM\Column(name="crossborder_tax_rate", type="string", length=10, nullable=true, options={"comment":"跨境税率，百分比，小数点2位"})
     */
    private $crossborder_tax_rate;

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
     * Get categoryId.
     *
     * @return int
     */
    public function getCategoryId()
    {
        return $this->category_id;
    }

    /**
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return PointsmallItemsCategory
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
     * Set categoryName.
     *
     * @param string $categoryName
     *
     * @return PointsmallItemsCategory
     */
    public function setCategoryName($categoryName)
    {
        $this->category_name = $categoryName;

        return $this;
    }

    /**
     * Get categoryName.
     *
     * @return string
     */
    public function getCategoryName()
    {
        return $this->category_name;
    }

    /**
     * Set parentId.
     *
     * @param int $parentId
     *
     * @return PointsmallItemsCategory
     */
    public function setParentId($parentId)
    {
        $this->parent_id = $parentId;

        return $this;
    }

    /**
     * Get parentId.
     *
     * @return int
     */
    public function getParentId()
    {
        return $this->parent_id;
    }

    /**
     * Set categoryLevel.
     *
     * @param int|null $categoryLevel
     *
     * @return PointsmallItemsCategory
     */
    public function setCategoryLevel($categoryLevel = null)
    {
        $this->category_level = $categoryLevel;

        return $this;
    }

    /**
     * Get categoryLevel.
     *
     * @return int|null
     */
    public function getCategoryLevel()
    {
        return $this->category_level;
    }

    /**
     * Set isMainCategory.
     *
     * @param bool|null $isMainCategory
     *
     * @return PointsmallItemsCategory
     */
    public function setIsMainCategory($isMainCategory = null)
    {
        $this->is_main_category = $isMainCategory;

        return $this;
    }

    /**
     * Get isMainCategory.
     *
     * @return bool|null
     */
    public function getIsMainCategory()
    {
        return $this->is_main_category;
    }

    /**
     * Set path.
     *
     * @param string|null $path
     *
     * @return PointsmallItemsCategory
     */
    public function setPath($path = null)
    {
        $this->path = $path;

        return $this;
    }

    /**
     * Get path.
     *
     * @return string|null
     */
    public function getPath()
    {
        return $this->path;
    }

    /**
     * Set sort.
     *
     * @param int|null $sort
     *
     * @return PointsmallItemsCategory
     */
    public function setSort($sort = null)
    {
        $this->sort = $sort;

        return $this;
    }

    /**
     * Get sort.
     *
     * @return int|null
     */
    public function getSort()
    {
        return $this->sort;
    }

    /**
     * Set goodsParams.
     *
     * @param string|null $goodsParams
     *
     * @return PointsmallItemsCategory
     */
    public function setGoodsParams($goodsParams = null)
    {
        $this->goods_params = $goodsParams;

        return $this;
    }

    /**
     * Get goodsParams.
     *
     * @return string|null
     */
    public function getGoodsParams()
    {
        return $this->goods_params;
    }

    /**
     * Set goodsSpec.
     *
     * @param string|null $goodsSpec
     *
     * @return PointsmallItemsCategory
     */
    public function setGoodsSpec($goodsSpec = null)
    {
        $this->goods_spec = $goodsSpec;

        return $this;
    }

    /**
     * Get goodsSpec.
     *
     * @return string|null
     */
    public function getGoodsSpec()
    {
        return $this->goods_spec;
    }

    /**
     * Set imageUrl.
     *
     * @param string|null $imageUrl
     *
     * @return PointsmallItemsCategory
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
     * Set crossborderTaxRate.
     *
     * @param string|null $crossborderTaxRate
     *
     * @return PointsmallItemsCategory
     */
    public function setCrossborderTaxRate($crossborderTaxRate = null)
    {
        $this->crossborder_tax_rate = $crossborderTaxRate;

        return $this;
    }

    /**
     * Get crossborderTaxRate.
     *
     * @return string|null
     */
    public function getCrossborderTaxRate()
    {
        return $this->crossborder_tax_rate;
    }

    /**
     * Set created.
     *
     * @param int $created
     *
     * @return PointsmallItemsCategory
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
     * @return PointsmallItemsCategory
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
