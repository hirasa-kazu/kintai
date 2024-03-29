<%@page
	import="bean.Employee, bean.Section, java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	if (session.getAttribute("loginUserId") == null) {
		response.sendRedirect("login.jsp");
	} else if (session.getAttribute("employeeCode") == null) {
		response.sendRedirect("menu.jsp");
	} else {
		Employee employee = (Employee) session.getAttribute("employee");
		List<Section> sections = (List<Section>) session.getAttribute("sections");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員情報編集画面</title>
<link rel="stylesheet" href="../common/css/style.css">
<script type="text/javascript" src="../common/JS/func.js"></script>

</head>
<body>
	<div class="header">
		<span class="big_title">勤怠管理</span>
	</div>

	<div class="main_wrapper">
		<%
			if (employee != null) {
		%>

		<div class="menu">
			<div class="main_frame">
				<p>従業員編集</p>
			</div>
		</div>

		<form action="EditEmployee.action" method="post" onsubmit="return chkEdit()">
			<div class="comment_show_all" id="comment_show_all">
				文字の入力、もしくは氏・名は16文字以下、<br>氏かな・名かなは24文字以下で入力してください
			</div>
			<div class="comment_error" id="comment_error">{&lt;&gt;&amp;.,/}の入力はできません</div>
			<div class="regist_table">
				<table>
					<tr>
						<td>従業員コード</td>
						<td>：</td>
						<td><%=employee.getEmployeeCode()%></td>
					</tr>
					<tr>
						<td>氏</td>
						<td>：</td>
						<td><input type="text" name="lastName"
							value="<%=employee.getLastName()%>" id="last_name"></td>
					</tr>
					<tr>
						<td>名</td>
						<td>：</td>
						<td><input type="text" name="firstName"
							value="<%=employee.getFirstName()%>" id="first_name"></td>
					</tr>
					<tr>
						<td>氏かな</td>
						<td>：</td>
						<td><input type="text" name="lastKanaName"
							id="last_kana_name" value="<%=employee.getLastKanaName()%>"></td>
					</tr>
					<tr>
						<td>名かな</td>
						<td>：</td>
						<td><input type="text" name="firstKanaName"
							value="<%=employee.getFirstKanaName()%>" id="first_kana_name"></td>
					</tr>
					<tr>
						<td>性別</td>
						<td>：</td>
						<td>
							<%
								if (employee.getGender() == 1) {
							%> <input type="radio" name="gender" value="0">男性 <input
							type="radio" name="gender" value="1" checked="checked">女性
							<%
 	} else {
 %> <input type="radio" name="gender" value="0" checked="checked">男性
							<input type="radio" name="gender" value="1">女性 <%
 	}
 %>
						</td>
					</tr>
					<tr>
						<td>部署名</td>
						<td>：</td>
						<td><select name="section_code">
								<%
									for (Section s : sections) {
								%>
								<option value="<%=s.getSectionCode()%>"><%=s.getSectionName()%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td>生年月日</td>
						<td>：</td>
						<td><input type="date" name="birthDay" id="birth_day"
							value="<%=employee.getBirthDay()%>" required></td>
						<td><div id="comment_date">本日より前の日付を入力してください</div></td>
					</tr>
					<tr>
						<td>入社日</td>
						<td>：</td>
						<td><input type="date" name="hireDate"
							value="<%=employee.getHireDate()%>" required></td>
					</tr>
				</table>

				<div class="tomenu_button">
					<div class="admin_user_button">
						<input type="submit" class="admin_user_submit" value="編集する">

						<a href="menu.jsp"> <input type="button" class="clear_button"
							value="キャンセル">
						</a>
					</div>
				</div>
			</div>
		</form>


		<%
			} else {
					response.sendRedirect("edit_error_employee.jsp");
				}
		%>

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