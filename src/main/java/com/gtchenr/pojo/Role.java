package com.gtchenr.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

//@Component
public class Role {

    public static final String SUPER_ADMINISTRATION = "super_administrator";
    public static final String ADMINISTRATION = "administrator";
    public static final String NORMAL_USER = "normal_user";
    public static final Integer UN_PERMISSION = 0;
    public static final Integer HAVE_PERMISSION = 1;
//    @Value("1")
    private Integer roleId;
//    @Value("super_administrator")
    private String roleName;
    //    @Value("1")
    private Integer statue;

    public Role() {
    }

    public Role(String roleName, Integer statue) {
        this.roleName = roleName;
        this.statue = statue;
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", statue=" + statue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleName, role.roleName) && Objects.equals(statue, role.statue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, statue);
    }

}
