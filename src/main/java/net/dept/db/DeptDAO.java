package net.dept.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	
	
	
	
}
