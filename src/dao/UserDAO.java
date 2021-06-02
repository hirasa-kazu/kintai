package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

public class UserDAO extends DAO{

	private static UserDAO instance = new UserDAO();		 //唯一のインスタンスとする
	private Connection con;			// 特定のデータベースとの接続(セッション)

	private PreparedStatement st;

	private UserDAO() {}

	//唯一のインスタンスを所得
	public static UserDAO getInstance() {
		return instance;
	}


	//管理者ユーザーがログインできるかどうかチェックする
	public boolean loginUser (String userId, String password) throws Exception {

		//String hashed = BCrypt.hashpw(password, BCrypt.gensalt());


		con = getConnection();
		boolean loginUserChkFlag = false;


		st = con.prepareStatement("select * from user where user_id = ? and password = ?");

		st.setString(1, userId);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();

		//String sql = "select * from user where user_id = ? and password = ?"

		//String hashed = rs.getString("password");

	/*		if(rs.next()) {
				if(userId.equals(rs.getString(1))) {
					if(BCrypt.checkpw(password, rs.getString(2))) {
						loginUserChkFlag = true;
					}
				}
			}*/

		if(rs.next()) {
			if(userId.equals(rs.getString(1))) {
				if(password.equals(rs.getString(2))) {
					loginUserChkFlag = true;
				}
			}
		}

		st.close();
		con.close();

		return loginUserChkFlag;
	}



	//管理者ユーザーの情報を新規追加する。
	public boolean insertUser(String userId, String password) throws Exception {
		con = getConnection();

		// オートコミットを無効にする
		con.setAutoCommit(false);

		boolean insertUserChkFlag = false;

		// user_idがマッチしたユーザレコードを取得する
		st = con.prepareStatement("select * from user where user_id = ?");
		st.setString(1, userId);

		ResultSet rs = st.executeQuery();


		//パスワードをハッシュ化
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());


		//テーブルのuser_idをチェックして同じ値がなかったら、userテーブルにinsertする
		if(!rs.next() || !userId.equals(rs.getString(1))) {
			st = con.prepareStatement("insert into user (user_id, password)values(?, ?) ");
			st.setString(1, userId);
			st.setString(2, hashed);
			int result = st.executeUpdate();

			// 正しく追加できた場合、コミットする
			if (result > 0) {
				insertUserChkFlag = true;
				con.commit();
			}
		}

		st.close();
		con.close();

		return insertUserChkFlag;
	}

}
