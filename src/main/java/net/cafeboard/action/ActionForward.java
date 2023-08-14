package net.cafeboard.action;

// Action 인터페이스에서 명령을 수행하고 결과 값을 가지고 이동하는 클래스
// redirect 여부 및 포워딩 페이지 위치 정보를 가지고 있다.
// 이 값들을 FrontCotroller에서 ActionForward 클래스 타입으로 반환값을
// 가져오면 그 값을 화깅나는 요청 페이지로 이동
public class ActionForward {
	private boolean redirect = false;
	private String path = null;
	
	
	public ActionForward() {
		
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	
	public void setRedirect(boolean b) {
		redirect = b;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String s) {
		
	}
}