package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;


//従業員のログアウトをチェックするクラス
public class AttendanceLogoutChkAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		session.removeAttribute("employeeCode");

		response.sendRedirect("attendance_logout.jsp");

		return "attendance_logout.jsp";
	}
}
