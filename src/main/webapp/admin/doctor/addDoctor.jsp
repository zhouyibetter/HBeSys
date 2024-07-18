<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024/7/10
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored= "false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<base href="<%=basePath%>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
<%--    定义文档就绪函数--%>
    <script>
        $(function(){
            //默认查询1级科室
            $.getJSON("${pageContext.request.contextPath}/admin/depart/getChildDepartList?pid=0",function(data){
                var opt="<option value=\"-1\">请选择科室</option>";
                $.each(data,function(i,item){
                    opt += "<option value=\""+item.departmentId+"\">"+item.departmentName+"</option>";
                })
                //获取一级科室的下拉菜单对象，设置option选项
                $("[name=pid]").html(opt);
            })
        })

        function GetDepartList(pid){
            $.getJSON("${pageContext.request.contextPath}/admin/depart/getChildDepartList?pid="+pid,function(data){
                var opt="<option value=\"-1\">请选择科室</option>";
                $.each(data,function(i,item){
                    opt += "<option value=\""+item.departmentId+"\">"+item.departmentName+"</option>";
                })
                //获取二级科室的下拉菜单对象，设置option选项
                $("[name=cid]").html(opt);
            })
        }
    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>添加医生账号</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/admin/doctor/addDoctor">
            <div class="form-group">
                    <div class="label">
                        <label>科室：</label>
                    </div>
                    <div class="field">
                        <select name="pid" class="input w50" onchange="GetDepartList(this.value)">
                        </select>
                        <select name="cid" class="input w50">
                        </select>
                        &nbsp&nbsp&nbsp
                        <div class="tips"></div>
                    </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>账号数量:</label>
                </div>
                <div class="field">
                    <input type="number" class="input w50" name="num" data-validate="required:请输入生成账号的数量" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body></html>
