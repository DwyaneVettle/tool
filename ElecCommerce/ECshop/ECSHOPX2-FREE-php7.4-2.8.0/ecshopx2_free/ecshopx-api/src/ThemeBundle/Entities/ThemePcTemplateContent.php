<?php

namespace ThemeBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * theme_pc_template_content pc页面装修组件内容
 *
 * @ORM\Table(name="theme_pc_template_content", options={"comment":"pc页面装修组件内容"},
 * indexes={
 *         @ORM\Index(name="idx_company_id", columns={"company_id"}),
 *     },)
 * @ORM\Entity(repositoryClass="ThemeBundle\Repositories\ThemePcTemplateContentRepository")
 */
class ThemePcTemplateContent
{
    /**
     * @var integer
     *
     * @ORM\Column(name="theme_pc_template_content_id", type="bigint")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $theme_pc_template_content_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint")
     */
    private $company_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="theme_pc_template_id", type="bigint")
     */
    private $theme_pc_template_id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=20, options={"comment":"配置名称"})
     */
    private $name;

    /**
     * @var string
     *
     * @ORM\Column(name="params", type="text", options={"comment":"配置参数"})
     */
    private $params;

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
     * Get themePcTemplateContentId.
     *
     * @return int
     */
    public function getThemePcTemplateContentId()
    {
        return $this->theme_pc_template_content_id;
    }

    /**
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return ThemePcTemplateContent
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
     * Set themePcTemplateId.
     *
     * @param int $themePcTemplateId
     *
     * @return ThemePcTemplateContent
     */
    public function setThemePcTemplateId($themePcTemplateId)
    {
        $this->theme_pc_template_id = $themePcTemplateId;

        return $this;
    }

    /**
     * Get themePcTemplateId.
     *
     * @return int
     */
    public function getThemePcTemplateId()
    {
        return $this->theme_pc_template_id;
    }

    /**
     * Set name.
     *
     * @param string $name
     *
     * @return ThemePcTemplateContent
     */
    public function setName($name)
    {
        $this->name = $name;

        return $this;
    }

    /**
     * Get name.
     *
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set params.
     *
     * @param string $params
     *
     * @return ThemePcTemplateContent
     */
    public function setParams($params)
    {
        $this->params = $params;

        return $this;
    }

    /**
     * Get params.
     *
     * @return string
     */
    public function getParams()
    {
        return $this->params;
    }

    /**
     * Set created.
     *
     * @param int $created
     *
     * @return ThemePcTemplateContent
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
     * @return ThemePcTemplateContent
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
