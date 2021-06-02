<%@page import="bean.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	if (session.getAttribute("loginUserId") == null) {
		response.sendRedirect("login.jsp");
	} else {
		Employee employee = (Employee) session.getAttribute("employee");
		if (employee != null) {
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集完了</title>
<link rel="stylesheet" href="../common/css/style.css">
</head>
<body>
	<div class="header">
		<span class="big_title">勤怠管理</span>
	</div>

	<div class="menu">
		<div class="main_frame">
			<p>
				従業員コード：<%=employee.getEmployeeCode()%>の編集完了しました
			</p>
		</div>
	</div>

	<div class="main_admin">
		<p>引き続き入力する場合は、メニューから入力をお願いいたします。</p>
		<div class="a_logout_button">
			<a href="menu.jsp">
				<button class="display_button">メニューに戻る</button>
			</a>
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
	} else {
			response.sendRedirect("menu.jsp");

		}
	}
%>