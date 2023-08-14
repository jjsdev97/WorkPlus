package net.cafemember.action;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberJoinAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // 주소 변경 없는 forward 방식 사용(jsp 페이지 show)
		forward.setPath("cafeMember/joinForm.jsp");
		return forward;
	}
}