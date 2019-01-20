package com.itdreamworks.boilermanage.entity;

import java.util.List;

public class Role {
    private Integer roleId;
    private String roleName,roleDesc;
    private List<RoleResource> roleResourceList;
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<RoleResource> getRoleResourceList() {
        return roleResourceList;
    }

    public void setRoleResourceList(List<RoleResource> roleResourceList) {
        this.roleResourceList = roleResourceList;
    }
}
