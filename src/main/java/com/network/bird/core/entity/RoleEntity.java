package com.network.bird.core.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 角色
 */
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "studio.xiaoyun.core.dao.UuidIdentifierGenerator")
    @Column(length = 32)
    private String roleId;

    /**
     * 角色名称
     */
    @Column(unique=true,length = 50, nullable = false)
    private String name;

    /**
     * 角色的描述
     */
    @Column
    private String description;

    @ManyToMany(targetEntity=PermissionEntity.class)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "permissionId"))
    private Set<PermissionEntity> permissions;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }
}
