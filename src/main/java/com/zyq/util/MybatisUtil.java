package com.zyq.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/*
 *功能描述：mybatis操作的工具类，获取SqlSession的方法，关闭sqlSession方法
 *作者：zz
 *时间：2024/4/12 10:22
 */
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    // 类加载的时候执行，而且只执行一次
    static {
        //1.加载mybatis的核心配置文件
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis_config.xml");
            //2、创建SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //3、创建SqlSessionFactory对象
            sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *功能描述：获取sqlSession对象的方法
     *方法参数：
     *返回值：
     *作者：zz
     *时间：2024/4/12 10:24
     */
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    /*
     *功能描述：关闭sqlSession的方法
     *方法参数：
     *返回值：
     *作者：zz
     *时间：2024/4/12 10:27
     */
    public static void closeSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.close();
            threadLocal.set(null);
        }
    }

    public static void main(String[] args) {
        System.out.println(MybatisUtil.getSqlSession());
    }
}
