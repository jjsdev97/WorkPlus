package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource ds;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex );
		}
	}
	
	public int isId(String id) {
		int result = -1; // DB에 해당 id가 없습니다.
		String sql = "select M_ID from member where M_ID = ? ";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, id);
			
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					result = 0; // DB에 해당 id가 있습니다.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} // isId end
	

	public int joininsert(Member m) {
		int result = 0;
		String max_sql = "(select nvl(max(m_num),0)+1 from member)";
		
		String sql = "insert into member "
				+ "(M_NUM, M_NAME, M_ID, M_PASS, E_NUM, VERIFY_EMAIL, "
				+ "D_NUM, R_ADMIT, M_STATUS, CHAT_STATUS, M_ADMIN) "
				+ "values (" + max_sql + ",?,?,?,?,?,?,?,?,?,?) ";
				
		try( Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setString(1, m.getM_NAME());
			pstmt.setString(2, m.getM_ID());
			pstmt.setString(3, m.getM_PASS());
			pstmt.setInt(4, m.getE_NUM());
			pstmt.setString(5, m.getVERIFY_EMAIL());
			pstmt.setInt(6, m.getD_NUM());
			pstmt.setString(7, m.getR_ADMIT()); 
			pstmt.setString(8, m.getM_STATUS()); 
			pstmt.setString(9, m.getCHAT_STATUS());
			pstmt.setString(10, m.getM_ADMIN());
			
			result = pstmt.executeUpdate();	//삽입 성공시 result는 1
			 } 	catch (Exception e) {
					e.printStackTrace();
			}
					
				return result;
	}

	//로그인
	public int isId(String id, String pass) {
		int result = -1; //DB에 해당 id가 없습니다.
		String sql = "selec tM_ID, M_PASS from member where M_ID = ?";
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					if(rs.getString(2).equals(pass)) {
						result = 1; //아이디와 비밀번호 일치하는 경우
					} else {
						result = 0;	//비밀번호가 일치하지 않는 경우
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}//isId end
	
}
