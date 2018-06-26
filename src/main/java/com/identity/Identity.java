package com.identity;

/**
 * 权限列表
 */
public enum Identity {
    /**
     * 管理员
     */
    Administrator("Administrator"),
    /**
     * 用户
     */
    User("User"),
    /**
     * 游客
     */
    Guest("Guest");

    private String name;

    Identity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "name='" + name + '\'' +
                '}';
    }
}
