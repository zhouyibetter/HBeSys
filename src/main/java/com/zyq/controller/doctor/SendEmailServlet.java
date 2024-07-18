package com.zyq.controller.doctor;

import com.alibaba.fastjson.JSON;
import com.zyq.service.ConsultationService;
import com.zyq.service.impl.ConsultationServiceImpl;
import com.zyq.util.EmailUtil;

import javax.mail.MessagingException;
import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/doctor/sendEmail")
public class SendEmailServlet extends HttpServlet {
    ConsultationService consultationService = new ConsultationServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String To = req.getParameter("To");
        String subject = req.getParameter("Subject");
        String body = req.getParameter("Body");

        Map<String, Boolean> res = new HashMap<>();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            consultationService.sendEmail(To, subject, body);
            res.put("success", true);
            writer.print(JSON.toJSONString(res));
        } catch (MessagingException e) {
            e.printStackTrace();
            res.put("success", false);
            writer.print(JSON.toJSONString(res));
        }
        writer.flush();
        writer.close();
    }
}
