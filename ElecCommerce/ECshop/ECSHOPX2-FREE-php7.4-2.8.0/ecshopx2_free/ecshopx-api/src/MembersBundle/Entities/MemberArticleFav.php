<?php

namespace MembersBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * MemberArticleFav 会员心愿单收藏
 *
 * @ORM\Table(name="members_article_fav", options={"comment"="会员心愿单收藏"}, indexes={
 *    @ORM\Index(name="idx_company_id", columns={"company_id"}),
 *    @ORM\Index(name="idx_user_id",   columns={"user_id"})
 * })
 * @ORM\Entity(repositoryClass="MembersBundle\Repositories\MemberArticleFavRepository")
 */
class MemberArticleFav
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="fav_id", type="bigint", options={"comment"="收藏id"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $fav_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment"="公司id"})
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="user_id", type="bigint", options={"comment"="会员id"})
     */
    private $user_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="article_id", type="bigint", options={"comment"="文章ID"})
     */
    private $article_id;

    /**
     * @var \DateTime $created
     *
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(type="integer", columnDefinition="bigint NOT NULL")
     */
    protected $created;

    /**
     * Get favId
     *
     * @return integer
     */
    public function getFavId()
    {
        return $this->fav_id;
    }

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return MemberArticleFav
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
     * Set userId
     *
     * @param integer $userId
     *
     * @return MemberArticleFav
     */
    public function setUserId($userId)
    {
        $this->user_id = $userId;

        return $this;
    }

    /**
     * Get userId
     *
     * @return integer
     */
    public function getUserId()
    {
        return $this->user_id;
    }

    /**
     * Set articleId
     *
     * @param integer $articleId
     *
     * @return MemberArticleFav
     */
    public function setArticleId($articleId)
    {
        $this->article_id = $articleId;

        return $this;
    }

    /**
     * Get articleId
     *
     * @return integer
     */
    public function getArticleId()
    {
        return $this->article_id;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return MemberArticleFav
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
}
