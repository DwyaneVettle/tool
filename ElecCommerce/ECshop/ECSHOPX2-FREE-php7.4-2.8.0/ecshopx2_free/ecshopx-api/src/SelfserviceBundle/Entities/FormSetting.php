<?php

namespace SelfserviceBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * FormSetting (自助表单配置)
 *
 * @ORM\Table(name="selfservice_form_setting", options={"comment"="自助表单配置"})
 * @ORM\Entity(repositoryClass="SelfserviceBundle\Repositories\FormSettingRepository")
 */
class FormSetting
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint",options={"comment":"id"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint",options={"comment":"公司id"})
     */
    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="field_title", type="string", options={"comment":"表单项标题(中文描述)"})
     */
    private $field_title;

    /**
     * @var integer
     *
     * @ORM\Column(name="field_name", type="string", options={"comment":"表单项英文名称(英文或拼音描述),唯一标示"})
     */
    private $field_name;

    /**
     * @var string
     *
     * @ORM\Column(name="form_element", nullable=true, type="string", options={"comment":"表单元素,text:文本,textarea:文本域,select:选择框,radio:单选,checkbox:多选框,date:日期选择,time:时间选择,area:地区地址选择, image:图片上传,number:纯数字",})
     */
    private $form_element = "text";

    /**
     * @var string
     *
     * @ORM\Column(name="image_url", nullable=true, type="string", options={"comment":"元素配图"})
     */
    private $image_url = "";

    /**
     * @var integer
     *
     * @ORM\Column(name="status", type="integer", options={"comment":"状态;1:有效，2:弃用", "default": 1})
     */
    private $status = 1;

    /**
     * @var integer
     *
     * @ORM\Column(name="sort", type="integer", options={"comment":"排序，数字越大越靠前", "default": 1})
     */
    private $sort = 1;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_required", type="boolean", options={"comment":"是否必填", "default": false})
     */
    private $is_required = false;

    /**
     * @var string
     * select 选择框 radio 单选框 checkbox 多项框
     *
     * @ORM\Column(name="options", type="text", nullable=true,  options={"comment":"表单元素为选择类时选择项（json）当form_element in (select,  radio, checkbox)时，此项必填"})
     */
    private $options;

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
     * Set id
     *
     * @param integer $id
     *
     * @return FormSetting
     */
    public function setId($id)
    {
        $this->id = $id;

        return $this;
    }

    /**
     * Get id
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return FormSetting
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
     * Set fieldTitle
     *
     * @param string $fieldTitle
     *
     * @return FormSetting
     */
    public function setFieldTitle($fieldTitle)
    {
        $this->field_title = $fieldTitle;

        return $this;
    }

    /**
     * Get fieldTitle
     *
     * @return string
     */
    public function getFieldTitle()
    {
        return $this->field_title;
    }

    /**
     * Set fieldName
     *
     * @param string $fieldName
     *
     * @return FormSetting
     */
    public function setFieldName($fieldName)
    {
        $this->field_name = $fieldName;

        return $this;
    }

    /**
     * Get fieldName
     *
     * @return string
     */
    public function getFieldName()
    {
        return $this->field_name;
    }

    /**
     * Set formElement
     *
     * @param string $formElement
     *
     * @return FormSetting
     */
    public function setFormElement($formElement)
    {
        $this->form_element = $formElement;

        return $this;
    }

    /**
     * Get formElement
     *
     * @return string
     */
    public function getFormElement()
    {
        return $this->form_element;
    }

    /**
     * Set options
     *
     * @param string $options
     *
     * @return FormSetting
     */
    public function setOptions($options)
    {
        $this->options = $options;

        return $this;
    }

    /**
     * Get options
     *
     * @return string
     */
    public function getOptions()
    {
        return $this->options;
    }

    /**
     * Set imageUrl
     *
     * @param string $imageUrl
     *
     * @return FormSetting
     */
    public function setImageUrl($imageUrl)
    {
        $this->image_url = $imageUrl;

        return $this;
    }

    /**
     * Get imageUrl
     *
     * @return string
     */
    public function getImageUrl()
    {
        return $this->image_url;
    }

    /**
     * Set status
     *
     * @param integer $status
     *
     * @return FormSetting
     */
    public function setStatus($status)
    {
        $this->status = $status;

        return $this;
    }

    /**
     * Get status
     *
     * @return integer
     */
    public function getStatus()
    {
        return $this->status;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return FormSetting
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
     * @return FormSetting
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
     * Set sort
     *
     * @param integer $sort
     *
     * @return FormSetting
     */
    public function setSort($sort)
    {
        $this->sort = $sort;

        return $this;
    }

    /**
     * Get sort
     *
     * @return integer
     */
    public function getSort()
    {
        return $this->sort;
    }

    /**
     * Set isRequired
     *
     * @param boolean $isRequired
     *
     * @return FormSetting
     */
    public function setIsRequired($isRequired)
    {
        $this->is_required = $isRequired;

        return $this;
    }

    /**
     * Get isRequired
     *
     * @return boolean
     */
    public function getIsRequired()
    {
        return $this->is_required;
    }
}
