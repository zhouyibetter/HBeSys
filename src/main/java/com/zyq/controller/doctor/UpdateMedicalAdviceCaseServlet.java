package com.zyq.controller.doctor;

import com.alibaba.fastjson.JSON;
import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/doctor/updateMedicalAdviceCase")
public class UpdateMedicalAdviceCaseServlet extends HttpServlet {
    private final DoctorService doctorService = new DoctorServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String consultationId = req.getParameter("consultationId");
        String medicalAdviceId = req.getParameter("medicalAdviceCase");
        Map<String, Boolean> result = new HashMap<>();
        PrintWriter writer = resp.getWriter();
        if (consultationId != null && !consultationId.isEmpty()) {
            if (doctorService.updateMedicalAdviceCaseServlet(consultationId, medicalAdviceId)) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        } else {
            result.put("success", false);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.print(JSON.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
