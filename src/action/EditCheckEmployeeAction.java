package action;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Employee;
import bean.Section;
import dao.EmployeeDAO;
import tool.Action;

//データベースに接続して従業員コードに対応する従業員情報を送る
public class EditCheckEmployeeAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUserId") == null) {
			response.sendRedirect("login.jsp");
		} else {
			request.setCharacterEncoding("UTF-8");
			String employeeCode = (String) session.getAttribute("employeeCode");
			EmployeeDAO empdao = EmployeeDAO.getInstance();
			Employee employee = null;
			List<Section> sections = new LinkedList<Section>();

			if (employeeCode != null) {

				try {

					employee = empdao.selectEmployee(employeeCode);
					sections = empdao.getSection();

				} catch (SQLException e) {
					e.printStackTrace();
				}

				session.setAttribute("employee", employee);
				session.setAttribute("sections", sections);

				if (employee == null) {
					response.sendRedirect("menu.jsp");
				} else {
					response.sendRedirect("edit_employee.jsp");
				}

			} else {
				response.sendRedirect("show_all_employee.jsp");
			}
		}
		return "show_all_employee.jsp";
	}
}
