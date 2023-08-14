package net.cafe.db;

public class CafeOrderBean {
    private int ORDER_UID;
    private int M_NUM;
    private int TOTAL_PRICE;
    private String ORDER_TIME;
    
    
	public int getORDER_UID() {
		return ORDER_UID;
	}
	public void setORDER_UID(int oRDER_UID) {
		ORDER_UID = oRDER_UID;
	}
	public int getM_NUM() {
		return M_NUM;
	}
	public void setM_NUM(int m_NUM) {
		M_NUM = m_NUM;
	}
	public int getTOTAL_PRICE() {
		return TOTAL_PRICE;
	}
	public void setTOTAL_PRICE(int tOTAL_PRICE) {
		TOTAL_PRICE = tOTAL_PRICE;
	}
	public String getORDER_TIME() {
		return ORDER_TIME;
	}
	public void setORDER_TIME(String oRDER_TIME) {
		ORDER_TIME = oRDER_TIME;
	}
    
   
}
