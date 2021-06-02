package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import tool.Action;


//従業員新規登録クラス
public class RegistEmployeeAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		// リクエストパラメータの取得
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String lastKanaName = request.getParameter("lastKanaName");
		String firstKanaName = request.getParameter("firstKanaName");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String birthDay = request.getParameter("birthDay");
		String sectionCode = request.getParameter("sectionCode");
		String hireDate = request.getParameter("hireDate");
		String password = request.getParameter("password");

		// DAOの生成
		EmployeeDAO dao = EmployeeDAO.getInstance();

		boolean insertUserChkFlag = false;

		//DBにかかわる処理
		try {

			insertUserChkFlag = dao.insertEmployee(lastName, firstName, lastKanaName, firstKanaName, gender, birthDay,
					sectionCode,hireDate, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!insertUserChkFlag) {
			lastName = null;

		}

		session.setAttribute("lastName", lastName);
		//sessionにおいて、JSPに渡す
		if (insertUserChkFlag) {
			response.sendRedirect("completion.jsp");

		} else {
			response.sendRedirect("regist_error_employee.jsp");

		}
		return "regist_error_employee.jsp";
	}
}
