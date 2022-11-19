<?php

namespace AdaPayBundle\Entities;

use Doctrine\ORM\Mapping as ORM;

/**
 * AdapayRegionsThird 省市区编码(六位码或九位码)
 *
 * @ORM\Table(name="adapay_regions_third", options={"comment":"省市区编码(六位码或九位码)"},
 *     indexes={
 *         @ORM\Index(name="idx_area_name", columns={"area_name"}),
 *         @ORM\Index(name="idx_area_code", columns={"area_code"})
 *     },
 * )
 * @ORM\Entity(repositoryClass="AdaPayBundle\Repositories\AdapayRegionsThirdRepository")
 */
class AdapayRegionsThird
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\Column(name="id", type="bigint", options={"comment":"ID"})
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="area_name", type="string", length=50, options={"comment":"名称"})
     */
    private $area_name;

    /**
     * @var integer
     *
     * @ORM\Column(name="pid", type="bigint", options={"comment":"父级ID"})
     */
    private $pid;

    /**
     * @var string
     *
     * @ORM\Column(name="area_code", type="string", length=50, options={"comment":"编码"})
     */
    private $area_code;

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
     * Set areaName.
     *
     * @param string $areaName
     *
     * @return AdapayRegions
     */
    public function setAreaName($areaName)
    {
        $this->area_name = $areaName;

        return $this;
    }

    /**
     * Get areaName.
     *
     * @return string
     */
    public function getAreaName()
    {
        return $this->area_name;
    }

    /**
     * Set areaCode.
     *
     * @param string $areaCode
     *
     * @return AdapayRegions
     */
    public function setAreaCode($areaCode)
    {
        $this->area_code = $areaCode;

        return $this;
    }

    /**
     * Get areaCode.
     *
     * @return string
     */
    public function getAreaCode()
    {
        return $this->area_code;
    }


    /**
     * Set pid.
     *
     * @param int $pid
     *
     * @return AdapayRegions
     */
    public function setPid($pid)
    {
        $this->pid = $pid;

        return $this;
    }

    /**
     * Get pid.
     *
     * @return int
     */
    public function getPid()
    {
        return $this->pid;
    }
}
