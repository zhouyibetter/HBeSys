<%--
  Created by IntelliJ IDEA.
  User: hz
  Date: 2024/7/16
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%--防止样式丢失--%>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<base href="<%=basePath%>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script>
        //设置时间和科室id
        function setDepartId(departId)
        {
            $("#departId").val(departId);
        }
        function  setCurrentDate(i)
        {
            $("#currentDate").val(getDateNoWeek(i));
        }
    </script>
    <script>
        function currentDate() {
            var date = Date();
            return date;
        }
        function getDateNoWeek(iStr) {
            const today = new Date();
            const month1 = today.getMonth() + 1;
           // alert(month1);
            const year1 = today.getFullYear();
            //alert(year1);

            // 将字符串 iStr 转换为整数
            const i = parseInt(iStr, 10); // 或者使用 +iStr

            // 计算新的日期
            const date1 = today.getDate() + i;
            var cDate = year1 + '-' + (month1 > 9 ? month1 : ('0' + month1)) + '-' + (date1 > 9 ? date1 : ('0' + date1));
            return cDate;
            //alert(cDate);
        }
        //提交表单的事件，表单验证
        function submitFormData()
        {
            $("#page").val("1");
            if($("#departId").val() == "-1"){
                //将需要删除的id发送到服务器中
                alert("请选择科室！！！");
            }else if($("#currentDate").val() == "-1")
            {
                alert("请选择时间！！！");
            }
            else {
                var p = $("#page").val()
                var d = $("#departId").val()
                var c = $("#currentDate").val()

                window.location.href = "${pageContext.request.contextPath}/admin/doctor/getDoctorSchedule?page="+ p + "&departId="+ d + "&currentDate=" + c;
            }
        }

        //添加科室
        function addSchedule()
        {
            if($("#departId").val() == "-1"){
                //将需要删除的id发送到服务器中
                alert("请选择添加需要的科室信息！！！");
            } else if($("#currentDate").val() == "-1") {
                alert("请选择添加需要的时间信息！！！");
            } else {
                var d = $("#departId").val()
                var c = $("#currentDate").val()
                window.location.href = "${pageContext.request.contextPath}/admin/doctor/addSchedule.jsp?departId="+ d + "&currentDate=" + c;
            }
        }

        //添加排班
        function enableAvailable()
        {
            window.location.href = "${pageContext.request.contextPath}/admin/doctor/enableAvailable";
        }
    </script>
    <script>

        // 当网页加载完成后显示当前日期
        window.onload = function () {
            showDate();
        };

        function showDate() {
            var weekDay = ["日", "一", "二", "三", "四", "五", "六"]
            const today = new Date();

            const month1 = today.getMonth() + 1;
            const year1 = today.getFullYear();

            for (i = 0; i <= 5; i++) {
                var date1 = today.getDate() + i;
                var weekday1 = weekDay[(today.getDay() + i) % 7];
                document.getElementById('todayDate' + i).innerHTML = year1 + '年' + month1 + '月' + date1 + '日' + '周' + weekday1;
            }

        }
    </script>
    <script type="text/javascript">
        $(function () {
            $(".leftnav h2").click(function () {
                $(this).next().slideToggle(200);
                $(this).toggleClass("on");
            })
            $(".leftnav ul li a").click(function () {
                $("#a_leader_txt").text($(this).text());
                $(".leftnav ul li a").removeClass("on");
                $(this).addClass("on");
            })
        });

    </script>
    <script>
        //页面跳转提交表单
        function  getSelectedPage(page)
        {
            var d = $("#departId").val()
            var c = $("#currentDate").val()
            window.location.href = "${pageContext.request.contextPath}/admin/doctor/getDoctorSchedule?page="+page + "&departId="+ d + "&currentDate=" + c;
        }
    </script>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            // 获取所有的department titles
            var titles = document.querySelectorAll(".department-title");

            // 恢复保存的展开项
            titles.forEach(function(title) {
                var id = title.id;
                var list = document.getElementById("department-list-" + id.split('-')[1]);
                var isExpanded = localStorage.getItem("department-" + id.split('-')[1]);

                if (isExpanded === "true") {
                    list.classList.add("active");
                }

                // 添加点击事件
                title.addEventListener("click", function() {
                    if (list.classList.contains("active")) {
                        list.classList.remove("active");
                        localStorage.setItem("department-" + id.split('-')[1], "false");
                    } else {
                        list.classList.add("active");
                        localStorage.setItem("department-" + id.split('-')[1], "true");

                    }
                });
            });
        });
    </script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/admin/doctor/getDoctorSchedule" id="listform">
    <div class="leftnav" style="top: 0; display: inline-block;">
        <div class="leftnav-title"><strong><span class="icon-list icon-pencil-square-o"></span>菜单列表</strong></div>
        <c:forEach items="${dListLevelF}" var="dListF">
            <!-- 3个hidden input 存储page departId currentDate -->
            <input type="hidden" name="page" id="page" value="${param.page}">
            <input type="hidden" name="departId" id="departId" value="${param.departId}">
            <input type="hidden" name="currentDate" id="currentDate" value="${param.currentDate}">


            <h2 id="department-${dListF.departmentId}" class="department-title">
                <span class="icon-pencil-square-o"></span>${dListF.departmentName}
            </h2>
            <ul id="department-list-${dListF.departmentId}" class="department-list">
                <c:forEach items="${dListLevelT}" var="dListT">
                    <c:choose>
                        <c:when test="${dListF.departmentId==dListT.departmentPid}">
                            <input type="hidden" value="${dListT.departmentId}" name="departId" class="input">
                            <li>
                                <a onclick="setDepartId(${dListT.departmentId})" target="right" style="cursor: pointer">
                                    <span class="icon-caret-right"></span>${dListT.departmentName}
                                </a>
                            </li>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </ul>
        </c:forEach>
    </div>

    <div class="panel admin-panel" style="display: inline-block; margin-left: 232px;">
        <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong></div>
        <div class="padding border-bottom" style="display: flex; justify-content: space-between">
            <td colspan="6">
                <button type="button" class="button border-green "  id="todayDate0" onclick="setCurrentDate('0')"><span
                        class="icon-plus-square-o"></span></button>
                <button type="button" class="button border-green "  id="todayDate1" onclick="setCurrentDate('1')"><span
                        class="icon-plus-square-o"></span></button>
                <button type="button" class="button border-green "  id="todayDate2" onclick="setCurrentDate('2')"><span
                        class="icon-plus-square-o"></span></button>
                <button type="button" class="button border-green "  id="todayDate3" onclick="setCurrentDate('3')"><span
                        class="icon-plus-square-o"></span></button>
                <button type="button" class="button border-green "  id="todayDate4" onclick="setCurrentDate('4')"><span
                        class="icon-plus-square-o"></span></button>
                <button type="button" class="button border-green "  id="todayDate5" onclick="setCurrentDate('5')"><span
                        class="icon-plus-square-o"></span></button>
            </td>
        </div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li><a class="button border-main icon-plus-square-o" onclick="submitFormData()" style="cursor: pointer">查看排班</a></li>
                <li><a class="button border-main icon-plus-square-o" onclick="addSchedule()" style="cursor: pointer">添加排班</a></li>
            </ul>
        </div>

        <table class="table table-hover text-center">
            <tr>
                <th width="10%">医生姓名</th>
                <th width="20%">日期</th>
                <th width="15%">时间</th>
                <th width="20%">预约总数</th>
                <th width="10%">剩余号</th>
                <th width="15%">操作</th>
            </tr>
            <volist name="list" id="vo">
                <c:forEach items="${pageInfo.list}" var="docPage">
                <tr>
                    <td>${docPage.doctors.name}</td>
                    <td>${docPage.date}</td>
                    <td>${docPage.shiftTime}</td>
                    <td>${docPage.visitCount}</td>
                    <td>${docPage.sumCount-docPage.visitCount}</td>
                    <%--<td><div class="button-group"> <a class="button border-red" href="javascript:void(0)" onclick="deleteDoctorById(${docPage.doctorId})"><span class="icon-trash-o"></span> 删除</a> </div></td>--%>
                    <td>
                        <c:choose>
                            <c:when test="${(docPage.sumCount-docPage.visitCount) != 0}">
                                <div class="button-group">  <a class="button border-green" href="changeDoctorSchedule.jsp?did=${docPage.doctorId}&shiftTime=${docPage.shiftTime}&date=${docPage.date}&visitCount=${docPage.visitCount}" ><span class="icon-pencil"></span> 修改</a> </div>
                            </c:when>
                            <c:otherwise>
                                <div class="button-group"> <a class="button border-green" href="changeDoctorSchedule.jsp?did=${docPage.doctorId}&shiftTime=${docPage.shiftTime}&date=${docPage.date}&visitCount=${docPage.visitCount}" ><span class="icon-pencil"></span> 排班</a> </div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </c:forEach>
            </volist>
            <!-- 分页 -->
            <tr>
                <td colspan="7">
                    <div class="pagelist">
                        <span >总记录数：${pageInfo.total}</span>
                        <c:choose>
                            <c:when test="${pageInfo.hasPreviousPage}">
                                <a href="javascript:void(0)" style="cursor: pointer" onclick="getSelectedPage(${pageInfo.prePage})">上一页</a>
                            </c:when>
                            <c:otherwise>
                                <a >上一页</a>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach items="${pageInfo.navigatepageNums}" var="num">
                            <c:choose>
                                <c:when test="${num == pageInfo.pageNum}">
                                    <span class="current">${num}</span>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:void(0)" onclick="getSelectedPage(${num})">${num}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${pageInfo.hasNextPage}">
                                <a href="javascript:void(0)" style="cursor: pointer" onclick="getSelectedPage(${pageInfo.nextPage})">下一页</a>
                            </c:when>
                            <c:otherwise>
                                <a>下一页</a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${pageInfo.isLastPage}">
                                <a>尾页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="getSelectedPage(${pageInfo.pages})">尾页</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
            </tr>

        </table>
    </div>
</form>

</body>
</html>
<style>
    .department-list {
        display: none; /* 默认隐藏所有的下拉列表 */
    }
    .department-list.active {
        display: block; /* 当添加 active 类时，显示下拉列表 */
    }

</style>