package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Employee;
import bean.Section;

public class EmployeeDAO extends DAO{

	//唯一のインスタンスを生成する
	private static EmployeeDAO instance = new EmployeeDAO();


	//特定のデータベースとの接続(セッション)。
	private Connection con;


	private PreparedStatement st;


	//privateのため新規のインスタンスをつくらせない
	private EmployeeDAO() {
	}


	//唯一のインスタンスを取得する
	public static EmployeeDAO getInstance() {
		return instance;
	}



	public boolean insertEmployee(String lastName, String firstName, String lastKanaName, String firstKanaName,
			int gender, String birthDay, String sectionCode, String hireDate, String password) throws Exception {

		con = getConnection();

		// オートコミットを無効にする
		con.setAutoCommit(false);
		boolean Flag = false;

		st = con.prepareStatement("SELECT MAX(employee_code) FROM employee;");
		ResultSet rs = st.executeQuery();

		int code = 1;
		String employeeCode;


		if(rs.next()){
			if(rs.getString(1) ==null) {
				code = 1;
			}else {
				code = Integer.parseInt(rs.getString(1).substring(1)) + 1;
			}

		}

		employeeCode = "E" + String.format("%03d", code);

		String sql2 = "insert into employee values('"+ employeeCode +"','" + lastName + "', '" + firstName + "', '" + lastKanaName + "',"
				+ "'" + firstKanaName + "', '" + gender + "', '" + birthDay + "' , '" + sectionCode + "', '" + hireDate + "',  '" + password + "');";

		int result = st.executeUpdate(sql2);

		// 正しく追加できた場合、コミットする
		if (result > 0) {
			Flag = true;
			con.commit();
		}

		st.close();
		con.close();

		return Flag;
	}


	//従業員の情報をアップデートする。（従業員情報編集機能)
	public Employee updateEmployee(Employee employee) throws Exception {
		con = getConnection();

		con.setAutoCommit(false);

		st = con.prepareStatement("UPDATE employee SET last_name = '" + employee.getLastName()
		+ "', first_name = '" + employee.getFirstName()
		+ "', last_kana_name = '"+ employee.getLastKanaName()
		+ "', first_kana_name = '" + employee.getFirstKanaName()
		+ "', gender = '" + employee.getGender()
		+ "', birth_day = '" + employee.getBirthDay()
		+ "', section_code = '" + employee.getSectionCode()
		+ "', hire_date = '" + employee.getHireDate()
		+ "' WHERE employee_code = '" + employee.getEmployeeCode() + "';");

		int count = st.executeUpdate();

		if (count > 0) {
			con.commit();
		}

		st.close();
		con.close();

		return employee;
	}

	/**
	 * @param employeeCode - 従業員コード。
	 * @return 対応する従業員、存在しない場合null。
	 * @throws SQLException。データベース処理に問題があった場合。
	 * 指定されたemployeeCodeから従業員の情報を取得して、Employee型で返す。
	 */
	public Employee selectEmployee(String employeeCode) throws Exception {
		con = getConnection();

		st = con.prepareStatement("SELECT * FROM employee WHERE employee_code = '"
				+ employeeCode + "';") ;

		ResultSet rs = st.executeQuery();

		Employee employee = null;

		if(rs.next() && rs.getString(1).equals(employeeCode)){
			employee = new Employee();
			employee.setEmployeeCode(rs.getString(1));
			employee.setLastName(rs.getString(2));
			employee.setFirstName(rs.getString(3));
			employee.setLastKanaName(rs.getString(4));
			employee.setFirstKanaName(rs.getString(5));
			employee.setGender(rs.getInt(6));
			employee.setBirthDay(rs.getString(7));
			employee.setSectionCode(rs.getString(8));
			employee.setHireDate(rs.getString(9));

		}

		st.close();
		con.close();

		return employee;
	}


	/**
	 * @param employeeCode - 従業員コード。
	 * @return データベースに従業員情報を更新出来たら1、出来なかったら0。
	 * @throws SQLException。データベース処理に問題があった場合。
	 * 指定されたemployeeCodeの従業員情報を削除する。
	 */
	public int deleteEmployee(String employeeCode) throws Exception {
		con = getConnection();

		con.setAutoCommit(false);
		st = con.prepareStatement("DELETE FROM employee WHERE employee_code = '"
				+ employeeCode + "';");
		int count = st.executeUpdate();

		if (count > 0) {
			con.commit();
		}

		st.close();
		con.close();

		return count;

	}

	/**
	 * @return List<Section> - 部署一覧。
	 * @throws SQLException。データベース処理に問題があった場合。
	 * 表示のために部署一覧を取得して、List<Section>型で返す。
	 */
	public List<Section> getSection() throws Exception {
		con = getConnection();

		st = con.prepareStatement("select * from section order by section_code;");


		ResultSet rs = st.executeQuery();

		List<Section> sections = new LinkedList<Section>();

		while(rs.next()){
			//レコードの値を取得
			Section se = new Section();
			se.setSectionCode(rs.getString(1));
			se.setSectionName(rs.getString(2));
			sections.add(se);
		}

		st.close();
		con.close();

		return sections;

	}


}
