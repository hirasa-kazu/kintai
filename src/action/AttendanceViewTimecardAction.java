package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.WorkTimeDAO;
import tool.Action;


//月を受け取ってデータベースに接続して対応する出退勤時刻情報を画面に送るクラス
public class AttendanceViewTimecardAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String employeeCode = (String) session.getAttribute("employeeCode");
		WorkTimeDAO workTimeDao = WorkTimeDAO.getInstance();

		try {

			String startCheck = workTimeDao.selectStartTime(employeeCode);
			session.setAttribute("startWork", startCheck);

			if(startCheck != null) {
				String finishCheck = workTimeDao.selectFinishTime(employeeCode);
				session.setAttribute("finishWork", finishCheck);

				//退勤ボタンが押されてなかったら休憩開始・終了ボタンが押せる
				if (finishCheck == null) {
					String startBreakCheck = workTimeDao.selectStartBreak(employeeCode);
					session.setAttribute("startBreak", startBreakCheck);

					//休憩開始ボタンが押されていたら休憩終了ボタンが押せる
					if (startBreakCheck != null) {
						String finishBreakCheck = workTimeDao.selectFinishBreak(employeeCode);
						session.setAttribute("finishBreak", finishBreakCheck);

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect("attendance_timecard.jsp");
		return "attendance_timecard.jsp";
	}
}
