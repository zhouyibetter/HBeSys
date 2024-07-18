package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.Doctor;
import com.zyq.pojo.DoctorQuery;
import com.zyq.pojo.DoctorSchedule;

import java.util.List;

public interface DoctorService {

    Doctor login(String username, String password);

    boolean addDoctors(String cid, String num);

    PageInfo getDoctorListPage(DoctorQuery doctorQuery);

    boolean deleteById(String did);

    boolean deleteDoctorById(String id);

    boolean updateDoctorByJobNumber(Doctor doctors);

    PageInfo getDoctorSchedule(DoctorSchedule doctorSchedule, String page);

    PageInfo getDoctorAllSchedule(String page);

    PageInfo getScheduleById(String departId, String page);

    List<Doctor> getDoctorByDepartId(String departId);

    boolean updateSchedule();

    boolean addSchedule(String doctorId,String currentDate, String departId, String shiftTime, String scheduleNum);

    boolean updateDoctorSchedule(Integer integer, Integer num, String shiftTime,String date);

    boolean determineScheduleExists(DoctorSchedule doctorSchedule);

    Doctor getDoctorById(String did);

    boolean updateDoctor(Doctor doctor);

    Boolean updateMedicalAdviceCaseServlet(String consultationId, String medicalAdviceCase);
}
