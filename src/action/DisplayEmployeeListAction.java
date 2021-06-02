package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ViewListDisplay;
import dao.ViewListDAO;
import tool.Action;

//データベースに接続して従業員情報全件一覧を取得するクラス
public class DisplayEmployeeListAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		if(session.getAttribute("loginUserId") == null) {
			response.sendRedirect("login.jsp");
		} else {

			//リクエストデータのエンコーディング
			request.setCharacterEncoding("UTF-8");

			// DAOのインスタンス生成
			 ViewListDAO dao =ViewListDAO.getInstance();

			try {


				List<ViewListDisplay> vldlist = dao.showAllList();
				session.setAttribute("vldlist", vldlist);

				response.sendRedirect("show_all_employee.jsp");


			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "menu.jsp";


	}
}
