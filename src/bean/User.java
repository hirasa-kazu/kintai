package bean;

//管理者クラス

public class User implements java.io.Serializable{

	private String userId;			//ユーザーID
	private String password;		//パスワード


	//ユーザーIDの所得・セット
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	//パスワードの所得・セット
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}