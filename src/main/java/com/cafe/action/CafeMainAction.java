package com.cafe.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.db.CafeItemBean;
import com.cafe.db.CafeItemDAO;

public class CafeMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CafeItemDAO idao = new CafeItemDAO();
		List<CafeItemBean> itemList = idao.getAllItems();
		
		request.setAttribute("itemList", itemList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("cafe/main.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
