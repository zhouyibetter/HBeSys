package com.zyq.controller.admin.depart;

import com.zyq.service.DepartmentService;
import com.zyq.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/depart/deleteDepart")
public class DeleteDepartServlet extends HttpServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String id = req.getParameter("id");
        System.out.println(id);
        boolean flag = departmentService.deleteDepartmentById(id);
        System.out.println(flag);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/admin/depart/getDepartList");
        }
    }
}
