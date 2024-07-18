package com.zyq.controller.doctor;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zyq.pojo.Doctor;
import com.zyq.service.ConsultationService;
import com.zyq.service.impl.ConsultationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/doctor/getConsultationConciseList")
public class GetConsultationListServlet extends HttpServlet {
    private final ConsultationService consultationService = new ConsultationServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求中用户请求当前页 page
        String page = req.getParameter("page");
        // 取用Session中保存的医生信息
        Integer doctorId = ((Doctor) req.getSession().getAttribute("doctor")).getDoctorId();
        PageInfo pageInfo = consultationService.getConsultationConciseList(page, doctorId);
        PrintWriter writer = resp.getWriter();
        Object o = JSON.toJSON(pageInfo);
        // 输出pageInfo对象
        writer.print(JSON.toJSON(pageInfo));
        writer.flush();
        writer.close();
    }
}
