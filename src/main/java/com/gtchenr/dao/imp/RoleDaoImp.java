package com.gtchenr.dao.imp;

import com.gtchenr.dao.RoleDao;
import com.gtchenr.pojo.Role;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


public class RoleDaoImp extends JdbcDaoSupport implements RoleDao {
    @Override
    public int add(Role role) {
//        String sql = "insert into Role(role_name,role_statue) value(?,?)";
//        return getJdbcTemplate().update(sql,role.getRoleName(),role.getStatue());
        return 1;
    }
}
