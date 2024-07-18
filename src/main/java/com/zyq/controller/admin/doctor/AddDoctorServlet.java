package com.zyq.controller.admin.doctor;

import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/doctor/addDoctor")
public class AddDoctorServlet extends HttpServlet {
    private DoctorService doctorService = new DoctorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String pid = req.getParameter("pid");
        String cid = req.getParameter("cid");
        String num = req.getParameter("num");

        boolean flag = false;
        if ("-1".equals(cid)) {
            flag = doctorService.addDoctors(pid, num);
        } else {
            flag = doctorService.addDoctors(cid, num);
        }
        if (flag) {
            resp.sendRedirect(req.getContextPath() + "/admin/doctor/getDoctorList");
        }
    }
}
