package com.zyq.service.impl;

import com.zyq.service.ProfessionalTitlesService;
import com.zyq.mapper.ProfessionalTitlesMapper;
import com.zyq.pojo.ProfessionalTitles;
import com.zyq.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProfessionalTitlesServiceImpl implements ProfessionalTitlesService {
    @Override
    public List<ProfessionalTitles> getProfessionalTitlesList() {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            ProfessionalTitlesMapper professionalTitlesMapper = sqlSession.getMapper(ProfessionalTitlesMapper.class);
            return professionalTitlesMapper.getProfessionalTitlesList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }
}
