package com.cafe.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.db.CafeItemBean;

public class CafeAdeAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CafeItemBean cafeItem = new CafeItemBean();
		
		request.setAttribute("cafeItem", cafeItem);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("order/ade.jsp");
		
		return forward;
	}
}
