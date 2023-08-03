package net.cafe.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CafeItemDAO {

	private DataSource ds;

	public CafeItemDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB연결 실패: " + ex);
		}
	}

	public List<CafeItemBean> getAllItems() {
		List<CafeItemBean> itemList = new ArrayList<CafeItemBean>();

		String sql = "SELECT * FROM CAFE_ITEM";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery()) {
				CafeItemBean item = new CafeItemBean();
				item.setITEM_UID(rs.getInt("ITEM_UID"));
				item.setITEM_NAME(rs.getString("ITEM_NAME"));
				item.setITEM_PRICE(rs.getInt("ITEM_PRICE"));
				item.setITEM_IMG_PATH(rs.getString("ITEM_IMG_PATH"));
				item.setITEM_DETAIL(rs.getString("ITEM_DETAIL"));
				item.setITEM_SOLDOUT(rs.getInt("ITEM_SOLDOUT"));
				item.setITEM_MENU(rs.getString("ITEM_MENU"));

				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemList;
	}

	public List<CafeItemBean> getCoffeeItems() {
	    List<CafeItemBean> itemList = new ArrayList<CafeItemBean>();

	    String sql = "SELECT * FROM CAFE_ITEM WHERE ITEM_MENU = 'coffee'";

	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            CafeItemBean item = new CafeItemBean();
	            item.setITEM_UID(rs.getInt("ITEM_UID"));
	            item.setITEM_NAME(rs.getString("ITEM_NAME"));
	            item.setITEM_PRICE(rs.getInt("ITEM_PRICE"));
//	            item.setITEM_IMG_PATH(rs.getString("ITEM_IMG_PATH"));
//	            item.setITEM_DETAIL(rs.getString("ITEM_DETAIL"));
//	            item.setITEM_SOLDOUT(rs.getInt("ITEM_SOLDOUT"));
	            item.setITEM_MENU(rs.getString("ITEM_MENU"));

	            itemList.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return itemList;
	}

	public List<CafeItemBean> getTeaItems() {
		 List<CafeItemBean> itemList = new ArrayList<CafeItemBean>();

		    String sql = "SELECT * FROM SCOTT.CAFE_ITEM WHERE ITEM_MENU = 'tea'";

		    try (Connection con = ds.getConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql);
		         ResultSet rs = pstmt.executeQuery()) {

		        while (rs.next()) {
		            CafeItemBean item = new CafeItemBean();
		            item.setITEM_UID(rs.getInt("ITEM_UID"));
		            item.setITEM_NAME(rs.getString("ITEM_NAME"));
		            item.setITEM_PRICE(rs.getInt("ITEM_PRICE"));
		            item.setITEM_IMG_PATH(rs.getString("ITEM_IMG_PATH"));
		            item.setITEM_DETAIL(rs.getString("ITEM_DETAIL"));
		            item.setITEM_SOLDOUT(rs.getInt("ITEM_SOLDOUT"));
		            item.setITEM_MENU(rs.getString("ITEM_MENU"));

		            itemList.add(item);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return itemList;
		}

}
