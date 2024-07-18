package com.zyq.pojo;

public class DoctorSchedule {
    private String scheduleId;
    private String doctorId;
    private Integer sumCount;
    private String date;
    private String shiftTime;
    private Integer departmentId ;
    private Integer isAvailable;
    private Integer visitCount;
    private Integer remarks;
    private Doctor doctor;

    public DoctorSchedule(String doctorId, String date, String shiftTime, Integer departmentId) {
        this.doctorId = doctorId;
        this.date = date;
        this.shiftTime = shiftTime;
        this.departmentId = departmentId;
    }

    public DoctorSchedule() {
    }

    public Integer getSumCount() {
        return sumCount;
    }

    public void setSumCount(Integer sumCount) {
        this.sumCount = sumCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getRemarks() {
        return remarks;
    }

    public void setRemarks(Integer remarks) {
        this.remarks = remarks;
    }

    public Doctor getDoctors() {
        return doctor;
    }

    public void setDoctors(Doctor doctors) {
        this.doctor = doctors;
    }
}
