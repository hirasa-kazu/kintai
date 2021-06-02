package action;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Section;
import dao.EmployeeDAO;
import tool.Action;

public class GetSectionEmployeeAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{

		HttpSession session = request.getSession();

		if(session.getAttribute("loginUserId") == null) {
			response.sendRedirect("login.jsp");
		} else {

			request.setCharacterEncoding("UTF-8");

			EmployeeDAO empdao = EmployeeDAO.getInstance();

			List<Section> sections = new LinkedList<Section>();

			try {

				sections = empdao.getSection();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			session.setAttribute("sections", sections);
			response.sendRedirect("regist_employee.jsp");
		}
		return "regist_employee.jsp";
	}
}
