package net.dept.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.member.db.Member;

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

	public int delete(int num) {
		// TODO Auto-generated method stub
		String sql = "delete from dept where d_num = ? ";
		int result = 0;
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public JsonObject select(int num) {
		// TODO Auto-generated method stub
		String sql = "SELECT D_NAME, D_LEVEL, D_COLOR, D_UPPERLEVEL " 
				   + "FROM DEPT "
				   + "WHERE D_NUM = ? ";
		
		JsonObject object = new JsonObject();
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, num);
			
			try(ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					object.addProperty("dname", rs.getString(1));
					object.addProperty("dlevel", rs.getInt(2));
					object.addProperty("dcolor", rs.getString(3));
					object.addProperty("dupperlevel", rs.getInt(4));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public int update(Dept d) {
		// TODO Auto-generated method stub
		String sql = "UPDATE DEPT "
				+ "SET D_NAME = ?, D_LEVEL = ?, D_COLOR = ?, D_UPPERLEVEL =? "
				+ "WHERE D_NUM = ? ";
		
		int result = 0;
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, d.getD_name());
			pstmt.setInt(2, d.getD_level());
			pstmt.setString(3, d.getD_color());
			pstmt.setInt(4, d.getD_upperlevel());
			pstmt.setInt(5, d.getD_num());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(result==0) {
			System.out.println("dept update실패");
		}
		
		
		return result;
	}

	public ArrayList<Member> getMemberList() {
		// TODO Auto-generated method stub
		ArrayList<Member> memberlist = new ArrayList<>();
		
		String sql = "SELECT M_NAME, D_NUM, POSITION.M_JOB, M_ID "
				+ "FROM MEMBER LEFT JOIN POSITION "
				+ "ON MEMBER.P_NUM = POSITION.P_NUM "
				+ "WHERE MEMBER.R_ADMIT = '2' AND MEMBER.M_STATUS = '1'";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			try(ResultSet rs = pstmt.executeQuery();){
				
				while(rs.next()){
					Member m = new Member();
					m.setM_NAME(rs.getString(1));
					m.setD_NUM(rs.getInt(2));
					m.setP_NUM(rs.getString(3));
					m.setM_ID(rs.getString(4));
					
					memberlist.add(m);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return memberlist;
	}

	public ArrayList<Dept> getDeptName() {
		// TODO Auto-generated method stub
		
		ArrayList<Dept> deptlist = new ArrayList<>();

	
		String sql = "SELECT D.D_NUM, D.D_NAME, COUNT(M.M_NUM) AS MEMBER_COUNT "
					+"FROM DEPT D LEFT JOIN MEMBER M ON D.D_NUM = M.D_NUM "
					+"WHERE M.R_ADMIT = '2' AND M.M_STATUS = '1' "
					+"GROUP BY D.D_NUM, D.D_NAME "
					+"ORDER BY D.D_NUM ";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				try(ResultSet rs = pstmt.executeQuery();){
					
					while(rs.next()){
						Dept d = new Dept();
						d.setD_num(rs.getInt(1));
						d.setD_name(rs.getString(2));
						d.setMemberCnt(rs.getInt(3));
												
						deptlist.add(d);
					}
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		
		return deptlist;
	}
	

	
	
	
	
	
	
	
	
}
