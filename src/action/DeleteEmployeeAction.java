package action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import tool.Action;

//データベースから従業員を削除するクラス
public class DeleteEmployeeAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		if(session.getAttribute("loginUserId") == null) {
			response.sendRedirect("login.jsp");
		} else {
			//リクエストのエンコーディング方式を指定
			request.setCharacterEncoding("utf-8");

			//リクエストパラメータの取得
			String employeeCode = (String)session.getAttribute("employeeCode");


			//インスタンス生成
			EmployeeDAO dao = EmployeeDAO.getInstance();


			//従業員データ取得（従業員コードに適応するもの）
			int count=0;
			try {

				count = dao.deleteEmployee(employeeCode);
				session.setAttribute("COUNT", count);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if(count != 1) {
				response.sendRedirect("delete_error_employee.jsp");
			}else{
				response.sendRedirect("delete_completion.jsp");
			}

		}
		return "delete_completion.jsp";
	}
}
