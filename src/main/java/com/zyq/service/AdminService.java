package com.zyq.service;

import com.zyq.pojo.Admin;
public interface AdminService {
    /**  
     * @Description 用户登录服务接口
     * @Auther zhouyigreat
     * @Date 2024/7/10
     */
    Admin login(String username, String password);
}
