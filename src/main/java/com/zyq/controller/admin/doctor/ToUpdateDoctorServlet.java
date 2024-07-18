package com.zyq.controller.admin.doctor;

import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;
import com.zyq.pojo.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/doctor/toUpdate")
public class ToUpdateDoctorServlet extends HttpServlet {
    private DoctorService doctorService = new DoctorServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String did = req.getParameter("did");
        String pid = req.getParameter("pid");
        //调用业务层根据主键id返回科室对象的方法
        Doctor doctor = doctorService.getDoctorById(did);
        if(pid!= null && !"".equals(pid)){
            doctor.setProfessionalTitleId(Integer.parseInt(pid));
        }
        else{
            doctor.setProfessionalTitleId(0);
        }
        System.out.println("pid: "+pid);
        //把科室对象设置到Request作用域
        req.setAttribute("doctor", doctor);
        //跳转修改页面回显对象信息
        req.getRequestDispatcher("updateDoctor.jsp").forward(req, resp);

    }
}
