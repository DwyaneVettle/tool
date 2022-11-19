<?php

namespace CompanysBundle\Entities;

use Doctrine\ORM\Mapping as ORM;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * Roles 员工角色表
 *
 * @ORM\Table(name="companys_roles", options={"comment":"员工角色表"}, indexes={@ORM\Index(name="idx_role_id", columns={"role_id"})}, indexes={@ORM\Index(name="idx_company_id", columns={"company_id"})})
 * @ORM\Entity(repositoryClass="CompanysBundle\Repositories\RolesRepository")
 */
class Roles
{
    /**
     * @var integer
     *
     * @ORM\Column(name="role_id", type="bigint", options={"comment":"角色id"})
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $role_id;

    /**
     * @var integer
     *
     * @ORM\Column(name="company_id", type="bigint", options={"comment":"公司id"})
     *
     */
    private $company_id;

    /**
     * @var string
     *
     * @ORM\Column(name="role_name", type="string", length=32, options={"comment":"角色名称"})
     */
    private $role_name;

    /**
     * @var string
     *
     * @ORM\Column(name="role_source", type="string", length=32, options={"comment":"角色平台来源,platform: 平台角色，distributor:店铺管理角色", "default":"platform"})
     */
    private $role_source = 'platform';

    /**
     * @var integer
     *
     * @ORM\Column(name="permission", type="text", options={"comment":"权限,json数据"})
     */
    private $permission;

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
     * Get roleId
     *
     * @return integer
     */
    public function getRoleId()
    {
        return $this->role_id;
    }

    /**
     * Set companyId
     *
     * @param integer $companyId
     *
     * @return Roles
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
     * Set roleName
     *
     * @param string $roleName
     *
     * @return Roles
     */
    public function setRoleName($roleName)
    {
        $this->role_name = $roleName;

        return $this;
    }

    /**
     * Get roleName
     *
     * @return string
     */
    public function getRoleName()
    {
        return $this->role_name;
    }

    /**
     * Set permission
     *
     * @param string $permission
     *
     * @return Roles
     */
    public function setPermission($permission)
    {
        $this->permission = $permission;

        return $this;
    }

    /**
     * Get permission
     *
     * @return string
     */
    public function getPermission()
    {
        return $this->permission;
    }

    /**
     * Set created
     *
     * @param integer $created
     *
     * @return Roles
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
     * @return Roles
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
     * Set roleSource
     *
     * @param string $roleSource
     *
     * @return Roles
     */
    public function setRoleSource($roleSource)
    {
        $this->role_source = $roleSource;

        return $this;
    }

    /**
     * Get roleSource
     *
     * @return string
     */
    public function getRoleSource()
    {
        return $this->role_source;
    }
}
