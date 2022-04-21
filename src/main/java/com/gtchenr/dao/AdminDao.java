package com.gtchenr.dao;

import com.gtchenr.pojo.Role;
import com.gtchenr.pojo.User;

public interface AdminDao extends UserDao {

    /**
     * 修改权限
     *
     * @return
     */
    int updatePermission(User user, Role role);

    /**
     * 判断用户是否存在
     *
     * @param user
     * @return
     */
    boolean userExist(User user);

    /**
     * 检查用户的权限
     *
     * @param user
     * @return
     */
    Role checkRole(User user);

    /**
     * 封禁管理员权限，把其转成普通用户权限
     *
     * @param user
     * @return
     */
    int denyAdminPermission(User user);

    int denyNormalPermission(User user);
}
