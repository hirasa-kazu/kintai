package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import tool.Action;


//管理者ユーザーのログインをチェックするクラス
public class LoginChkAction extends Action {


	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();


		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		UserDAO userDao = UserDAO.getInstance();


		boolean loginUserChkFlag = false;


		try {
				loginUserChkFlag = userDao.loginUser(userId, password);
			}catch(Exception e) {
				e.printStackTrace();
			}

		if (loginUserChkFlag) {
			session.setAttribute("loginUserId", userId);
			response.sendRedirect("menu.jsp");
		}else {

			response.sendRedirect("login_error.jsp");
		}

			return "login.jsp";


	}
}