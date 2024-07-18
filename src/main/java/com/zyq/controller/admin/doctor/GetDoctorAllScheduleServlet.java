package com.zyq.controller.admin.doctor;

import com.github.pagehelper.PageInfo;
import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//用于多条件获取医生信息
@WebServlet("/admin/doctor/getDoctorAllSchedule")
public class GetDoctorAllScheduleServlet extends HttpServlet {
    private DoctorService doctorsService = new DoctorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String page = req.getParameter("page");
        //调用业务层查询方法,需要分页用PageInfo
        PageInfo pageInfo = doctorsService.getDoctorAllSchedule(page);
        //跳转jsp页面，显示数据，把需要展示数据设置到request作用域中
        req.setAttribute("pageInfo",pageInfo);

        //使用转发的跳转方式
        req.getRequestDispatcher("schedule.jsp").forward(req,resp);
    }
}
