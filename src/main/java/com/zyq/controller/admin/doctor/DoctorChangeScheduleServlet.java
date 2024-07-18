package com.zyq.controller.admin.doctor;

import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/doctor/changeSchedule")
public class DoctorChangeScheduleServlet extends HttpServlet {
    private DoctorService doctorsService = new DoctorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String num = req.getParameter("num");
        String did = req.getParameter("did");
        String shiftTime = req.getParameter("shiftTime");
        String date = req.getParameter("date");
        System.out.println("date="+date);
        System.out.println("did="+ did);
        System.out.println("num="+ num);
        if(did!=null && !did.equals(""))
        {
            boolean flag = doctorsService.updateDoctorSchedule(Integer.valueOf(did),Integer.valueOf(num), shiftTime, date);
            System.out.println("flag="+ flag);
            if(flag){
                resp.sendRedirect(req.getContextPath()+"/admin/doctor/getDoctorAllSchedule");

            }
        }

    }
}
