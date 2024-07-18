package com.zyq.controller.admin.doctor;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.DoctorSchedule;
import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//用于多条件获取医生信息
@WebServlet("/admin/doctor/getDoctorSchedule")
public class GetDoctorScheduleListServlet extends HttpServlet {
    private DoctorService doctorsService = new DoctorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String page = req.getParameter("page");
        String departId = req.getParameter("departId");
        String currentDate = req.getParameter("currentDate");
        //创建对象封装查询参数
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        if (!departId.equals("")) {
            doctorSchedule.setDepartmentId(Integer.valueOf(departId));
        }
        if (!currentDate.equals("")) {
            doctorSchedule.setDate(currentDate);
        }

        //调用业务层查询方法,需要分页用PageInfo
        PageInfo pageInfo = doctorsService.getDoctorSchedule(doctorSchedule, page);
        //跳转jsp页面，显示数据，把需要展示数据设置到request作用域中
        req.setAttribute("pageInfo", pageInfo);
        req.setAttribute("departId", departId);
        req.setAttribute("currentDate", currentDate);
        req.setAttribute("page", page);
        System.out.println("schedulePageInfo = " + pageInfo);
        //使用转发的跳转方式
        req.getRequestDispatcher("schedule.jsp").forward(req, resp);
    }
}
