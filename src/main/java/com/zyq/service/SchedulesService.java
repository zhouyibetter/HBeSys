package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.DoctorQuery;
import com.zyq.pojo.DoctorSchedule;
import com.zyq.pojo.Doctor;

import java.util.List;

public interface SchedulesService {

    boolean deleteScheduleById(String id);
}
