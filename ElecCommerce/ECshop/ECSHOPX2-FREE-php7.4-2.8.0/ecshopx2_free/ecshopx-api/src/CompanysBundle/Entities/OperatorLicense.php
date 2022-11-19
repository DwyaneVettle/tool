<?php

namespace CompanysBundle\Entities;

use Doctrine\ORM\Mapping as ORM;

/**
 * OperatorLicense 商户许可协议
 *
 * @ORM\Table(name="operator_license", options={"comment":"商户许可协议表"})
 * @ORM\Entity(repositoryClass="CompanysBundle\Repositories\OperatorLicenseRepository")
 */
class OperatorLicense
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint", options={"comment":"协议id"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", options={"comment":"许可协议类型: app"})
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="title", type="string", options={"comment":"许可协议标题"})
     */
    private $title;

    /**
     * @var string
     *
     * @ORM\Column(name="content", type="text", options={"comment":"许可协议内容"})
     */
    private $content;

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
     * Set title.
     *
     * @param string $title
     *
     * @return OperatorLicense
     */
    public function setTitle($title)
    {
        $this->title = $title;

        return $this;
    }

    /**
     * Get title.
     *
     * @return string
     */
    public function getTitle()
    {
        return $this->title;
    }

    /**
     * Set content.
     *
     * @param string $content
     *
     * @return OperatorLicense
     */
    public function setContent($content)
    {
        $this->content = $content;

        return $this;
    }

    /**
     * Get content.
     *
     * @return string
     */
    public function getContent()
    {
        return $this->content;
    }

    /**
     * Set type.
     *
     * @param string $type
     *
     * @return OperatorLicense
     */
    public function setType($type)
    {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type.
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }
}
