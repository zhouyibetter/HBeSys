package com.zyq.mapper;

import com.zyq.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

public interface AdminMapper {
    @Select("SELECT * FROM admins WHERE username=#{username} AND password=#{password}")
    Admin login(@Param("username")String username, @Param("password")String password) throws SQLException;
}
