package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

//削除処理か編集処理か判断するクラス
public class CheckEditDeleteAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");

		String employeeCode = request.getParameter("employeeCode");

		session.setAttribute("employeeCode", employeeCode);

		if (request.getParameter("submit").equals("従業員を編集する")) {
			response.sendRedirect("EditCheckEmployee.action");

		} else if (request.getParameter("submit").equals("従業員を削除する")) {
			response.sendRedirect("DeleteEmployee.action");

		} else {
			response.sendRedirect("show_all_employee.jsp");

		}
		return "show_all_employee.jsp";

	}

}
