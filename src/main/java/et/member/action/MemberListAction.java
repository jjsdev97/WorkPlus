package et.member.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dept.db.Dept;
import net.member.db.Member;
import net.member.db.MemberDAO;
import net.member.db.Position;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward actionforward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		
		int page1 = 1;
		int page2 = 1;
		int page3 = 1;
		
		int limit = 10;
		if(request.getParameter("page1")!=null) {
			page1 = Integer.parseInt(request.getParameter("page1"));
		}
		if(request.getParameter("page2")!=null) {
			page2 = Integer.parseInt(request.getParameter("page2"));
		}
		if(request.getParameter("page3")!=null) {
			page3 = Integer.parseInt(request.getParameter("page3"));
		}
		
		
		
		
		
		
		
		
		System.out.println("넘어온 페이지 = " + page1);
		System.out.println("넘어온 페이지 = " + page2);
		System.out.println("넘어온 페이지 = " + page3);
		
		int listcount_search = 0;
		int index = -1;   //search_field에 존재하지 않는 값으로 초기화
		
		String search_word = "";
		
		HashMap<String, Integer> map=null;
	
		ArrayList<Member>  searchlist = null;
		ArrayList<Member>  list1 = null;
		ArrayList<Member>  list2 = null;
		ArrayList<Member>  list3 = null;
		ArrayList<Dept> deptlist = null;
		ArrayList<Position> position = null;
		int listcount1 = 0;
		int listcount2 = 0;
		int listcount3 = 0;
		
		
		// 1)관리자 페이지 - 사용자 관리 클릭한 경우(가입대기인 회원목록 표시)
		if(request.getParameter("search_word") == null || request.getParameter("search_word").equals("")){
			//가입대기인 회원 목록 표시
			map = mdao.getListCountAdmit();
			list1 = mdao.getList1(page1, limit, "R_ADMIT = '1'");  //가입승인
			list2 = mdao.getList1(page2, limit, "M_STATUS = '2'"); //이용중지
			list3 = mdao.getList1(page3, 5, "R_ADMIT = '2'");  //승인완료
			
			
			deptlist = mdao.deptinfo();
			position = mdao.jobinfo();
			
		} else {  //검색을 클릭한 경우 
			
			index = Integer.parseInt(request.getParameter("search_field")); //목록의 값 index로 가져오기
			String[] search_field = new String[] {"M_NAME", "E_NUM", "D_NUM"};
			String[] status = new String[] {"R_ADMIT = '1'",  "M_STATUS = '2'", "R_ADMIT = '2'" };
			search_word = request.getParameter("search_word");
			String tab = request.getParameter("tab");
			int tab_int = Integer.parseInt(tab)-1;
			listcount_search = mdao.getListCount(search_field[index], search_word, status[tab_int] );
			map = new HashMap<String, Integer> ();
		    String[] key={"wait", "stop", "complete"};
		    map.put(key[tab_int], listcount_search);
		    searchlist = mdao.getList(search_field[index], search_word, page1, limit,  status[tab_int]);
		}
		
		//int maxpage = (listcount + limit - 1) / limit;
		//System.out.println("총 페이지 수 = " + maxpage );
		
		//int startpage = ((page - 1) / 10) * 10 + 1;
		//int endpage = startpage + 10 - 1;
		//System.out.println("현재 페이지에 보여줄 마지막 페이지 수: " + endpage);
		//System.out.println("현재 페이지에 보여줄 시작 페이지 수: " + startpage);
		
		//if( endpage > maxpage ) endpage = maxpage;
		
		request.setAttribute("page1", page1); //현재 페이지 수
		request.setAttribute("page2", page2); //현재 페이지 수
		request.setAttribute("page3", page3); //현재 페이지 수
		//request.setAttribute("maxpage", maxpage); //최대 페이지 수
		
		//request.setAttribute("startpage", startpage);
		//request.setAttribute("endpage", endpage);
		//request.setAttribute("listcount", listcount);
		request.setAttribute("search_field", index);
		request.setAttribute("search_word", search_word);
		
		request.setAttribute("totallist1", list1);
		request.setAttribute("totallist2", list2);
		request.setAttribute("totallist3", list3);
		request.setAttribute("searchlist", searchlist);
		
		request.setAttribute("listcount", map);
		request.setAttribute("deptlist", deptlist);
		request.setAttribute("position", position);
		request.setAttribute("tab", request.getParameter("tab"));
		actionforward.setPath("member/M-memberList.jsp");
		actionforward.setRedirect(false);
		
		return actionforward;
	}

}
