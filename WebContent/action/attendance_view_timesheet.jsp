<%@page import="java.time.LocalTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="bean.WorkTime"%>
<%@page import="java.util.Calendar"%>
<%@page import="bean.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String employeeCode = (String) session.getAttribute("employeeCode");
	if(employeeCode == null) {
		response.sendRedirect("attendance_login.jsp");
	} else {
		Calendar thisMonthCalendar = (Calendar) session.getAttribute("thisMonth");
		if(thisMonthCalendar == null) {
			response.sendRedirect("attendance_menu.jsp");
		} else {
			List<WorkTime> workTimeThisMonthList =
					(List<WorkTime>) session.getAttribute("workTimeThisMonthList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タイムシート</title>
<link rel="stylesheet" href="../common/css/style.css">
</head>
<body>
	<div class="header">
		<span class="big_title">勤怠管理</span>
	</div>

	<div class= "menu">
	<div class= "main_frame">
		<p>タイムシート</p>
	</div>
	</div>
		<div class="name_format">
			名前：<%= session.getAttribute("employeeName") %>
		</div>
		<div class="name_format">
			<%= thisMonthCalendar.get(Calendar.YEAR) + "年"
		+ thisMonthCalendar.get(Calendar.MONTH) + "月" %>分
		</div>

		<div class="edit_top">
		<div class="show_all_table"><table>
		<tr class="top_table"><th>日にち</th><th>曜日</th><th>出勤</th><th>退勤</th><th>休憩入り</th><th>休憩戻り</th>
		<th>休憩時間</th><th>実働時間</th></tr>

	<!-- 日にち -->
		<%
			thisMonthCalendar.add(Calendar.MONTH, -1);
			int dayOfMonth = thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			thisMonthCalendar.add(Calendar.MONTH, 1);
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH時mm分");
			DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("HH時間mm分");
			for(int i = 1; i <= dayOfMonth; i++) {
				Boolean chkDateFlag = false;
		%>
				<tr class="main_table"><td><%= i + "日" %></td>

	<!-- 曜日 -->
		<%
		String s = null;

		thisMonthCalendar.set(Calendar.DATE,1);
		int week = thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_WEEK);


		for(int a = 1; a <= 5; a++){
			for(int w = week; w <= 7; w++){
				switch(w){
					case 1:
				        s = "日曜日";
				        break;
				    case 2:
				        s = "月曜日";
				        break;
				    case 3:
				        s = "火曜日";
				        break;
				    case 4:
				        s = "水曜日";
				        break;
				    case 5:
				        s = "木曜日";
				        break;
				    case 6:
				        s = "金曜日";
				        break;
				    case 7:
				        s = "土曜日";
				        break;
				}
			}
		}


		%>

				<td><%= s %></td>

			<%
				for(WorkTime workTime: workTimeThisMonthList) {
				String workDate =
						workTime.getWorkDate().format(DateTimeFormatter.ofPattern("dd"));
					if(Integer.parseInt(workDate) == i) {
			%>
						<td><% if(workTime.getStartTime() != null) { %>
						<%= workTime.getStartTime().format(timeFormat) %><% } %></td>
						<td><% if(workTime.getFinishTime() != null) { %>
						<%= workTime.getFinishTime().format(timeFormat) %><% } %></td>
						<td><% if(workTime.getBreakStartTime() != null) { %>
						<%= workTime.getBreakStartTime().format(timeFormat) %><% } %></td>
						<td><% if(workTime.getBreakFinishTime() != null) { %>
						<%= workTime.getBreakFinishTime().format(timeFormat) %><% } %></td>
						<td><% if(workTime.getBreakTime() != null) {%>
						<%= hourFormat.format(LocalTime.MIDNIGHT.plus(workTime.getBreakTime())) %>
						<% } %></td>
						<td><% if(workTime.getWorkingHours() != null) { %>
						<%= hourFormat.format(LocalTime.MIDNIGHT.plus(workTime.getWorkingHours())) %>
						<% } %></td></tr>
			<%
						chkDateFlag = true;
						break;
					}
				}
				if(!chkDateFlag) {
			%>
					<td></td><td></td><td></td><td></td><td></td><td></td></tr>

		<%
				}
			}
		%>


		</table></div></div>


		<div class="signature_form"><table border="1">
		<tr><th>本人印</th><th>承認印</th></tr>
		<tr><td>&nbsp;</td><td>&nbsp;</td></tr>

		</table></div>
		<div class="link_button">
			<a href="attendance_menu.jsp">
				<button class="display_button">メニュー画面に戻る</button>
			</a>
		</div>

	<div class="footer_top">
	<table class="table_format" >
			<tr><th>管理者情報</th></tr>
			<tr><td class="cel">会社名</td><td>&nbsp;</td><td>インターネット北海道株式会社</td></tr>
			<tr><td class="cel">Tell</td><td>&nbsp;</td><td>011-213-1688</td>
			<tr><td class="cel">Email</td><td>&nbsp;</td><td>info@inet-do.co.jp</td></tr>
		</table>
	</div>

	<div class="footer_design">
	<footer>
		<small>© 1994 インターネット北海道株式会社</small>
	</footer>
	</div>

</body>
</html>
<%
		}
	}
%>