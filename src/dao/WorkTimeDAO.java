package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import bean.WorkTime;


//画面表示のために出退勤時刻管理データベースと繋ぐDAOクラス
public class WorkTimeDAO extends DAO{

	private static WorkTimeDAO instance = new WorkTimeDAO(); //唯一のインスタンス
	private Connection con;
	private PreparedStatement st;

	//privateのため新規のインスタンスをつくらせない
	private WorkTimeDAO() {
	}


	//唯一のインスタンスを取得する
	public static WorkTimeDAO getInstance() {
		return instance;
	}



	//出勤情報が既に存在しているかチェックする
	public String selectStartTime(String employeeCode) throws Exception {

		con = getConnection();

		st = con.prepareStatement("SELECT * FROM work_time WHERE employee_code = '" + employeeCode +
				"' AND work_date = '" + LocalDate.now() + "';");
		ResultSet rs = st.executeQuery();

		if(rs.next()) {
			st.close();
			con.close();

			return "disable";
		} else {
			st.close();
			con.close();

			return null;
		}
	}

	//退勤情報が既に存在しているかチェックする
	public String selectFinishTime(String employeeCode) throws Exception {

		con = getConnection();

		st = con.prepareStatement("SELECT * FROM work_time WHERE employee_code = '" + employeeCode +
				"' AND work_date = '" + LocalDate.now() + "';");
		ResultSet rs = st.executeQuery();
		if(rs.next() && rs.getString(4) != null) {
			st.close();
			con.close();

			return "disable";
		} else {
			st.close();
			con.close();

			return null;
		}
	}

	//休憩開始情報が既に存在しているかチェックする
	public String selectStartBreak(String employeeCode) throws Exception {

		con = getConnection();

		st = con.prepareStatement("SELECT * FROM work_time WHERE employee_code = '" + employeeCode +
				"' AND work_date = '" + LocalDate.now() + "';");
		ResultSet rs = st.executeQuery();
		if(rs.next() && rs.getString(5) != null) {
			st.close();
			con.close();

			return "disable";
		} else {
			st.close();
			con.close();

			return null;
		}
	}


	//休憩終了情報が既に存在しているかチェックする
	public String selectFinishBreak(String employeeCode) throws Exception {

		con = getConnection();

		st = con.prepareStatement("SELECT * FROM work_time WHERE employee_code = '" + employeeCode +
				"' AND work_date = '" + LocalDate.now() + "';");
		ResultSet rs = st.executeQuery();
		if(rs.next() && rs.getString(6) != null) {
			st.close();
			con.close();

			return "disable";
		} else {
			st.close();
			con.close();

			return null;
		}
	}


	//従業員コードと月から勤務記録を抽出する
	public List<WorkTime> selectWorkTimeThisMonthList(String employeeCode,String thisMonth)
			throws Exception {

		con = getConnection();

		List<WorkTime> workTimeThisMonthList = new LinkedList<WorkTime>();
		st = con.prepareStatement("SELECT * FROM work_time WHERE employee_code = '" + employeeCode +
		"' AND work_date LIKE '" + thisMonth + "%';");
		ResultSet rs = st.executeQuery();

		while(rs.next()){
			WorkTime workTime = new WorkTime();
			workTime.setWorkDate(LocalDate.parse(rs.getString(2),
					DateTimeFormatter.ofPattern("yyyy-MM-dd")) );
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			if(rs.getString(3) != null) {
				LocalTime startTime = LocalTime.parse(rs.getString(3), dtf);
				workTime.setStartTime(startTime);
			}
			if(rs.getString(4) != null) {
				LocalTime finishTime = LocalTime.parse(rs.getString(4), dtf);
				workTime.setFinishTime(finishTime);
			}
			if(rs.getString(5) != null) {
				LocalTime breakStartTime = LocalTime.parse(rs.getString(5), dtf);
				workTime.setBreakStartTime(breakStartTime);
			}
			if(rs.getString(6) != null) {
				LocalTime breakFinishTime = LocalTime.parse(rs.getString(6), dtf);
				workTime.setBreakFinishTime(breakFinishTime);
			}
			if(rs.getString(5) != null && rs.getString(6) != null) {
				//自動計算セットするメソッド
				workTime.getBreakTime();
			}
			if(rs.getString(3) != null && rs.getString(4) != null) {
				//自動計算セットするメソッド
				workTime.getWorkingHours();
			}
			workTimeThisMonthList.add(workTime);
		}
		st.close();
		con.close();

		return workTimeThisMonthList;
	}

}
