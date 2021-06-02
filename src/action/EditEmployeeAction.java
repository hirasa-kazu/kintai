package action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Employee;
import dao.EmployeeDAO;
import tool.Action;


//データベースに接続して編集した従業員情報を更新する
public class EditEmployeeAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		Employee employee = (Employee) session.getAttribute("employee");

		request.setCharacterEncoding("utf-8");
		String employeeCode = request.getParameter("employeeCode");
		String lastName = request.getParameter("lastName");
		employee.setLastName(lastName);
		String lastKanaName = request.getParameter("lastKanaName");
		employee.setLastKanaName(lastKanaName);
		String firstName = request.getParameter("firstName");
		employee.setFirstName(firstName);
		String firstKanaName = request.getParameter("firstKanaName");
		employee.setFirstKanaName(firstKanaName);
		int gender = Integer.parseInt(request.getParameter("gender"));
		employee.setGender(gender);
		String birthDay = request.getParameter("birthDay");
		employee.setBirthDay(birthDay);
		String hireDate = request.getParameter("hireDate");
		employee.setHireDate(hireDate);
		String sectionCode = request.getParameter("section_code");
		employee.setSectionCode(sectionCode);

		EmployeeDAO empdao = EmployeeDAO.getInstance();
		try {

			employee = empdao.updateEmployee(employee);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.removeAttribute("employeeCode");
		session.setAttribute("employee", employee);
		response.sendRedirect("edit_completion.jsp");

		return "edit_completion.jsp";
	}

}
