package com.zyq.controller.admin.doctor;

import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/doctor/enableAvailable")
@MultipartConfig
public class UpdateScheduleServlet extends HttpServlet {
    private DoctorService doctorsService = new DoctorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean flag = doctorsService.updateSchedule();

        if(flag){
            //替换session中得信息，重新查询医生信息，放到session中
            resp.sendRedirect(req.getContextPath() + "/admin/doctor/schedule.jsp");
        }


    }
}
