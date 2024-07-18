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

@WebServlet("/admin/depart/updateDepart")
public class UpdateDepartServlet extends HttpServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String departname = req.getParameter("departname");
        String departdesc = req.getParameter("departdesc");
        String id = req.getParameter("id");
        //把参数封装给Department对象
        Department departments = new Department();
        departments.setDepartmentName(departname);
        departments.setDepartmentDescription(departdesc);
        if(id!= null && !"".equals(id)){
            departments.setDepartmentId(Integer.parseInt(id));
        }
        boolean flag = departmentService.updateDepartment(departments);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/admin/depart/getDepartList");
        }
    }
}
