package com.zyq.service.impl;


import com.zyq.mapper.DoctorMapper;
import com.zyq.service.SchedulesService;
import com.zyq.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;


public class SchedulesServiceImpl implements SchedulesService {

    @Override
    public boolean deleteScheduleById(String id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            doctorMapper.deleteScheduleById(id);
            sqlSession.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }
}
