package com.zyq.controller;

import com.alibaba.fastjson.JSON;
import com.zyq.pojo.Admin;
import com.zyq.pojo.Doctor;
import com.zyq.service.AdminService;
import com.zyq.service.DoctorService;
import com.zyq.service.impl.AdminServiceImpl;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AdminService adminService = new AdminServiceImpl();
    private final DoctorService doctorService = new DoctorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        PrintWriter writer = resp.getWriter();
        // 根据传入的角色进行分支查询判断
        // 查找数据库中是否有<username, password>的用户

        // 结果JSON构造hash
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        boolean flag = false;
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        if ("m".equals(role)) {
            // 管理员角色登录
            Admin admin = adminService.login(username, password); // 1
            if (admin != null) {
                req.getSession().setAttribute("admin", admin);
                flag = true;
                result.put("success", true);
                writer.print(JSON.toJSONString(result)); // 使用toJSONString而不是toJSON
            }
        } else if ("d".equals(role)) {
            // 医生角色登录
            Doctor doctor = doctorService.login(username, password); // 2
            if (doctor != null) {
                req.getSession().setAttribute("doctor", doctor);
                flag = true;
                result.put("success", true);
                writer.print(JSON.toJSONString(result)); // 使用toJSONString而不是toJSON
            }
        }

        if (!flag) {
            result.put("success", false);
            writer.print(JSON.toJSONString(result)); // 使用toJSONString而不是toJSON
        }
        writer.flush();
        writer.close();
    }
}
