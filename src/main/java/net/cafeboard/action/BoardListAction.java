package net.cafeboard.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.cafeboard.db.BoardDAO;
import net.cafeboard.db.cafeitemBean;
import net.cafemember.action.Action;
import net.cafemember.action.ActionForward;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDAO boarddao = new BoardDAO();
		List<cafeitemBean> itemlist = new ArrayList<cafeitemBean>();
		
		int page = 1; // 보여줄 page
		int limit = 10; // 한 페이지의 보여줄 게시판 목록의 수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지: " + page);
		
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit: " + limit);
		
		// list 수(글의 수)
		int listcount = boarddao.getListCount();
		
		// 리스트 받아오기
		itemlist = boarddao.getBoardList(page, limit);
		
		int maxpage = (listcount + limit - 1)/limit;
		System.out.println("total page: " + maxpage);
		
		// 페이지의 시작 숫자 ex)1 11 21...
		int startpage = ((page-1)/10) * 10 + 1;
		System.out.println("현재 페이지에서 보여줄 시작 페이지 수: " + startpage);
		
		// 페이지의 마지막 숫자 ex)10 20 30...
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에서 보여줄 마지막 페이지 수: " + endpage);
		
		// 25페이지가 maxpage일 경우 30 페이지가 아닌 25까지만 표시
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		String state = request.getParameter("state");
		
		if(state == null) { // ☆request로 담기
			System.out.println("state==null");
			request.setAttribute("page", page); // 현재 페이지 수 
			request.setAttribute("maxpage", maxpage); // 최대 페이지 수
			request.setAttribute("startpage", startpage); // 헌재 페이지 시작 숫자
			request.setAttribute("endpage", endpage); // 헌재 페이지 마지막 숫자
			request.setAttribute("listcount", listcount);
			request.setAttribute("itemlist", itemlist);
			
			request.setAttribute("limit", limit);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false); // forward 방식(주소 변경없이 값 그대로 들고 가기 위해)
			
			forward.setPath("cafeAdmin/boardList.jsp");
			return forward;
			
		} else { // ajax 방식의 데이터 설정 및 요청
			System.out.println("state=ajax");
			// ☆JsonObject로 담기
			JsonObject object = new JsonObject();
			object.addProperty("page", page);
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			
			//★JsonObject에는 List 형식을 담을 수 있는 addProperty()가 존재하지 않는다.
			// List 형식을 JsonElement로 바꾸어 주어야 object에 저장 가능(List => JsonElement)
			JsonElement je = new Gson().toJsonTree(itemlist); 
			System.out.println("itemlist: " + je.toString());
// void com.goole.gson.JsonObject.add(String property, JsonElement value) 메소드를 통해 저장
			object.add("itemlist", je);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(object);
			System.out.println(object.toString());
			return null;
		}
	}
}
