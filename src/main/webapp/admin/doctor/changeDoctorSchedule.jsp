<%--
  Created by IntelliJ IDEA.
  User: Hongming
  Date: 2024/7/17
  Time: 11:48
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
    <script>
        function changeDoctorScheduleJudle(visitCount)
        {
            var sumCount = document.getElementsByName("num")[0].value;
            if(sumCount < visitCount)
            {
                alert("修改总数应该大于现已预约数");
            }
            else{
               $("#listform").submit();
            }
        }
    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>排班</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="${pageContext.request.contextPath}/admin/doctor/changeSchedule" id="listform">
            <div class="form-group">
                <div class="label">
                    <label>数量：</label>
                </div>
                <div class="field">
                    <input type="number" class="input w50"   name="num" data-validate="required:请输入排班数量" />
                    <div class="tips"></div>
                    <input type="hidden" name="did" value="${param.did}" >
                    <input type="hidden" name="shiftTime" value="${param.shiftTime}">
                    <input type="hidden" name="date" value="${param.date}">
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <a href="javascript:void(0)" name="ddd" class="button bg-main icon-check-square-o" onclick="changeDoctorScheduleJudle(${param.visitCount})"> 提交</a>
                </div>
    </div>
        </form>
    </div>
</div>

</body></html>