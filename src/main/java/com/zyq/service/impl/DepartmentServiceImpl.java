package com.zyq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyq.service.DepartmentService;
import com.zyq.mapper.DepartmentMapper;
import com.zyq.pojo.Department;
import com.zyq.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public PageInfo getDepartListPage(String page,int pId) {
        //使用分页插件进行分页查询
        //pageNum：用户请求的当前页 pageSize：每页显示的记录数
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DepartmentMapper departmentsMapper = sqlSession.getMapper(DepartmentMapper.class);
            //使用分页插件进行分页查询
            //pageNum:用户请求的当前页 pageSize：每页显示的记录数 3，5
            if(page != null && !"".equals(page)){
                PageHelper.startPage(Integer.valueOf(page),5);//开始分页
            }else {
                PageHelper.startPage(1,5);//开始分页,默认返回第一页数据
            }
            //紧跟开始分页代码后面的第一个查询默认会自动分页
            List<Department> departList = departmentsMapper.getDepartList(pId);
            for(Department depart:departList){
                Integer departmentId = depart.getDepartmentId();
                int count = departmentsMapper.getChildCount(departmentId);
                if(count>0){
                    depart.setHaveChild(true);
                }else{
                    depart.setHaveChild(false);
                }
                System.out.println("hasChild"+depart.getHasChild());
                depart.setChildCount(count);
            }
            //创建分页对象，设置集合到分页对象中
            PageInfo<Department> pageInfo = new PageInfo<>(departList);
            System.out.println("pageInfo = " + pageInfo);
            return pageInfo;
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return null;
    }
    //根据pid查询所有二级科室
    @Override
    public List<Department> getDepartListAll(String pid) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DepartmentMapper departmentsMapper = sqlSession.getMapper(DepartmentMapper.class);
            //紧跟开始分页代码后面的第一个查询默认会自动分页
            List<Department> departList = departmentsMapper.getDepartList(Integer.valueOf(pid));
            //循环遍历科室的集合，添加是否有下级科室的属性值
            return departList;
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return null;
    }

    @Override
    //添加科室信息
    public boolean addDepartment(Department departments) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentsMapper = sqlSession.getMapper(DepartmentMapper.class);
            departmentsMapper.addDepartment(departments);
            sqlSession.commit();//提交事务
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        } finally {
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return false;
    }

    @Override
    public Department getDepartmentById(String did) {

        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DepartmentMapper departmentsMapper = sqlSession.getMapper(DepartmentMapper.class);
            Department departments =departmentsMapper.getDepartmentById(did);
            return departments;

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return null;
    }

    @Override
    public boolean updateDepartment(Department departments) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentsMapper = sqlSession.getMapper(DepartmentMapper.class);
            departmentsMapper.updateDepartment(departments);
            sqlSession.commit();//提交事务
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();//事务回滚dish
        } finally {
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return false;
    }

    @Override
    public boolean deleteDepartmentById(String id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DepartmentMapper departmentsMapper = sqlSession.getMapper(DepartmentMapper.class);
            departmentsMapper.deleteDepartmentById(id);
            sqlSession.commit();//提交事务
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        } finally {
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return false;
    }

    @Override
    public List<Department> getDepartListLevel(Integer level) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DepartmentMapper departmentsMapper = sqlSession.getMapper(DepartmentMapper.class);
            return departmentsMapper.getDepartListLevel(level);

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return null;
    }
}
