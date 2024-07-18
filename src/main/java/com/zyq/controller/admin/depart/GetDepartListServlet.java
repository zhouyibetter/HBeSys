package com.zyq.controller.admin.depart;

import com.github.pagehelper.PageInfo;
import com.zyq.service.DepartmentService;
import com.zyq.service.impl.DepartmentServiceImpl;
import com.zyq.pojo.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/depart/getDepartList")
public class GetDepartListServlet extends HttpServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //分页；limit 0,3 第一页 limit 3,3 第二页
        //获取请求中用户请求当前页 page
        String page = req.getParameter("page");//用户请求的当前页
        //业务层返回PageInfo对象：包含页面需要分页信息，集合列表
        PageInfo<Department> pageInfo =departmentService.getDepartListPage(page,0);
        //跳转jsp展示数据，将需要展示的数据设置到Request作用域
        req.setAttribute("pageInfo",pageInfo);
        //使用转发的跳转方式
        req.getRequestDispatcher("departList.jsp").forward(req,resp);
    }
}
