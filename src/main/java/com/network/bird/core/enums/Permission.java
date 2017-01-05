package com.network.bird.core.enums;

/**
 * 定义所有权限。
 * <p>命名规则：[类型]_[增、删、改、查]_[操作]</p>
 */
public enum  Permission {
    /**
     * 添加新用户
     */
    USER_CREATE(OperationType.USER,"添加新用户"),
    /**
     * 更新角色的权限
     */
    ROLE_UPDATE_PERMISSION(OperationType.ROLE,"更新角色的权限"),
    /**
     * 获得所有角色
     */
    ROLE_GET_ALL(OperationType.ROLE,"获得所有角色"),
    /**
     * 获得所有权限
     */
    PERMISSION_GET_ALL(OperationType.SECURITY,"获得所有权限"),
    /**
     * 根据角色id获得该角色的权限
     */
    PERMISSION_GET_BY_ROLEID(OperationType.SECURITY,"根据角色id获得该角色的权限"),
    /**
     * 获得所有意见反馈
     */
    FEEDBACK_GET_ALL(OperationType.MESSAGE,"获得所有意见反馈"),
    /**
     * 根据id删除意见反馈
     */
    FEEDBACK_DELETE_BY_ID(OperationType.MESSAGE,"根据id删除意见反馈");

    private String description;
    private OperationType type;
    Permission(OperationType type, String description){
        this.type = type;
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public OperationType getType() {
        return type;
    }
}

