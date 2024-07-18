package com.zyq.controller.admin.doctor;

import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/doctor/addSchedule")
public class AddScheduleServlet extends HttpServlet {
    private DoctorService doctorsService = new DoctorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String currentDate = req.getParameter("currentDate");
        String departId = req.getParameter("departId");
        String doctorId = req.getParameter("doctorId");
        String shiftTime = req.getParameter("shiftTime");
        String scheduleNum = req.getParameter("scheduleNum");
        System.out.println("doctorId = " + doctorId);
        String sTime = null;
        if (shiftTime.equals("0")) {
            sTime = "上午";
        } else if (shiftTime.equals("1")) {
            sTime = "下午";
        }
        boolean flag = doctorsService.addSchedule(doctorId, currentDate, departId, sTime, scheduleNum);
        if (flag) {
            System.out.println("doctorId = " + doctorId);
            resp.sendRedirect(req.getContextPath() + "/admin/doctor/getDoctorSchedule?departId=" + departId + "&currentDate=" + currentDate);
        }

    }
}
