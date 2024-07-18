package com.zyq.service;

import com.github.pagehelper.PageInfo;
import com.zyq.pojo.Department;

import java.util.List;

public interface DepartmentService {
    PageInfo<Department> getDepartListPage(String page,int pId);

    List<Department> getDepartListAll(String pid);

    boolean addDepartment(Department departments);

    Department getDepartmentById(String did);

    boolean updateDepartment(Department departments);

    boolean deleteDepartmentById(String id);

    List<Department> getDepartListLevel(Integer level);
}
