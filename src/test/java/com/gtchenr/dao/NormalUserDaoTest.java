package com.gtchenr.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.apache.ibatis.io.Resources;
import com.gtchenr.pojo.Role;


import java.io.IOException;
import java.io.Reader;
import java.util.List;


public class NormalUserDaoTest {

    @Test
    public void Test01() throws IOException {
        String batisConfig = "mybatis.xml";

        //读取配置文件，创建工厂
        Reader reader = Resources.getResourceAsReader(batisConfig);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
        //创建连接
        SqlSession sqlSession = ssf.openSession();
        //查询
        List<Role> objects = sqlSession.selectList("com.gtchenr.pojo.Role.selectRole");

    }
}
