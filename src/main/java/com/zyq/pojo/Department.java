package com.zyq.pojo;

public class Department {
    private int departmentId;
    private String departmentName;
    private int departmentPid;
    private int departmentLevel;
    private String departmentPath;
    private String departmentDescription;
    private boolean hasChild;//是否有下级科室
    private int childCount;//下级科室个数

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentPid() {
        return departmentPid;
    }

    public void setDepartmentPid(int departmentPid) {
        this.departmentPid = departmentPid;
    }

    public int getDepartmentLevel() {
        return departmentLevel;
    }

    public void setDepartmentLevel(int departmentLevel) {
        this.departmentLevel = departmentLevel;
    }

    public String getDepartmentPath() {
        return departmentPath;
    }

    public void setDepartmentPath(String departmentPath) {
        this.departmentPath = departmentPath;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public boolean getHasChild() {
        return hasChild;
    }

    public void setHaveChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}

