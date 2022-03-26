package com.gtchenr.mapper;

import com.gtchenr.pojo.Role;

import java.util.List;


public interface RoleMapper {

   /**
    * 查找使用用户的权限
    * @return
    */
   List<Role> queryAll();

   /**
    * 添加一个用户的权限
    * @param role
    * @return
    */
   int add(Role role);

   /**
    * 通过roleId查找权限
    * @param roleId
    * @return
    */
   Role queryByRoleId(Integer roleId);

   /**
    * 从数据库删除权限
    * @param roleId
    * @return
    */
   int delete(Integer roleId);
}
