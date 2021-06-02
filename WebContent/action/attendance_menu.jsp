<%@page import="bean.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String employeeCode = (String) session.getAttribute("employeeCode");
	if (employeeCode == null) {
		response.sendRedirect("attendance_login.jsp");
	} else {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員用メニュー画面</title>
<link rel="stylesheet" href="../common/css/style.css">
</head>
<body>
	<div class="header">
		<span class="big_title">勤怠管理</span>
	</div>

	<div class="menu">
		<div class="main_frame">
			<p>従業員用メニュー画面</p>
		</div>
	</div>
	<div class="main_wrapper">
		<div class="employee_button">
			<a href="AttendanceViewTimecard.action" class="regist_button">
				<button class="a_display_button">打刻する</button>
			</a> <a href="attendance_select_timesheet.jsp" class="regist_button">
				<button class="a_display_button">月次報告する</button>
			</a>
		</div>

		<div class="logout_button">
			<form action="AttendanceLogoutChk.action" method="post">
				<input type="submit" class="a_display_button" value="ログアウト">
			</form>
		</div>
		<div class="display_top">
			<input type="button" class="display_button"
				onclick="index.jsp" value="Topページ">
		</div>
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
%>