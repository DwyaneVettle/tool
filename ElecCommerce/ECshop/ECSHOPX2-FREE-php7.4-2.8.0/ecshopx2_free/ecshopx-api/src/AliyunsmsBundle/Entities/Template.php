<?php

namespace AliyunsmsBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * Template 短信模板
 *
 * @ORM\Table(name="aliyunsms_template", options={"comment":"模板表"},
 *     indexes={
 *         @ORM\Index(name="idx_company_id", columns={"company_id"}),
 *     },
 * )
 * @ORM\Entity(repositoryClass="AliyunsmsBundle\Repositories\TemplateRepository")
 */
class Template
{
    /**
     * @var string
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint", options={"comment":"模板ID"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="template_type", type="string", options={"comment":"短信类型: 0：验证码;1：短信通知;2：推广短信;3：国际/港澳台消息"})
     */
    private $template_type;

    /**
     * @var string
     *
     * @ORM\Column(name="template_name", type="string", options={"comment":"模板名称"})
     */
    private $template_name;

    /**
     * @var string
     *
     * @ORM\Column(name="remark", type="string", options={"comment":"模板申请说明"})
     */
    private $remark;


    /**
     * @var text
     *
     * @ORM\Column(name="template_content", type="text", options={"comment":"模板内容"})
     */
    private $template_content;

    /**
     * @var integer
     *
     * @ORM\Column(name="scene_id", type="integer", options={"comment":"短信场景"})
     */
    private $scene_id;

    /**
     * @var string
     *
     * @ORM\Column(name="template_code", nullable=true, type="string", options={"comment":"模板编码"})
     */
    private $template_code;

    /**
     * @var string
     *
     * @ORM\Column(name="status", type="string", options={"comment":"审核状态:0-审核中;1-审核通过;2-审核失败"})
     */
    private $status = 0;

    /**
     * @var string
     *
     * @ORM\Column(name="reason", nullable=true, type="string", options={"comment":"审核备注"})
     */
    private $reason = '';

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
     * Get id.
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set companyId.
     *
     * @param int $companyId
     *
     * @return Template
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
     * Set templateType
     *
     * @param string $templateType
     *
     * @return Template
     */
    public function setTemplateType($templateType)
    {
        $this->template_type = $templateType;

        return $this;
    }

    /**
     * Get templateType.
     *
     * @return string
     */
    public function getTemplateType()
    {
        return $this->template_type;
    }

    /**
     * Set templateName.
     *
     * @param string $templateName
     *
     * @return Template
     */
    public function setTemplateName($templateName)
    {
        $this->template_name = $templateName;

        return $this;
    }

    /**
     * Get templateName.
     *
     * @return string
     */
    public function getTemplateName()
    {
        return $this->template_name;
    }

    /**
     * Set remark.
     *
     * @param string $remark
     *
     * @return Template
     */
    public function setRemark($remark)
    {
        $this->remark = $remark;

        return $this;
    }

    /**
     * Get remark.
     *
     * @return string
     */
    public function getRemark()
    {
        return $this->remark;
    }


    /**
     * Set templateContent.
     *
     * @param text $templateContent
     *
     * @return Template
     */
    public function setTemplateContent($templateContent)
    {
        $this->template_content = $templateContent;

        return $this;
    }

    /**
     * Get templateContent.
     *
     * @return text
     */
    public function getTemplateContent()
    {
        return $this->template_content;
    }

    /**
     * Set sceneId.
     *
     * @param integer $sceneId
     *
     * @return Template
     */
    public function setSceneId($sceneId)
    {
        $this->scene_id = $sceneId;

        return $this;
    }

    /**
     * Get sceneId.
     *
     * @return integer
     */
    public function getSceneId()
    {
        return $this->scene_id;
    }

    /**
     * Set templateCode.
     *
     * @param string $templateCode
     *
     * @return Template
     */
    public function setTemplateCode($templateCode)
    {
        $this->template_code = $templateCode;

        return $this;
    }

    /**
     * Get templateCode.
     *
     * @return string
     */
    public function getTemplateCode()
    {
        return $this->template_code;
    }
    /**
     * Set status.
     *
     * @param string $status
     *
     * @return Template
     */
    public function setStatus($status)
    {
        $this->status = $status;

        return $this;
    }

    /**
     * Get status.
     *
     * @return string
     */
    public function getStatus()
    {
        return $this->status;
    }

    /**
     * Get reason.
     *
     * @return string
     */
    public function getReason()
    {
        return $this->reason;
    }

    /**
     * Set reason.
     *
     * @param string $reason
     *
     * @return Template
     */
    public function setReason($reason)
    {
        $this->reason = $reason;

        return $this;
    }

    /**
     * Set created.
     *
     * @param int $created
     *
     * @return Template
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
     * @return Template
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
