package com.zyq.pojo;

//医生的查询对象
public class DoctorQuery {
    private String did;
    private String dname;
    private String pid;
    private String jobnum;
    private String page;

    public DoctorQuery(String did, String dname, String pid, String jobnum, String page) {
        this.did = did;
        this.dname = dname;
        this.pid = pid;
        this.jobnum = jobnum;
        this.page = page;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getJobnum() {
        return jobnum;
    }

    public void setJobnum(String jobnum) {
        this.jobnum = jobnum;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
