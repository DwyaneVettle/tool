<?php

namespace KaquanBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * MemberCardGrade(会员卡等级表)
 *
 * @ORM\Table(name="membercard_grade", options={"comment":"会员卡等级表"}, indexes={
 *    @ORM\Index(name="idx_companyid", columns={"company_id"})
 * })
 * @ORM\Entity(repositoryClass="KaquanBundle\Repositories\MembercardGradeRepository")
 */
class MemberCardGrade
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="grade_id", type="bigint", options={"comment":"等级ID"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $grade_id;

    /**
     * @var string
     *
     * @ORM\Column(name="company_id", type="string", options={"comment":"公司ID"})
     */
    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="grade_name", type="string", options={"comment":"等级名称"})
     */
    private $grade_name;

    /**
     * @var boolean
     *
     * @ORM\Column(name="default_grade", type="boolean", options={"comment":"是否默认等级", "default":false})
     */
    private $default_grade;

    /**
     * @var json_array
     *
     * @ORM\Column(name="background_pic_url", nullable=true, type="string", length=1024, options={"comment":"商家自定义会员卡背景图"})
     */
    private $background_pic_url;

    /**
     * @var json_array
     *
     * @ORM\Column(name="promotion_condition", nullable=true, type="json_array", options={"comment":"升级条件"})
     */
    private $promotion_condition;

    /**
     * @var json_array
     *
     * @ORM\Column(name="privileges", nullable=true, type="json_array", options={"comment":"会员权益"})
     */
    private $privileges;

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
     * @ORM\Column(type="integer")
     */
    protected $updated;

    /**
     * @var string
     *
     * @ORM\Column(name="third_data", nullable=true, type="string", options={"comment":"第三方数据"})
     */
    private $third_data;

    /**
     * @var string
     *
     * @ORM\Column(name="external_id", nullable=false, type="string", length=50, options={"comment":"外部唯一标识，外部调用方自定义的值", "default":""})
     */
    private $external_id;

    /**
     * Get gradeId
     *
     * @return integer
     */
    public function getGradeId()
    {
        return $this->grade_id;
    }

    /**
     * Set companyId
     *
     * @param string $companyId
     *
     * @return MemberCardGrade
     */
    public function setCompanyId($companyId)
    {
        $this->company_id = $companyId;

        return $this;
    }

    /**
     * Get companyId
     *
     * @return string
     */
    public function getCompanyId()
    {
        return $this->company_id;
    }

    /**
     * Set gradeName
     *
     * @param string $gradeName
     *
     * @return MemberCardGrade
     */
    public function setGradeName($gradeName)
    {
        $this->grade_name = $gradeName;

        return $this;
    }

    /**
     * Get gradeName
     *
     * @return string
     */
    public function getGradeName()
    {
        return $this->grade_name;
    }

    /**
     * Set defaultGrade
     *
     * @param boolean $defaultGrade
     *
     * @return MemberCardGrade
     */
    public function setDefaultGrade($defaultGrade)
    {
        $this->default_grade = $defaultGrade;

        return $this;
    }

    /**
     * Get defaultGrade
     *
     * @return boolean
     */
    public function getDefaultGrade()
    {
        return $this->default_grade;
    }

    /**
     * Set backgroundPicUrl
     *
     * @param string $backgroundPicUrl
     *
     * @return MemberCardGrade
     */
    public function setBackgroundPicUrl($backgroundPicUrl)
    {
        $this->background_pic_url = $backgroundPicUrl;

        return $this;
    }

    /**
     * Get backgroundPicUrl
     *
     * @return string
     */
    public function getBackgroundPicUrl()
    {
        return $this->background_pic_url;
    }

    /**
     * Set promotionCondition
     *
     * @param array $promotionCondition
     *
     * @return MemberCardGrade
     */
    public function setPromotionCondition($promotionCondition)
    {
        $this->promotion_condition = $promotionCondition;

        return $this;
    }

    /**
     * Get promotionCondition
     *
     * @return array
     */
    public function getPromotionCondition()
    {
        return $this->promotion_condition;
    }

    /**
     * Set privileges
     *
     * @param array $privileges
     *
     * @return MemberCardGrade
     */
    public function setPrivileges($privileges)
    {
        $this->privileges = $privileges;

        return $this;
    }

    /**
     * Get privileges
     *
     * @return array
     */
    public function getPrivileges()
    {
        return $this->privileges;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return MemberCardGrade
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
     * @return MemberCardGrade
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
     * Set thirdData.
     *
     * @param string|null $thirdData
     *
     * @return MembersAddress
     */
    public function setThirdData($thirdData = null)
    {
        $this->third_data = $thirdData;

        return $this;
    }

    /**
     * Get thirdData.
     *
     * @return string|null
     */
    public function getThirdData()
    {
        return $this->third_data;
    }

    /**
     * Set externalId.
     *
     * @param string $externalId
     *
     * @return MemberCardGrade
     */
    public function setExternalId($externalId)
    {
        $this->external_id = $externalId;

        return $this;
    }

    /**
     * Get externalId.
     *
     * @return string
     */
    public function getExternalId()
    {
        return $this->external_id;
    }
}
