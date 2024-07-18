package com.zyq.controller.admin.doctor;

import com.alibaba.fastjson.JSON;
import com.zyq.pojo.Doctor;
import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//返回二级科室
@WebServlet("/admin/doctor/getScheduleByDepartId")
public class GetScheduleByDepartIdServlet extends HttpServlet {
    private DoctorService doctorsService = new DoctorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String departId = req.getParameter("departId");
        System.out.println("departId = " + departId);
        List<Doctor> dScheduleByDepartIdList = doctorsService.getDoctorByDepartId(departId);
        System.out.println("dScheduleByDepartIdList = " + dScheduleByDepartIdList);
        //获取输出对象
        PrintWriter out = resp.getWriter();
        out.print(JSON.toJSON(dScheduleByDepartIdList));//把dlist转换成JSON格式
        out.flush();
        out.close();
    }
}
