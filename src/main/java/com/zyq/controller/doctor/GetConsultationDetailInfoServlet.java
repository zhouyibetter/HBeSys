package com.zyq.controller.doctor;

import com.alibaba.fastjson.JSON;
import com.zyq.pojo.Consultation;
import com.zyq.service.ConsultationService;
import com.zyq.service.impl.ConsultationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/doctor/getConsultationDetailInfo")
public class GetConsultationDetailInfoServlet extends HttpServlet {
    private final ConsultationService consultationService = new ConsultationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String consultationId = req.getParameter("consultationId");
        Consultation consultation = consultationService.getConsultationDetailInfo(consultationId);
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(consultation));
        writer.flush();
        writer.close();
    }
}
