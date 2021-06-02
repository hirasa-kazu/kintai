<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% if(session.getAttribute("loginUserId") != null){
		response.sendRedirect("mune.jsp");
	}else{
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../common/css/style.css">
<title>管理者用ログイン</title>
</head>
<body>
	<div class="header">
		<span class="big_title">勤怠管理</span>
	</div>

	<div class="menu">
		<div class="main_frame">
			<p>管理者用ログイン画面</p>
		</div>
	</div>

	<div class="main_wrapper">
		<form action="LoginChk.action" method="post">
			<div class="regist_table">
				<table>
					<tr>
						<td>ユーザーID</td>
						<td>:</td>
						<td><input type="text" name="userId"></td>
					</tr>

					<tr>
						<td>パスワード</td>
						<td>:</td>
						<td><input type="text" name="password"></td>
					</tr>
				</table>
			</div>

			<div class="admin_user_button">
				<input type="submit" class="login_button" value="ログイン"><input type="reset" class="clear_button">
			</div>
		</form>
	</div>


	<div class="admin_user_button">
		<input type="button" class="display_button" onclick="location.href='index.jsp'" value="Topページ">
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
<% } %>