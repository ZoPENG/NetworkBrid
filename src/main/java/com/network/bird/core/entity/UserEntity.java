package com.network.bird.core.entity;

import com.network.bird.core.constant.UserStatus;
import com.network.bird.core.constant.UserType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 用户
 */
@Entity
@Table(name = "user")
public class UserEntity {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "studio.xiaoyun.core.dao.UuidIdentifierGenerator")
    @Column(length = 32)
    private String userId;

    /**
     * 用户名
     */
    @Column(unique=true,length = 50)
    private String name;

    /**
     * 登陆密码
     */
    @Column(length = 100, nullable = false)
    private String password;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Date createDate;

    /**
     * 邮箱
     */
    @Column(unique=true,length = 100)
    private String email;

    /**
     * 用户状态
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    /**
     * 用户类型
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;

    /**
     * 用户拥有的角色
     */
    @ManyToMany(targetEntity=RoleEntity.class)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<RoleEntity> roles;

    /**
     * 用户拥有的权限
     */
    @ManyToMany(targetEntity=PermissionEntity.class)
    @JoinTable(name = "user_permission",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "permissionId"))
    private Set<PermissionEntity> permissions;

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
