<?php

namespace SuperAdminBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * ShopMenuRelType 菜单类型关联表
 *
 * @ORM\Table(name="shop_menu_rel_type", options={"comment"="菜单类型关联"}, indexes={
 *    @ORM\Index(name="ix_shopmenu_id", columns={"shopmenu_id"}),
 * })
 * @ORM\Entity(repositoryClass="SuperAdminBundle\Repositories\ShopMenuRelTypeRepository")
 */
class ShopMenuRelType
{
    /**
     * @var integer
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(name="rel_id", type="bigint", options={"comment":"关联ID"})
     */
    private $rel_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="shopmenu_id", type="bigint", options={"comment":"菜单id"})
     */
    private $shopmenu_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="menu_type", type="integer",  options={"comment":"菜单类型", "default":1})
     */
    private $menu_type;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="integer",  options={"comment":"公司ID"})
     */
    private $company_id;

    /**
      * @var \DateTime $created
      *
      * @Gedmo\Timestampable(on="create")
      * @ORM\Column(type="integer")
      */
    protected $created;


    /**
     * Get relId.
     *
     * @return int
     */
    public function getRelId()
    {
        return $this->rel_id;
    }

    /**
     * Set shopmenuId.
     *
     * @param int $shopmenuId
     *
     * @return ShopMenuRelType
     */
    public function setShopmenuId($shopmenuId)
    {
        $this->shopmenu_id = $shopmenuId;

        return $this;
    }

    /**
     * Get shopmenuId.
     *
     * @return int
     */
    public function getShopmenuId()
    {
        return $this->shopmenu_id;
    }

    /**
     * Set menuType.
     *
     * @param int $menuType
     *
     * @return ShopMenuRelType
     */
    public function setMenuType($menuType)
    {
        $this->menu_type = $menuType;

        return $this;
    }

    /**
     * Get menuType.
     *
     * @return int
     */
    public function getMenuType()
    {
        return $this->menu_type;
    }

    /**
     * Set created.
     *
     * @param int $created
     *
     * @return ShopMenuRelType
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
     * @return string
     */
    public function getCompanyId()
    {
        return $this->company_id;
    }

    /**
     * @param string $companyId
     */
    public function setCompanyId($companyId)
    {
        $this->company_id = $companyId;
    }
}
