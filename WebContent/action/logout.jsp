<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("loginUserId") != null) {
		response.sendRedirect("menu.jsp");
	} else {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
<link rel="stylesheet" href="../common/css/style.css">
</head>
<body>
	<div class="header">
		<span class="big_title">勤怠管理</span>
	</div>

	<div class="menu">
		<div class="main_frame">
			<p>ログアウト完了しました！！！</p>
		</div>
	</div>

	<div class="main_wrapper">
		<div class="main_admin">本日もお疲れ様でした！！！</div>

		<div class="logout_button">
			<input type="button" class="display_button" value="ログイン画面に戻る"
				onclick="location.href='index.jsp'">
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