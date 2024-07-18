package com.zyq.controller.admin.doctor;

import com.github.pagehelper.PageInfo;
import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;
import com.zyq.pojo.Doctor;
import com.zyq.pojo.DoctorQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/doctor/getDoctorList")
public class GetDoctorListServlet extends HttpServlet {
    private DoctorService doctorService = new DoctorServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String did = req.getParameter("did");
        String dname = req.getParameter("dname");
        String pid = req.getParameter("pid");
        String jobnum = req.getParameter("jobnum");
        String page = req.getParameter("page");
        DoctorQuery doctorQuery = new DoctorQuery(did,dname,pid,jobnum,page);
        //调用业务层查询方法
        PageInfo pageInfo = doctorService.getDoctorListPage(doctorQuery);
        //把分页对象设置到request作用域中
        req.setAttribute("pageInfo", pageInfo);
        req.setAttribute("doctorQuery", doctorQuery);
        req.getRequestDispatcher("doctorList.jsp").forward(req,resp);
        System.out.println(pageInfo);
    }
}
