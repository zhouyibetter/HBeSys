package com.zyq.controller.admin.doctor;

import com.zyq.service.DoctorService;
import com.zyq.service.impl.DoctorServiceImpl;
import com.zyq.pojo.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import com.zyq.util.FileUtil;

@WebServlet("/admin/doctor/updateDoctor")
@MultipartConfig
public class UpdateDoctorServlet extends HttpServlet {
    private DoctorService doctorService = new DoctorServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String doctorname = req.getParameter("doctorname");
        String doctordesc = req.getParameter("doctordesc");
        String doctorphone = req.getParameter("doctorphone");
        String doctoremail = req.getParameter("doctoremail");
        String doctorfee = req.getParameter("doctorfee");
        String entryDate = req.getParameter("doctorEntryDate");
        String id = req.getParameter("id");
        String pid = req.getParameter("doctorprofessionalTitle");
        //把参数封装给Doctor对象
        Doctor doctor = new Doctor();
        doctor.setName(doctorname);
        doctor.setIntroduction(doctordesc);
        doctor.setPhone(doctorphone);
        doctor.setEmail(doctoremail);
        doctor.setEntryDate(entryDate);
        doctor.setProfessionalTitleId(Integer.parseInt(pid));
        if(id!= null && !"".equals(id)){
            doctor.setDoctorId(Integer.parseInt(id));
        }
        if(doctorfee!= null && !"".equals(doctorfee)){
            doctor.setRegistrationFee(doctorfee);
        }
        Part part = req.getPart("myfile");
        if(part != null && part.getSize() > 0){//需要处理文件上传
            //返回文件上传成功后的路径
            String myfile = FileUtil.transferTo(req, "myfile");
            //设置到doctors对象
            doctor.setAvatar(myfile);
        }
        boolean flag = doctorService.updateDoctor(doctor);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/admin/doctor/getDoctorList");
        }
    }
}
