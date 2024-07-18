package com.zyq.listener;

import com.zyq.service.DepartmentService;
import com.zyq.service.ProfessionalTitlesService;
import com.zyq.service.impl.DepartmentServiceImpl;
import com.zyq.service.impl.ProfessionalTitlesServiceImpl;
import com.zyq.pojo.Department;
import com.zyq.pojo.ProfessionalTitles;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class MyWebApplicationListener implements ServletContextListener {
    //创建科室的业务层对象和职称的对象
    private DepartmentService departmentsService = new DepartmentServiceImpl();
    private ProfessionalTitlesService professionalTitlesService = new ProfessionalTitlesServiceImpl();

    //对象创建时候被调用
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听application作用域对象的创建 = " + sce);
        //查询所有的职称数据
        List<ProfessionalTitles> professionalTitlesList = professionalTitlesService.getProfessionalTitlesList();
        //查询所有的科室信息，二级
        List<Department> dlistLevelf = departmentsService.getDepartListLevel(1);
        List<Department> dlistLevelt= departmentsService.getDepartListLevel(2);
        //把查询所有数据设置到application的作用域中
        ServletContext application = sce.getServletContext();
        application.setAttribute("ptlist", professionalTitlesList);
        application.setAttribute("dListLevelF", dlistLevelf);
        application.setAttribute("dListLevelT", dlistLevelt);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
