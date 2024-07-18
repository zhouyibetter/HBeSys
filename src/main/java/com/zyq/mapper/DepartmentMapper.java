package com.zyq.mapper;

import com.zyq.pojo.Department;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentMapper {
    @Select("select* from departments where department_pid=#{pid}")
    List<Department> getDepartList (int pId) throws SQLException;

    @Insert("insert  into departments(department_name,department_pid,department_level,department_description)" +
            "values(#{departmentName},#{departmentPid},#{departmentLevel},#{departmentDescription})")
    void addDepartment(Department departments) throws  SQLException;

    @Select("select* from departments where department_id=#{did}")
    Department getDepartmentById(@Param("did") String did) throws  SQLException;

    @Update("update departments set department_name=#{departmentName},department_description=#{departmentDescription} where department_id=#{departmentId}")
    void updateDepartment(Department departments) throws  SQLException;

    @Delete("delete from departments where department_id=#{id}")
    void deleteDepartmentById(@Param("id") String id) throws  SQLException;

    @Select("select * from departments where department_level=#{level}")
    List<Department> getDepartListLevel(Integer level) throws SQLException;

    @Select("select * from departments where department_pid=#{pid}")
    void addDepartMent(Department departments) throws SQLException;

    @Update("update departments set department_name=#{departmentName},department_description=#{departmentDescription} where department_id=#{departmentId}")
    void updateDepartMent(Department departments) throws SQLException;

    @Delete("delete from departments where department_id=#{did}")
    void deleteById(@Param("did")String id) throws  SQLException;

    @Select("select count(*) from departments where department_pid=#{departmentId}")
    int getChildCount(Integer departmentId) throws  SQLException;

    @Select("select * from departments where department_level=#{level}")
    List<Department> getDepartListByLevel(Integer level) throws  SQLException;
}
