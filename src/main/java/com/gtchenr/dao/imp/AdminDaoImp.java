package com.gtchenr.dao.imp;

import com.gtchenr.dao.AdminDao;
import com.gtchenr.pojo.Role;
import com.gtchenr.pojo.User;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AdminDaoImp extends JdbcDaoSupport implements AdminDao {

    @Override
    public int add(User user) {
        return 0;
    }


    @Override
    public int updatePermission(User user, Role role) {
        if (!userExist(user) || user == null)
            return -1;
        if (checkRole(user).equals(role))
            return -2;
        String sql = "update role r inner join user u on r.role_id=u.role_id set r.role_name=?,r.role_statue=? where u.role_id=?";
        return getJdbcTemplate().update(sql, new Object[]{role.getRoleName(), role.getStatue(), user.getUserId()});
    }

    @Override
    public boolean userExist(User user) {
        String sql = "select user_id from user where user_id=? or login_name=?";
        List list = getJdbcTemplate().query(sql, new Object[]{user.getUserId(), user.getLoginName()}, new RowMapper<Boolean>() {
            @Override
            public Boolean mapRow(ResultSet resultSet, int i) throws SQLException {
                return null;
            }
        });
        if (list.size() == 1)
            return true;
        return false;
    }

    @Override
    public Role checkRole(User user) {
        if (!userExist(user))
            return null;
        String sql = "select role_name,role_statue from user u inner join role r on u.role_id=r.role_id where u.user_id=?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{user.getUserId()}, new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                Role role = new Role();
                role.setRoleName((String) resultSet.getObject("role_name"));
                role.setStatue((Integer) resultSet.getObject("role_statue"));
                return role;
            }
        });
    }

    @Override
    public int denyAdminPermission(User user) {
        if (!checkRole(user).getRoleName().equals(Role.ADMINISTRATION))
            return -1;
        Role role = new Role();
        role.setRoleName(Role.NORMAL_USER);
        role.setStatue(Role.HAVE_PERMISSION);
        return updatePermission(user, role);
    }

    @Override
    public int denyNormalPermission(User user) {
        if (!checkRole(user).getRoleName().equals(Role.NORMAL_USER))
            return -1;
        Role role = new Role(Role.NORMAL_USER, Role.UN_PERMISSION);
        return updatePermission(user, role);
    }


}
