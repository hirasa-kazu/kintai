package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import tool.Action;


//管理者ユーザー新規登録クラス
public class RegistAdminUserAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		UserDAO dao = UserDAO.getInstance();

		boolean insertUserChkFlag = false;

		try {

			insertUserChkFlag = dao.insertUser(userId, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if(insertUserChkFlag) {
			session.setAttribute("loginUserId", userId);
			response.sendRedirect("completion.jsp");
		} else {
			response.sendRedirect("regist_error_adminuser.jsp");

		}
		return "regist_error_adminuser.jsp";
	}
}
