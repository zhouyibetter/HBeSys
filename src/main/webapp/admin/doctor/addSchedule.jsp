<%--
  Created by IntelliJ IDEA.
  User: hz
  Date: 2024/7/9
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript">
        //页面加载完成就需要做的
        //定义文档就绪函数
        //默认查询一级科室
        $(function (){
            var departId = "<%=request.getParameter("departId")%>";
            $.getJSON("${pageContext.request.contextPath}/admin/doctor/getScheduleByDepartId?departId="+departId,function (data){

                var opt = " <option value=\"-1\">请选择医生</option>";
                $.each(data,function (i,item){
                    opt +=  "<option  value=\""+item.doctorId+"\">"+item.name+"</option>";
                })
                //获取一级科室的下拉菜单对象，设置option选项
                $("[name=doctorId]").html(opt);
            })
        })


    </script>
    <script>
        function getScheduleByDateId(){
            var doctorId = $("#doctorId").val();
            var shiftTime = $("#shiftTime").val();
            var departId = $("#departId").val();
            var currentDate = $("#currentDate").val();
            var testT = 0;
            //发送请求到服务器，根据pid查询返回该科室下所有的二级科室
            $.getJSON("${pageContext.request.contextPath}/admin/doctor/determineScheduleExists?doctorId="+doctorId+"&shiftTime=" + shiftTime + "&departId=" + departId + "&currentDate=" + currentDate,function(data) {

                if (data.doctorId == doctorId && data.date == currentDate && data.shiftTime == shiftTime &&data.departmentId == departId)
                {
                    testT = 1;
                }
                });
            alert(testT)
            //发送请求到服务器，根据pid查询返回该科室下所有的二级科室

        }
        var flag=false;
        function  checkName(doctorId,shiftTime,departId,currentDate){

            $.get("${pageContext.request.contextPath}/admin/doctor/determineScheduleExists?doctorId="+doctorId+"&shiftTime=" + shiftTime + "&departId=" + departId + "&currentDate=" + currentDate,function (res){
                if(res=="true")
                {
                    flag=true;
                }

                else{
                    alert("该排班已经存在")
                    flag=false;
                }
            },"text");
            return flag;

        }

        function checkFormData(){
            var doctorId = $("#doctorId").val();
            var shiftTime = $("#shiftTime").val();
            var departId = $("#departId").val();
            var currentDate = $("#currentDate").val();
            if(checkName(doctorId,shiftTime,departId,currentDate))
            {
                return true;
            }
            else
            {
                return false;
            }

        }
    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加内容</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/admin/doctor/addSchedule" onsubmit="return checkFormData()">
            <div class="form-group">
                <div class="label">
                    <label>排班日期：</label>
                </div>
                <div class="field">
                    <input type="hidden" name="departId" id="departId" value="${param.departId}">
                    <input type="date" class="input w50" id="currentDate" name="currentDate"  value="${param.currentDate}" data-validate="required:请填写排班日期" readonly />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>医生：</label>
                </div>
                <div class="field">
                    <select name="doctorId" id="doctorId"  class="input w50" >

                    </select>
                    <div class="tips"></div>
                </div>
                </div>
            <div class="form-group">
                <div class="label">
                    <label>排班时间：</label>
                </div>
                <div class="field">
                    <select  name="shiftTime" id="shiftTime" class="input w50" data-validate="required:请选择排班时间">
                        <option value="-1">请选择排班时间</option>
                        <option value="0" >上午</option>
                        <option value="1" >下午</option>
                    </select>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>排班数量：</label>
                </div>
                <div class="field">
                    <input type="number" class="input w50"  name="scheduleNum"   data-validate="required:请填写排班数量"  />
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