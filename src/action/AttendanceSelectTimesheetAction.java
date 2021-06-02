package action;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Employee;
import bean.WorkTime;
import dao.EmployeeDAO;
import dao.WorkTimeDAO;
import tool.Action;

//月を受け取って対応する出退勤時刻情報を渡すクラス
public class AttendanceSelectTimesheetAction extends Action{


	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String employeeCode = (String) session.getAttribute("employeeCode");
		String thisMonth = request.getParameter("thisMonth");
		Calendar thisMonthCalneder = Calendar.getInstance();
		thisMonthCalneder.set(Calendar.YEAR, Integer.parseInt(thisMonth.substring(0, 4)));
		thisMonthCalneder.set(Calendar.MONTH, Integer.parseInt(thisMonth.substring(5)));

		WorkTimeDAO workTimeDao = WorkTimeDAO.getInstance();
		EmployeeDAO empdao = EmployeeDAO.getInstance();
		try {

			List<WorkTime> workTimeThisMonthList =
					workTimeDao.selectWorkTimeThisMonthList(employeeCode, thisMonth);
			session.setAttribute("workTimeThisMonthList", workTimeThisMonthList);

			Employee employee = empdao.selectEmployee(employeeCode);
			String employeeName = employee.getLastName() + "　" + employee.getFirstName();
			session.setAttribute("employeeName", employeeName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.setAttribute("thisMonth", thisMonthCalneder);
		response.sendRedirect("attendance_view_timesheet.jsp");

		return "attendance_view_timesheet.jsp";
	}

}
