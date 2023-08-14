package net.cafe.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import net.cafe.db.CafeItemBean;
import net.cafe.db.CafeItemDAO;

public class CafeSideAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CafeItemDAO idao = new CafeItemDAO();
		List<CafeItemBean> sideItemList = idao.getItemsByMenu("side"); // 에이드 메뉴 항목 가져오기

		// 웹 페이지에 sideItemList 속성 추가(웹 페이지 생명 주기 동안 접근 가능)
		request.setAttribute("sideItemList", sideItemList);
		// jsp에서 ${sideItemList}로 접근 가능

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<CafeItemBean> cartItemList = (List<CafeItemBean>) session.getAttribute("cartItemList");

		if (cartItemList == null) {
			cartItemList = new ArrayList<>();
		}

		String selectedItemUId = request.getParameter("selectedItemUId");
		String deleteItemUId = request.getParameter("deleteItemUId");

		if (selectedItemUId != null) {
			int itemUId = Integer.parseInt(selectedItemUId);
			CafeItemBean selectedItem = findItemById(itemUId, sideItemList);
			if (selectedItem != null) {
				cartItemList.add(selectedItem);
			}
//          request.setCharacterEncoding("euc-kr");
			session.setAttribute("cartItemList", cartItemList); // 장바구니 정보를 세션에 저장
//          request.setAttribute("cartItemList", cartItemList);
//          request.setAttribute("totalPrice", calculateTotalPrice(cartItemList));
			session.setAttribute("totalPrice", calculateTotalPrice(cartItemList));
		}

		if (deleteItemUId != null) {
			int itemUIdToDelete = Integer.parseInt(deleteItemUId);
			CafeItemBean itemToDelete = findItemById(itemUIdToDelete, cartItemList);
			if (itemToDelete != null) {
				cartItemList.remove(itemToDelete);
			}
			session.setAttribute("cartItemList", cartItemList); // 변경된 장바구니 목록을 세션에 저장
			session.setAttribute("totalPrice", calculateTotalPrice(cartItemList)); // 변경된 총 가격 업데이트
		}

		String ajaxRequest = request.getHeader("X-Requested-With");
		String fetchCart = request.getParameter("fetchCart");

		if ("true".equalsIgnoreCase(fetchCart) && ajaxRequest != null) {
		    // AJAX 요청으로 장바구니 정보만 가져오는 경우
		    Map<String, Object> responseMap = new HashMap<>();
		    responseMap.put("cartItemList", cartItemList);
		    responseMap.put("totalPrice", calculateTotalPrice(cartItemList));

		    StringBuilder cartItemNames = new StringBuilder();
		    for(CafeItemBean item : cartItemList) {
		        cartItemNames.append(item.getITEM_NAME()).append(", ");
		    }
		    if(cartItemNames.length() > 0) {
		        cartItemNames.setLength(cartItemNames.length() - 2); // 마지막 ", " 제거
		    }

		    responseMap.put("cartItemNames", cartItemNames.toString());
		    response.setContentType("application/json");
		    response.getWriter().write(new Gson().toJson(responseMap));
		    return null; 
		}  else if(ajaxRequest != null) {
			// AJAX 요청 처리
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(cartItemList));
            return null; 
        }
		
		else {
			// 일반 페이지 요청 처리
			ActionForward forward = new ActionForward();
			forward.setPath("cafe/side.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}

	private CafeItemBean findItemById(int itemUId, List<CafeItemBean> itemList) {
		for (CafeItemBean item : itemList) {
			if (item.getITEM_UID() == itemUId) {
				return item;
			}
		}
		return null;
	}

	private int calculateTotalPrice(List<CafeItemBean> cartItemList) {
		int totalPrice = 0;
		for (CafeItemBean item : cartItemList) {
			totalPrice += item.getITEM_PRICE();
		}
		return totalPrice;
	}
}
