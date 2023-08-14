package net.cafeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafemember.action.Action;
import net.cafemember.action.ActionForward;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // Redirect:off => 주소가 변하지 않는 forward 방식
		forward.setPath("cafeAdmin/boardWrite.jsp");
		return forward;
	}
}
