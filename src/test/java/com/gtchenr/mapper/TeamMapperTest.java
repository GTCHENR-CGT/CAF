package com.gtchenr.mapper;

import com.gtchenr.pojo.Team;
import com.gtchenr.utils.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TeamMapperTest {

    TeamMapper teamMapper = MybatisUtil.getSqlSession().getMapper(TeamMapper.class);

    @Test
    public void test01() {
        List<Team> teams = teamMapper.queryAll();
        for (Team t : teams) {
            System.out.println(t);
        }
    }

    @Test
    public void test02() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

}
