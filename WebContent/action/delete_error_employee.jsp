<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	if (session.getAttribute("loginUserId") == null) {
		response.sendRedirect("login.jsp");
	} else {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員情報削除エラー</title>
<link rel="stylesheet" href="../common/css/style.css">
</head>
<body>
	<div class="header">
		<span class="big_title">勤怠管理</span>
	</div>

	<div class="menu">
		<div class="main_frame">
			<p>⚠入力内容をご確認ください</p>
		</div>
	</div>
	<div class="main_wrapper">

		<div class="main_admin">
			入力内容に不備がありました。<br> 従業員一覧画面に戻り、再入力して下さい
		</div>

		<div class="logout_button">
			<input type="button" class="display_button" value="従業員一覧画面に戻る"
				onclick="location.href='show_all_employee.jsp';">
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