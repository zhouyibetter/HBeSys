package com.zyq.controller.admin.depart;

import com.zyq.service.DepartmentService;
import com.zyq.service.impl.DepartmentServiceImpl;
import com.zyq.pojo.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/depart/toUpdate")
public class ToUpdateDepartServlet extends HttpServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String did = req.getParameter("did");
        //调用业务层根据主键id返回科室对象的方法
        Department departments = departmentService.getDepartmentById(did);
        //把科室对象设置到Request作用域
        req.setAttribute("departments", departments);
        //跳转修改页面回显对象信息
        req.getRequestDispatcher("updateDepart.jsp").forward(req, resp);

    }
}
