package com.gtchenr.mapper;

import com.gtchenr.pojo.Role;
import com.gtchenr.utils.MybatisUtil;
import org.junit.Test;

import java.util.List;

public class RoleMapperTest {

    RoleMapper roleMapper = MybatisUtil.getSqlSession().getMapper(RoleMapper.class);

    @Test
    public void test01(){
        List<Role> roles = roleMapper.queryAll();
        for (Role r:roles) {
            System.out.println(r);
        }
    }

    @Test
    public void test02(){
        Role role = new Role(Role.NORMAL_USER,Role.HAVE_PERMISSION);
        roleMapper.add(role);
        MybatisUtil.getSqlSession().commit();
        System.out.println("roleId="+role.getRoleId());
    }

    @Test
    public void testQueryByRoleId(){
        Role role = roleMapper.queryByRoleId(1);
        System.out.println(role);
    }

    @Test
    public void deleteTest(){
        int update = roleMapper.delete(10);
        MybatisUtil.getSqlSession().commit();
        System.out.println(update);
    }
}
