package bean;

//従業員クラス

public class Employee implements java.io.Serializable{

	private String employeeCode;		//従業員コード
	private String lastName;			//苗字
	private String firstName;			//名前
	private String lastKanaName	;		//苗字かな
	private String firstKanaName;		//名前かな
	private int gender;				//性別
	private String birthDay;			//誕生日
	private String sectionCode;			//部署コード
	private String hireDate;			//入社日
	private String password;			//従業員パスワード


	//従業員コードの所得・セット
	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}


	//苗字の所得・セット
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	//名前の所得・セット
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	//苗字かなの所得・セット
	public String getLastKanaName() {
		return lastKanaName;
	}

	public void setLastKanaName(String lastKanaName) {
		this.lastKanaName = lastKanaName;
	}


	//名前かなの所得・セット
	public String getFirstKanaName() {
		return firstKanaName;
	}

	public void setFirstKanaName(String firstKanaName) {
		this.firstKanaName = firstKanaName;
	}


	//性別の所得・セット
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}


	//文字列を受け取って対応する数値をgenderフィールドにセットする
	public void setGender(String gender) {
		if(gender.equals("男性")) {
			this.gender = 0;
		}else if(gender.equals("女性")) {
			this.gender = 1;
		}
	}


	//誕生日の所得・セット
	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}


	//部署コードの所得・セット
	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}


	//入社日の所得・セット
	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}


	//従業員のパスワードの所得・セット
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
