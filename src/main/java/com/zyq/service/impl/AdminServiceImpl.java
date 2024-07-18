package com.zyq.service.impl;

import com.zyq.mapper.AdminMapper;
import com.zyq.pojo.Admin;
import com.zyq.service.AdminService;
import com.zyq.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    @Override
    public Admin login(String username, String password) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
            return adminMapper.login(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }
}
