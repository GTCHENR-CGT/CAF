package com.gtchenr.dao.imp;

import com.gtchenr.dao.NormalUserDao;
import com.gtchenr.pojo.User;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class NormalUserDaoImp extends JdbcDaoSupport implements NormalUserDao {

    @Override
    public int add(User user) {
        String sql = "insert into user(login_name,password,role_id,name,sex,phone,email) value (?,?,?,?,?,?,?)";
        return getJdbcTemplate().update(sql, user.getLoginName(), user.getPassword(), user.getRoleId(), user.getName(),
                user.getSex(), user.getPhone(), user.getEmail());

    }
}
