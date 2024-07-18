package com.zyq.mapper;

import com.zyq.pojo.Consultation;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

public interface ConsultationMapper {
    List<Consultation> getConsultationConciseList(Integer doctorId) throws SQLException;

    Consultation getConsultationDetailInfo(String consultationId) throws SQLException;
}
