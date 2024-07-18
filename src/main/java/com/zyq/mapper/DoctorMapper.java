package com.zyq.mapper;

import com.zyq.pojo.Doctor;
import com.zyq.pojo.DoctorQuery;
import com.zyq.pojo.DoctorSchedule;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

public interface DoctorMapper {

    @Select("select max(job_number) from doctors")
    String getJobNumberMax() throws SQLException;

    @Select("select max(job_number) from doctors")
    int getMaxJobNum()throws SQLException;

    @Select("select * from doctors where doctor_id=#{did}")
    Doctor getDoctorById(@Param("did") String did) throws  SQLException;

    @Insert("insert into doctors(job_number,password,department_id) values (#{jobNum},'123456',#{did})")
    void addDoctor(@Param("did") String did, @Param("jobNum") int jobNum) throws SQLException;

    List<Doctor> getDoctorList(DoctorQuery doctorQuery) throws SQLException;

    @Update("update doctors set state=0 where doctor_id=#{id}")
    void deleteDoctorById(@Param("id")String id)throws SQLException;

    @Update("update doctors set name=#{name},professional_title_id=#{professionalTitleId},phone=#{phone},entry_date=#{entryDate},introduction=#{introduction},avatar=#{avatar}where job_number=#{jobNumber}")
    void updateDoctorByJobNumber(Doctor doctor)throws SQLException;

    List<DoctorSchedule> getDoctorScheduleList(DoctorSchedule doctorSchedule)throws SQLException;

    List<DoctorSchedule> getDoctorAllSchedule()throws SQLException;

    List<DoctorSchedule> getScheduleById(String departId)throws SQLException;

    @Select("select * from doctors where department_id=#{id}")
    List<Doctor> getDoctorByDepartId(Integer id)throws SQLException;

    @Update("update doctors set state=0 where doctor_id=#{id}")
    void updateSchedule()throws SQLException;

    @Insert("insert into doctor_schedule(doctor_id, date, shift_time, department_id, is_available, visit_count) value(#{doctorId},#{currentDate},#{shifTime},#{departId},1,#{scheduleNum})")
    void addSchedule(@Param("doctorId")Integer doctorId,@Param("currentDate")String currentDate, @Param("departId")Integer departId, @Param("shifTime")String shifTime, @Param("scheduleNum")Integer scheduleNum)throws SQLException;

    @Update("update doctor_schedule d set visit_count=#{num} where d.date=#{date} and doctor_id=#{did}  and shift_time=#{shiftTime} ")
    void updateDoctorByDid(@Param("did") Integer did,@Param("num") Integer num,@Param("shiftTime") String shiftTime,@Param("date") String date)throws SQLException;

    @Select("select count(doctor_id) from doctor_schedule where doctor_id=#{doctorId} and shift_time=#{shiftTime} and date=#{date} and department_id=#{departmentId}")
    int determineScheduleExists(DoctorSchedule doctorSchedule);

    @Delete("delete from doctor_schedule where schedule_id=#{sid}")
    void deleteScheduleById(@Param("sid") String id) throws SQLException;

    @Delete("delete from doctors where doctor_id=#{did}")
    void deleteById(@Param("did") String did) throws SQLException;

    @Update("update doctors set name=#{name},introduction=#{introduction},phone=#{phone},email=#{email},registration_fee=#{registrationFee},entry_date=#{entryDate},professional_title_id=#{professionalTitle} where doctor_id=#{doctorId}")
    void updateDoctor(Doctor doctor) throws SQLException;

    // zyq
    @Select("SELECT * FROM doctors WHERE job_number=#{username} AND password=#{password}")
    Doctor login(@Param("username")String username, @Param("password")String password) throws SQLException;

    @Update("UPDATE consultation SET medical_advice_case=#{medicalAdviceCase} WHERE consultation_id=#{consultationId}")
    Integer updateMedicalAdviceCaseServlet(@Param("consultationId")String consultationId, @Param("medicalAdviceCase")String medicalAdviceCase) throws SQLException;
}
