package action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttendanceEmployeeDAO;
import tool.Action;

//従業員のログインをチェックするクラス
public class AttendanceLoginChkAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		String employeeCode = request.getParameter("employeeCode");
		String password = request.getParameter("password");

		AttendanceEmployeeDAO attendEmpDao = AttendanceEmployeeDAO.getInstance();

		try {

			employeeCode = attendEmpDao.loginEmployee(employeeCode, password);
			session.setAttribute("employeeCode", employeeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (employeeCode != null) {
			response.sendRedirect("attendance_menu.jsp");
		} else {
			response.sendRedirect("attendance_login_error.jsp");

		}
		return "attendance_login.jsp";
	}
}
