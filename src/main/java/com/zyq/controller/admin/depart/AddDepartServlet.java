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

@WebServlet("/admin/depart/addDepart")
public class AddDepartServlet extends HttpServlet {
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
        String pid = req.getParameter("pid");
        //把参数封装给Department对象
        Department departments = new Department();
        departments.setDepartmentName(departname);
        departments.setDepartmentDescription(departdesc);
        if(pid != null && !pid.equals("")){
            int dpid = Integer.parseInt(pid);
            //设置父辈id
            departments.setDepartmentPid(dpid);
            if(dpid==0)
            //父辈id为0,为一级科室
            {
                departments.setDepartmentLevel(1);
            }else{
                departments.setDepartmentLevel(2);
            }
        }
        boolean flag = departmentService.addDepartment(departments);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/admin/depart/getDepartList");
        }
    }
}
