package com.zyq.controller.admin.depart;

import com.alibaba.fastjson.JSON;
import com.zyq.service.DepartmentService;
import com.zyq.service.impl.DepartmentServiceImpl;
import com.zyq.pojo.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin/depart/getChildDepartList")
public class GetChildDepartListServlet extends HttpServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        List<Department> dlist = departmentService.getDepartListAll(pid);
        //先获取输出对象
        PrintWriter out = resp.getWriter();
        //转换成json格式输出
        out.println(JSON.toJSON(dlist));
        out.flush();
        out.close();
    }
}
