package net.cafe.db;

public class CafeOrderBean {
    private int itemUid;
    private String itemName;
    private int itemPrice;
    private String itemImgPath;
    private String itemDetail;
    
    
    public int getItemUid() {
		return itemUid;
	}
	public void setItemUid(int itemUid) {
		this.itemUid = itemUid;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemImgPath() {
		return itemImgPath;
	}
	public void setItemImgPath(String itemImgPath) {
		this.itemImgPath = itemImgPath;
	}
	public String getItemDetail() {
		return itemDetail;
	}
	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}
	public int getItemSoldOut() {
		return itemSoldOut;
	}
	public void setItemSoldOut(int itemSoldOut) {
		this.itemSoldOut = itemSoldOut;
	}
	public String getItemMenu() {
		return itemMenu;
	}
	public void setItemMenu(String itemMenu) {
		this.itemMenu = itemMenu;
	}
	private int itemSoldOut;
    private String itemMenu;

    // 생성자, getter, setter 메서드 생략

}
