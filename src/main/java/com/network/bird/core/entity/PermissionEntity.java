package com.network.bird.core.entity;

import com.network.bird.core.constant.OperationType;
import com.network.bird.core.constant.Permission;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 权限
 */
@Entity
@Table(name = "permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "studio.xiaoyun.core.dao.UuidIdentifierGenerator")
    @Column(length = 32)
    private String permissionId;
    /**
     * 权限名称
     */
    @Column(unique=true,nullable = false)
    @Enumerated(EnumType.STRING)
    private Permission name;
    /**
     * 权限类型
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationType type;
    /**
     * 描述
     */
    @Column(length = 200,nullable = false)
    private String description;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public Permission getName() {
        return name;
    }

    public void setName(Permission name) {
        this.name = name;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionEntity that = (PermissionEntity) o;
        return permissionId.equals(that.permissionId);
    }

    @Override
    public int hashCode() {
        return permissionId.hashCode();
    }
}
