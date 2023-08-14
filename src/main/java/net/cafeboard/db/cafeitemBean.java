package net.cafeboard.db;

public class cafeitemBean {
	private int    ITEM_UID;      // 글번호
	private String ITEM_NAME;     // 상품 이름 
	private String ITEM_DETAIL;   // 상품 내용
	private String ITEM_IMG_PATH; // 첨부될 파일의 이름
	private int ITEM_PRICE; 	  // 상품 가격
	private String ITEM_MENU;
	
	public String getITEM_MENU() {
		return ITEM_MENU;
	}
	public void setITEM_MENU(String iTEM_MENU) {
		ITEM_MENU = iTEM_MENU;
	}
	public int getITEM_PRICE() {
		return ITEM_PRICE;
	}
	public void setITEM_PRICE(int iTEM_PRICE) {
		ITEM_PRICE = iTEM_PRICE;
	}
	
	public int getITEM_UID() {
		return ITEM_UID;
	}
	public void setITEM_UID(int iTEM_UID) {
		ITEM_UID = iTEM_UID;
	}
	
	public String getITEM_NAME() {
		return ITEM_NAME;
	}
	public void setITEM_NAME(String iTEM_NAME) {
		ITEM_NAME = iTEM_NAME;
	}
	
	public String getITEM_DETAIL() {
		return ITEM_DETAIL;
	}
	public void setITEM_DETAIL(String iTEM_DETAIL) {
		ITEM_DETAIL = iTEM_DETAIL;
	}
	public String getITEM_IMG_PATH() {
		return ITEM_IMG_PATH;
	}
	public void setITEM_IMG_PATH(String iTEM_IMG_PATH) {
		ITEM_IMG_PATH = iTEM_IMG_PATH;
	}
	
}
