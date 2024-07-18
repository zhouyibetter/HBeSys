package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.Consultation;

import javax.mail.MessagingException;
import java.io.IOException;

public interface ConsultationService {
    PageInfo getConsultationConciseList(String page, Integer patientId);

    Consultation getConsultationDetailInfo(String consultationId);

    Boolean sendEmail(String to, String subject, String text) throws MessagingException;
}
