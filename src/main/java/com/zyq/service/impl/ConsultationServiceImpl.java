package com.zyq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyq.mapper.ConsultationMapper;
import com.zyq.pojo.Consultation;
import com.zyq.service.ConsultationService;
import com.zyq.util.EmailUtil;
import com.zyq.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ConsultationServiceImpl implements ConsultationService {

    @Override
    public PageInfo getConsultationConciseList(String page, Integer doctorId) {
        try {
            // 获取MenuMapper的sql对象
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            ConsultationMapper consultationMapper = sqlSession.getMapper(ConsultationMapper.class);

            // 使用分页插件进行分页
            if (page != null && !page.isEmpty()) {
                PageHelper.startPage(Integer.parseInt(page), 5); // 开始分页
            } else {
                PageHelper.startPage(1, 5); // 开始分页
            }
            // 紧跟着分页代码后面的第一个查询
            List<Consultation> consultationConciseList = consultationMapper.getConsultationConciseList(doctorId);
            // 创建分页对象，设置集合到分页对象
            PageInfo<Consultation> pageInfo = new PageInfo<>(consultationConciseList);
            return pageInfo;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public Consultation getConsultationDetailInfo(String consultationId) {
        try {
            // 获取MenuMapper的sql对象
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            ConsultationMapper consultationMapper = sqlSession.getMapper(ConsultationMapper.class);

            Consultation consultation = consultationMapper.getConsultationDetailInfo(consultationId);
            return consultation;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public Boolean sendEmail(String to, String subject, String text) throws MessagingException {
        EmailUtil.sendEmail(to, subject, text);
        return true;
    }
}
