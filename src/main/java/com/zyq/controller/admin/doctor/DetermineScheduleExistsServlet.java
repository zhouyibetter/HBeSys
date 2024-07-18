package com.zyq.controller.admin.doctor;

import com.zyq.pojo.DoctorSchedule;
import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//返回二级科室
@WebServlet("/admin/doctor/determineScheduleExists")
public class DetermineScheduleExistsServlet extends HttpServlet {
    private DoctorService doctorsService = new DoctorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String doctorId = req.getParameter("doctorId");
        String shiftTime = req.getParameter("shiftTime");
        String currentDate = req.getParameter("currentDate");
        String departId = req.getParameter("departId");
        DoctorSchedule temp = new DoctorSchedule();
        String sTime=null;
        if (!doctorId.equals("") && !departId.equals(""))
        {
            temp.setDepartmentId(Integer.valueOf(departId));

        }
        temp.setDoctorId(doctorId);
        temp.setDate(currentDate);
        if(shiftTime.equals("0")){
            sTime = "上午";
        }else if(shiftTime.equals("1")){
            sTime = "下午";
        }
        temp.setShiftTime(sTime);

        boolean flag  = doctorsService.determineScheduleExists(temp);
        System.out.println("flag = " + flag);
        //获取输出对象
        PrintWriter writer = resp.getWriter();
        writer.print(flag);

        writer.flush();
        writer.close();

    }
}
