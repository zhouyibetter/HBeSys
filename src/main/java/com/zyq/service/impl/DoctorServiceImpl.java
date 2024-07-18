package com.zyq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyq.mapper.DoctorMapper;
import com.zyq.pojo.Doctor;
import com.zyq.pojo.DoctorQuery;
import com.zyq.pojo.DoctorSchedule;
import com.zyq.service.DoctorService;
import com.zyq.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    @Override
    public boolean addDoctors(String cid, String num) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            //cid:科室的id，job_number 自动生成,password=1234
            //1、查询job_number在数据库中最大值
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            String jobNumber = doctorMapper.getJobNumberMax();
            int jobNum = Integer.parseInt(jobNumber);
            for (int i = 0; i < Integer.parseInt(num); i++) {
                doctorMapper.addDoctor(cid, ++jobNum);
            }
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }

    @Override
    public PageInfo<Doctor> getDoctorListPage(DoctorQuery doctorQuery) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            //分页查询
            String page = doctorQuery.getPage();
            if (page != null && !"".equals(page)) {
                PageHelper.startPage(Integer.valueOf(page), 5);//开始分页
            } else {
                PageHelper.startPage(1, 5);//开始分页,默认返回第一页数据
            }
            List<Doctor> dlist = doctorMapper.getDoctorList(doctorQuery);
            PageInfo<Doctor> pageInfo = new PageInfo<>(dlist);
            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            Doctor doctor = doctorMapper.getDoctorById(id);
            doctorMapper.deleteById(id);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }

    @Override
    public boolean deleteDoctorById(String id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            doctorMapper.deleteDoctorById(id);
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

    @Override
    public boolean updateDoctorByJobNumber(Doctor doctor) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            doctorMapper.updateDoctorByJobNumber(doctor);
            sqlSession.commit();//提交事务
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }

    @Override
    public PageInfo getDoctorSchedule(DoctorSchedule doctorSchedule, String page) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            //分页查询
            if (page != null && !"".equals(page)) {
                PageHelper.startPage(Integer.valueOf(page), 5);
            } else {
                PageHelper.startPage(1, 5);
            }
            //紧跟开始分页的第一个查询会默认自动分页
            List<DoctorSchedule> doctorScheduleList = doctorMapper.getDoctorScheduleList(doctorSchedule);
            //将list转化成PageInfo
            PageInfo doctorPageInfo = new PageInfo(doctorScheduleList);
            return doctorPageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public PageInfo getDoctorAllSchedule(String page) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            //分页查询
            if (page != null && !"".equals(page)) {
                PageHelper.startPage(Integer.valueOf(page), 5);
            } else PageHelper.startPage(1, 5);
            //紧跟开始分页的第一个查询会默认自动分页
            List<DoctorSchedule> doctorList = doctorMapper.getDoctorAllSchedule();
            //将list转化成PageInfo
            PageInfo doctorPageInfo = new PageInfo(doctorList);
            System.out.println("pageInfoAllSchedule = " + doctorPageInfo);
            return doctorPageInfo;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public PageInfo getScheduleById(String departId, String page) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            //分页查询
            if (page != null && !"".equals(page)) {
                PageHelper.startPage(Integer.valueOf(page), 5);
            } else PageHelper.startPage(1, 5);
            //紧跟开始分页的第一个查询会默认自动分页
            List<DoctorSchedule> scheduleList = doctorMapper.getScheduleById(departId);
            //将list转化成PageInfo
            PageInfo scheduleByIdPageInfo = new PageInfo(scheduleList);
            System.out.println("pageInfoScheduleById = " + scheduleByIdPageInfo);
            return scheduleByIdPageInfo;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public List<Doctor> getDoctorByDepartId(String departId) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            //紧跟开始分页的第一个查询会默认自动分页
            Integer id = Integer.valueOf(departId);
            List<Doctor> doctorList = doctorMapper.getDoctorByDepartId(id);
            return doctorList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public boolean updateSchedule() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            doctorMapper.updateSchedule();
            sqlSession.commit();//提交事务
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }

    @Override
    public boolean addSchedule(String doctorId, String currentDate, String departId, String shiftTime, String scheduleNum) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            doctorMapper.addSchedule(Integer.valueOf(doctorId), currentDate, Integer.valueOf(departId), shiftTime, Integer.valueOf(scheduleNum));
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

    @Override
    public boolean updateDoctorSchedule(Integer did, Integer num, String shiftTime, String date) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            doctorMapper.updateDoctorByDid(did, num, shiftTime, date);
            sqlSession.commit();//提交事务
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }

    @Override
    public boolean determineScheduleExists(DoctorSchedule doctorSchedule) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);

            int count = doctorMapper.determineScheduleExists(doctorSchedule);
            System.out.println("count = " + count);
            return count <= 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }

    @Override
    public Doctor getDoctorById(String did) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            Doctor doctor = doctorMapper.getDoctorById(did);
            return doctor;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return null;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            doctorMapper.updateDoctor(doctor);
            sqlSession.commit();//提交事务
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();//事务回滚dish
        } finally {
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return false;
    }

    // zyq
    @Override
    public Doctor login(String username, String password) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            return doctorMapper.login(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public Boolean updateMedicalAdviceCaseServlet(String consultationId, String medicalAdviceCase) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
            doctorMapper.updateMedicalAdviceCaseServlet(consultationId, medicalAdviceCase);
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
