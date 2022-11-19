<?php

namespace KaquanBundle\Entities;

use Doctrine\ORM\Mapping as ORM;

/**
 * RelTags 卡券关联标签表
 *
 * @ORM\Table(name="kaquan_rel_tags", options={"comment":"卡券关联标签表"}, indexes={
 *    @ORM\Index(name="idx_card_id", columns={"card_id"}),
 *    @ORM\Index(name="idx_company_id", columns={"company_id"}),
 * })
 * @ORM\Entity(repositoryClass="KaquanBundle\Repositories\DiscountRelTagsRepository")
 */
class RelTags
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="tag_id", type="bigint", length=64, options={"comment":"标签id"})
     */
    private $tag_id;

    /**
     * @var bigint
     *
     * @ORM\Id
     * @ORM\Column(name="card_id", type="bigint", options={"comment":"优惠券id"})
     */
    private $card_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", nullable=true, options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * Set tagId.
     *
     * @param int $tagId
     *
     * @return RelTags
     */
    public function setTagId($tagId)
    {
        $this->tag_id = $tagId;

        return $this;
    }

    /**
     * Get tagId.
     *
     * @return int
     */
    public function getTagId()
    {
        return $this->tag_id;
    }

    /**
     * Set cardId.
     *
     * @param int $cardId
     *
     * @return RelTags
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
     * @return RelTags
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
}
