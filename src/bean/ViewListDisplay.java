package bean;

import java.sql.Date;

public class ViewListDisplay implements java.io.Serializable{

	private String employeeCode;		//従業員コード
	private String employeeName;		//従業員氏名
	private String employeeKanaName	;	//従業員氏名かな
	private String gender;				//性別
	private Date birthDay;			//誕生日
	private String sectionCode;			//部署コード
	private Date hireDate;			//入社日


	//従業員コードの所得・セット
	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}


	//従業員氏名の所得・セット
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	//従業員氏名かなの所得・セット
	public String getEmployeeKanaName() {
		return employeeKanaName;
	}

	public void setEmployeeKanaName(String employeeKanaName) {
		this.employeeKanaName = employeeKanaName;
	}


	//性別の所得・セット
	public String getGender() {
		return gender;
	}


	//文字列を受け取って対応する文字列をgenderフィールドにセットする
	public void setGender(int gender) {
		if(gender == 0) {
			this.gender = "男性";
		}else if(gender == 1) {
			this.gender = "女性";
		}
	}


	//誕生日の所得・セット
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date date) {
		this.birthDay = date;
	}


	//部署コードの所得・セット
	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}


	//入社日の所得・セット
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

}
