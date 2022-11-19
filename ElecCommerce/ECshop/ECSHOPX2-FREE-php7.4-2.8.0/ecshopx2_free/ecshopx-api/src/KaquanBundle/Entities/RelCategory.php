<?php

namespace KaquanBundle\Entities;

use Doctrine\ORM\Mapping as ORM;

/**
 * RelItems 卡券关联分类表
 *
 * @ORM\Table(name="kaquan_rel_category", options={"comment":"卡券关联分类表"}, indexes={
 *    @ORM\Index(name="idx_card_id", columns={"card_id"}),
 *    @ORM\Index(name="idx_company_id", columns={"company_id"}),
 * })
 * @ORM\Entity(repositoryClass="KaquanBundle\Repositories\DiscountRelCategoryRepository")
 */
class RelCategory
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="category_id", type="bigint", length=64, options={"comment":"分类id"})
     */
    private $category_id;

    /**
     * @var int
     *
     * @ORM\Id
     * @ORM\Column(name="card_id", type="bigint", length=64, options={"comment":"卡券id"})
     */
    private $card_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", nullable=true, options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="category_level", type="integer", options={"comment":"分类等级"})
     */
    private $category_level = 0;

    /**
     * Set categoryId.
     *
     * @param int $categoryId
     *
     * @return RelCategory
     */
    public function setCategoryId($categoryId)
    {
        $this->category_id = $categoryId;

        return $this;
    }

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
     * Set cardId.
     *
     * @param int $cardId
     *
     * @return RelCategory
     */
    public function setCardId($cardId)
    {
        $this->card_id = $cardId;

        return $this;
    }

    /**
     * Get cardId.
     *
     * @return int
     */
    public function getCardId()
    {
        return $this->card_id;
    }

    /**
     * Set companyId.
     *
     * @param int|null $companyId
     *
     * @return RelCategory
     */
    public function setCompanyId($companyId = null)
    {
        $this->company_id = $companyId;

        return $this;
    }

    /**
     * Get companyId.
     *
     * @return int|null
     */
    public function getCompanyId()
    {
        return $this->company_id;
    }

    /**
     * Set categoryLevel.
     *
     * @param int $categoryLevel
     *
     * @return RelCategory
     */
    public function setCategoryLevel($categoryLevel)
    {
        $this->category_level = $categoryLevel;

        return $this;
    }

    /**
     * Get categoryLevel.
     *
     * @return int
     */
    public function getCategoryLevel()
    {
        return $this->category_level;
    }
}
