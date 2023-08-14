package net.cafemember.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafemember.db.cafeMember;
import net.cafemember.db.cafeMemberDAO;

public class MemberSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ActionForward forward = new ActionForward();
		cafeMemberDAO mdao = new cafeMemberDAO();

		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		System.out.println("넘어온 페이지 = " + page);

		List<cafeMember> list = null;
		int listcount = 0;
		int index = -1;

		String search_word = "";

		// 메뉴-관리자-회원정보 클릭한 경우
		// 또는 메뉴-관리자-회원정보 클릭 후 페이지 클릭한 경우
		// memberList.net?page=2&search_field=-1&search_word=)

		if (request.getParameter("search_word") == null || request.getParameter("search_word").equals("")) {
			listcount = mdao.getListCount();
			list = mdao.getList(page,limit);
			
		}else { //검색을 클릭한 경우
			index = Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] { "id", "name" };
			search_word = request.getParameter("search_word");
			listcount = mdao.getListCount(search_field[index], search_word);
			list = mdao.getList(search_field[index], search_word, page, limit);
		}
		
		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지 수 = " + maxpage);
		
		int startpage = ((page-1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 =" +endpage);
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 =" +startpage);
		
		if(endpage > maxpage) endpage = maxpage;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		
		request.setAttribute("startpage", startpage);
		
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount);
		request.setAttribute("totallist", list);
		request.setAttribute("search_field", index);
		request.setAttribute("search_word", search_word);
		
		forward.setPath("cafeMember/memberList.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
