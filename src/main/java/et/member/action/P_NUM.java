package et.member.action;

public enum P_NUM {
	1("대표이사"),
	2("기술팀"),
	3("영업팀"),
	4("인사팀"),
	5("개발팀");
	
	
	
	private final String value;
	
	P_NUM(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
