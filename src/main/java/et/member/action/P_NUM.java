package et.member.action;

public enum P_NUM {
	ceo("대표이사"),
	GeneralManager("부장"),
	Manager("과장"),
	AssistantManager("대리"),
	staff("사원");
	
	
	
	private final String value;
	
	P_NUM(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
