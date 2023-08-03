package net.dept.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DeptDAO {
	private DataSource ds;

	public DeptDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB연결 실패: " + ex);
		}
	}

	public int insert(Dept d) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "INSERT INTO DEPT VALUES "
				   + "((SELECT NVL(MAX(D_NUM), 0) + 1 FROM DEPT), ?, ?, ?, ?)" ;
			
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, d.getD_name());
			pstmt.setInt(2, d.getD_level());
			pstmt.setString(3, d.getD_color());
			pstmt.setInt(4, d.getD_upperlevel());
		
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("deptadd 에러 : " + e);
		}
		
		
		return result;
	}

	public JsonArray getList() {
		
		// TODO Auto-generated method stub
		String sql = "SELECT D_NUM, D_NAME, D_LEVEL, D_COLOR, D_UPPERLEVEL " 
				   + "FROM DEPT "
				   + "ORDER BY D_LEVEL, D_NUM ";
		
		JsonArray array = new JsonArray();
		Dept d = new Dept();
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			try(ResultSet rs = pstmt.executeQuery();){
				
				while(rs.next()) {
					JsonObject object = new JsonObject();
					object.addProperty("d_num", rs.getInt(1));
					object.addProperty("d_name", rs.getString(2));
					object.addProperty("d_level", rs.getInt(3));
					object.addProperty("d_color", rs.getString(4));
					object.addProperty("d_upperlevel", rs.getInt(5));
					
					array.add(object);
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return array;
	}
	
	
	
	
	
	
}
