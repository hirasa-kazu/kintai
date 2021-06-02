package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.ViewListDisplay;

public class ViewListDAO extends DAO{


	//唯一のインスタンスを生成する
	private static ViewListDAO instance = new ViewListDAO();


	//特定のデータベースとの接続(セッション)
	private Connection con;


	//従業員一覧表示用モデルクラスのリストを生成する。
	private List<ViewListDisplay> list = new LinkedList<ViewListDisplay>();


	//privateのため新規のインスタンスをつくらせない。
	private ViewListDAO() {
	}


	//唯一のインスタンスを取得する
	public static ViewListDAO getInstance() {
		return instance;
	}


	/**
	 * @return List<ViewListDisplay> - 従業員モデルクラスのリスト。
	 * @throws SQLException。データベース処理に問題があった場合。
	 * 従業員情報一覧全件を取得して、List<ViewListDisplay>型で返します。
	 */
	public List<ViewListDisplay> showAllList() throws Exception {
		con = getConnection();

		PreparedStatement st;
		list.clear();

		String sql = "SELECT e.employee_code,concat(e.last_name, e.first_name),"
				+ "concat(e.last_kana_name, e.first_kana_name),"
				+ "e.gender, e.birth_day,"
				+ "s.section_name, e.hire_date "
				+ "FROM employee e LEFT OUTER JOIN section s "
				+ "ON e.section_code = s.section_code";
		st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while(rs.next()){
			//レコードの値を取得
			ViewListDisplay vld = new ViewListDisplay();

			vld.setEmployeeCode(rs.getString(1));
			vld.setEmployeeName(rs.getString(2));
			vld.setEmployeeKanaName(rs.getString(3));
			vld.setGender(rs.getInt(4));
			vld.setBirthDay(rs.getDate(5));
			vld.setSectionCode(rs.getString(6));
			vld.setHireDate(rs.getDate(7));

			list.add(vld);
		}

		st.close();
		con.close();

		return list;
	}
}
