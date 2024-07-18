<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2024/7/10
  Time: 16:36
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
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改医生信息</strong></div>
    <div class="body-content">
        <form method="post" id="myForm" class="form-x" enctype="multipart/form-data" action="${pageContext.request.contextPath}/doctor/updateDoctor">
            <div class="form-group">
                <br>
                <div class="label">
                    <label>医生姓名:</label>
                </div>
                <div class="field">
<%--                    保存提交的当前记录Id--%>
                    <input type="hidden" name="id" value="${doctor.doctorId}">
                    <input type="hidden" name="pid" value="${doctor.professionalTitleId}">
                    <input type="text" class="input w50" id="Dname" value="${doctor.name}" name="doctorname" data-validate="required:请输入医生姓名" />
                    <div class="tips"></div>
                </div>
                <div class="label">
                    <label>电话:</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="Dphone" value="${doctor.phone}" name="doctorphone" required data-validate="required:请输入医生电话" />
                    <div class="tips"></div>
                </div>
                <div class="label">
                    <label>邮箱:</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="Demail" value="${doctor.email}" name="doctoremail" required data-validate="required:请输入医生邮箱" />
                    <div class="tips"></div>
                </div>
                <div class="label">
                    <label>挂号费:</label>
                </div>
                <div class="field">
                    <label>
                        <input type="text" class="input w50" id="DFee" value="${doctor.registrationFee}" name="doctorfee" data-validate="required:请输入挂号费" />
                    </label>
                    <div class="tips"></div>
                </div>
                <div class="label">
                    <label>入职日期:</label>
                </div>
                <div class="field">
                    <label>
                        <input type="date" class="input w50" id="entryDate" value="${doctor.entryDate}" name="doctorEntryDate"/>
                    </label>
                    <div class="tips"></div>
                </div>
                <div class="label">
                    <label>职称:</label>
                </div>
                <li>
                    <select name="doctorprofessionalTitle" class="input" style="width:200px; line-height:17px;">
                        <c:forEach items="${ptlist}" var="pt">
                            <option value="${pt.id}"${doctor.professionalTitleId==pt.id?"selected":""}>${pt.titleName}</option>
                        </c:forEach>
                    </select>
                </li>
                <div class="label">
                    <label>头像:</label>
                </div>
                <label>
                    <input type="hidden" name="avatar" value="${doctors.avatar}">
                    <input type="file" class="input tips" name="myfile" style="width: 25%;float: left"/>
                    <img src="${pageContext.request.contextPath}/${doctors.avatar}" width="50px">
                </label>
                <div class="tips"></div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>医生简介:</label>
                </div>
                <div class="field">
                    <textarea class="input" name="doctordesc" style=" height:90px;">${doctor.introduction}</textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" onclick="test()"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function test(){
        var flag = false
        var now = new Date();
        var currentDate = now.toISOString().split('T')[0];
        var EntryDate = document.getElementById("entryDate").value
        var doctorEmail = document.getElementById("Demail").value
        var doctorFee = document.getElementById("DFee").value
            if(EntryDate == null ||EntryDate==""){
                alert("入职日期不能为空，请重新填写！")
                event.preventDefault()
            }
            else if(EntryDate>currentDate){
                    alert("入职日期不合理，请重新填写！")
                    event.preventDefault()
            }
            else if(isValidEmail(doctorEmail)==false){
                alert("邮箱格式不合法，请重新填写！")
                event.preventDefault()
            }
            else if(parseInt(doctorFee)==0){
                alert("挂号费不能为0，请重新填写！")
                event.preventDefault()
            }
            else{
                $("#listform").submit()
            }
    }
    //检测邮箱格式是否合法
    function isValidEmail(emailStr) {
        const regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
        return regex.test(emailStr);
    }
</script>
</body></html>
