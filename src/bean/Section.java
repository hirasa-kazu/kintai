package bean;

public class Section implements java.io.Serializable{

	private String sectionCode;			//部署コード
	private String sectionName;			//部署名


	//部署コードの所得・セット
	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}


	//部署名の所得・セット
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
}
