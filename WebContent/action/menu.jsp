<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("loginUserId") == null){
		response.sendRedirect("login.jsp");
	}else{
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../common/css/style.css">
<title>管理者用メニュー</title>
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
		<div class="employee_button">
			<a href="GetSectionEmployee.action" class="regist_button">
				<button class="display_button">従業員登録</button>
			</a>
			<a href="DisplayEmployeeList.action">
				<button class="display_button">	従業員情報一覧</button>
			</a>
		</div>

		<div class="logout_button">
			<a href="regist_adminuser.jsp">
				<button class="display_button">管理者権限を付与する</button>
			</a>
		</div>

		<div class="link_main_button">
			<form action="LogoutChk.action" method="post">
				<button class="display_button">ログアウト</button>
			</form>
		</div>

		<div class="link_main_button">
			<input type="button" class="display_button" onclick="location.href='index.jsp'" value="Topページ">
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

<% } %>