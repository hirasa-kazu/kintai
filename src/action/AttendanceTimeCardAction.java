package action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttendanceEmployeeDAO;
import tool.Action;


//打刻情報を受け取ってデータベースに反映させるクラス
public class AttendanceTimeCardAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String employeeCode = (String) session.getAttribute("employeeCode");

		//どのボタンが押されたかをチェック
		String attendance = request.getParameter("attendance");
		AttendanceEmployeeDAO attendEmpDao = AttendanceEmployeeDAO.getInstance();

		boolean Flag = false;
		try {

			if (attendance.equals("出勤処理")) {
				Flag = attendEmpDao.setStartTime(employeeCode);
			} else if (attendance.equals("退勤処理")) {
				Flag = attendEmpDao.setFinishTime(employeeCode);
			} else if (attendance.equals("休憩開始処理")) {
				Flag = attendEmpDao.setStartBreakTime(employeeCode);
			} else if (attendance.equals("休憩終了処理")) {
				Flag = attendEmpDao.setFinishBreakTime(employeeCode);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (Flag) {
			session.setAttribute("attendance", attendance);
			response.sendRedirect("attendance_completion.jsp");
		} else {
			response.sendRedirect("attendance_timecard_error.jsp");
		}
		return "location.href='attendance_timecard_error.jsp'";
	}

}
