package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttendanceEmployeeDAO extends DAO{

	private static AttendanceEmployeeDAO instance = new AttendanceEmployeeDAO();		//唯一のインスタンス
	private Connection con;			//特定のデータベースとの接続
	private PreparedStatement st;

	//日付/時間オブジェクトの出力及び解析のためのフォーマッタ
	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private AttendanceEmployeeDAO() {}

	//唯一のインスタンスを取得
	public static AttendanceEmployeeDAO getInstance() {
		return instance;
	}


	//指定されたemployeeCodeとpasswordから従業員がログインできるかどうかチェックする
	public String loginEmployee(String employeeCode, String password)throws Exception{
		con = getConnection();

		st = con.prepareStatement("select * from employee where employee_code = '"+ employeeCode + "' and password = '"+ password + "';") ;
		ResultSet rs = st.executeQuery();

		if(rs.next()) {
			if(employeeCode.equals(rs.getString(1))) {
				if(password.equals(rs.getString(10))){
					return employeeCode;
				}
			}
		}

		st.close();
		con.close();

		return null;
	}

	//タイムカード出勤時間をテーブルに記録する
	public boolean setStartTime(String employeeCode) throws SQLException {
		con.setAutoCommit(false);
		LocalDateTime now = LocalDateTime.now();
		//既にその日のデータが追加されていたらfalseを返す
		String sql = "SELECT * from work_time WHERE employee_code = '" + employeeCode
				+ "' and work_date = '" + now.format(dateFormat) + "';";

		st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			return false;
		} else {
			sql = "INSERT INTO work_time (employee_code, work_date, start_time) VALUES ('"
			+ employeeCode + "', '" + now.format(dateFormat) + "', '"
			+ now.format(timeFormat) + "' );";
			st = con.prepareStatement(sql);
			st.executeUpdate();
			con.commit();

			st.close();
			con.close();
			return true;
		}
	}


	//タイムカード退勤時間をテーブルに記録する
	public boolean setFinishTime(String employeeCode) throws Exception {
		con = getConnection();

		con.setAutoCommit(false);
		LocalDateTime now = LocalDateTime.now();

		//出勤が押されていなかったらfalseを返す
		st = con.prepareStatement("SELECT * from work_time WHERE employee_code = '" + employeeCode
				+ "' and work_date = '" + now.format(dateFormat) + "';");
		ResultSet rs = st.executeQuery();
		if(!rs.next()) {
			return false;
		} else {
			st = con.prepareStatement("UPDATE work_time SET finish_time = '" + now.format(timeFormat)
					+ "' WHERE employee_code = '" + employeeCode + "' and work_date = '" + now.format(dateFormat) + "';");
			st.executeUpdate();
			con.commit();

			st.close();
			con.close();

			return true;
		}
	}


	//タイムカード休憩開始時間をテーブルに記録する
	public boolean setStartBreakTime(String employeeCode) throws Exception {

		con = getConnection();

		con.setAutoCommit(false);
		LocalDateTime now = LocalDateTime.now();

		//出勤が押されていなかったらfalseを返す
		st = con.prepareStatement("SELECT * from work_time WHERE employee_code = '" + employeeCode
				+ "' and work_date = '" + now.format(dateFormat) + "';");

		ResultSet rs = st.executeQuery();

		if(!rs.next()) {
			st.close();
			con.close();

			return false;
		} else {
			st = con.prepareStatement("UPDATE work_time SET break_start_time = '" + now.format(timeFormat)
					+ "' WHERE employee_code = '" + employeeCode + "' and work_date = '" + now.format(dateFormat) + "';");
			st.executeUpdate();
			con.commit();

			st.close();
			con.close();

			return true;
		}
	}


	//タイムカード休憩終了時間をテーブルに記録する
	public boolean setFinishBreakTime(String employeeCode) throws Exception {

		con =getConnection();

		con.setAutoCommit(false);
		LocalDateTime now = LocalDateTime.now();

		//出勤または休憩開始が押されていなかったらfalseを返す
		st = con.prepareStatement("SELECT * from work_time WHERE employee_code = '" + employeeCode
				+ "' and work_date = '" + now.format(dateFormat) + "';");
		ResultSet rs = st.executeQuery();

		if(!rs.next() && rs.getString(5) == null) {
			st.close();
			con.close();

			return false;
		} else {
			st = con.prepareStatement("UPDATE work_time SET break_finish_time = '" + now.format(timeFormat)
					+ "' WHERE employee_code = '" + employeeCode + "' and work_date = '" + now.format(dateFormat) + "';");
			st.executeUpdate();
			con.commit();

			st.close();
			con.close();

			return true;
		}
	}
}
