package com.gtchenr.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {

    private static ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<>();
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try (Reader reader = Resources.getResourceAsReader("mybatis.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static SqlSession getSqlSession() {

        SqlSession sqlSession = sqlSessionThreadLocal.get();
        if (sqlSession == null)
            sqlSessionThreadLocal.set(sqlSessionFactory.openSession(true));
        return sqlSessionThreadLocal.get();
    }

    /**
     * 关闭连接
     */
    public static void closeSqlSession() {
        SqlSession sqlSession = sqlSessionThreadLocal.get();
        if (sqlSession != null)
            sqlSession.close();

    }

    public static SqlSession getSqlSession1() {

        return sqlSessionFactory.openSession(true);

    }
}
