package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.dept.db.Dept;

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
				+ "(M_NUM, M_NAME, M_ID, M_PASS, E_NUM, VERIFY_EMAIL, M_HIREDATE, "
				+ " D_NUM, R_ADMIT, M_STATUS, CHAT_STATUS, M_ADMIN) "
				+ " values (" + max_sql + ",?,?,?,?,?,SYSDATE,?,?,?,?,?) ";
				
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
		String sql = "select M_ID, M_PASS from member where M_ID = ? ";
		
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

	public Member findId(String name, int empnum) {
		String sql = "select M_NAME, M_ID from member "
					+ "	where M_NAME = ? AND E_NUM = ? ";
		Member member = null;
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, name);
			pstmt.setInt(2, empnum);
			
			
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					member = new Member();
					member.setM_NAME(rs.getString(1));
					member.setM_ID(rs.getString(2));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
		}

	public int resetPass(String pass) {
		
		int result = 0;
		String sql = "update member set M_PASS = ? "
				+ " where VERIFY_EMAIL = ? ";
		
		return 0;
	}

	public HashMap<String, Integer> getListCountAdmit() {
		String sql = "select (SELECT COUNT(*) 		FROM MEMBER		WHERE R_ADMIT = '1' and  M_ID != 'admin' ) wait"
				+ "       , (SELECT COUNT(*)   	 FROM MEMBER    WHERE M_STATUS = '2' and  M_ID != 'admin' ) stop"
				+ "       , (SELECT COUNT(*)		FROM MEMBER		WHERE R_ADMIT = '2' and  M_ID != 'admin' ) complete"
				+ " FROM DUAL   ";
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					map.put("wait", rs.getInt(1));
					map.put("stop", rs.getInt(2));
					map.put("complete", rs.getInt(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCountAdmit() 에러: " + ex);
		}
		
		
		return map;
	}

	public List<Member> getList(int page, int limit ) {
		
		//page : 페이지
		//limit : 페이지 당 목록의 수
		//조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문
		
		List<Member> list = new ArrayList<Member>();
		String member_list_sql = "select * from(select b.*, rownum rnum "
							+ "					from(select * from member where M_ID != 'admin' AND M_ADMIN = '1' "
							+ "						 order by M_NAME)b "
							+ "	  		   where rownum <= ? ) "
							+ "   where rnum >= ? and rnum <= ? ";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(member_list_sql);){
			
			//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, ...
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			try(ResultSet rs = pstmt.executeQuery()){
				
				while(rs.next()) {
					Member m = new Member();
					m.setM_NUM(rs.getInt(1));
					m.setM_NAME(rs.getString(2));
					m.setM_ID(rs.getString(3));
					m.setM_PASS(rs.getString(4));
					m.setE_NUM(rs.getInt(5));
					m.setVERIFY_EMAIL(rs.getString(6));
					m.setM_HIREDATE(rs.getDate(7));
					m.setD_NUM(rs.getInt(8));
					m.setP_NUM(rs.getString(9));
					m.setR_ADMIT(rs.getString(10));
					m.setM_STATUS(rs.getString(11));
					m.setCHAT_STATUS(rs.getString(12));
					m.setM_ADMIN(rs.getString(13));
					
					list.add(m);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount()에러 : " + ex);
		}
		
		return null;
	}

	public ArrayList<Member> getList1(int page, int limit, String sql) {

		//page : 페이지
		//limit : 페이지 당 목록의 수
		//조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문
		
		/*원래 쿼리문
		 * 
		 * "select * from(select b.*, rownum rnum "
							+ "					from(select * from member where M_ID != 'admin' AND " + sql 
							+ "						 order by M_NAME)b "
							+ "	  		 	    where rownum <= ? ) "
							+ "  		   where rnum >= ? and rnum <= ? ";
		 */
		
		ArrayList<Member> list = new ArrayList<Member>();
		String member_list_sql = "select * from(select b.*, rownum rnum "
				+ "					from(select * from member "
				+ "									left join dept on member.d_num = dept.d_num "
				+ "									left join position on member.p_num = position.p_num "
				+ "								  where M_ID != 'admin' AND " + sql 
				+ "						 order by M_NAME)b "
				+ "	  		   where rownum <= ? ) "
				+ "   where rnum >= ? and rnum <= ? ";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(member_list_sql);){
			
			//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, ...
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			
			try(ResultSet rs = pstmt.executeQuery()){
				
				while(rs.next()) {
					Member m = new Member();
					m.setM_NUM(rs.getInt(1));
					m.setM_NAME(rs.getString(2));
					m.setM_ID(rs.getString(3));
					m.setM_PASS(rs.getString(4));
					m.setE_NUM(rs.getInt(5));
					m.setVERIFY_EMAIL(rs.getString(6));
					m.setM_HIREDATE(rs.getDate(7));
					m.setD_NUM(rs.getInt(8));
					m.setP_NUM(rs.getString(9));
					m.setR_ADMIT(rs.getString(10));
					m.setM_STATUS(rs.getString(11));
					m.setCHAT_STATUS(rs.getString(12));
					m.setM_ADMIN(rs.getString(13));
					m.setM_PROFILEFILE(rs.getString(14));
					m.setD_NAME(rs.getString(17));
					m.setM_JOB(rs.getString(22));
					
					list.add(m);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount()에러 : " + ex);
		} 
		
		return list;
	}

	public int confirm(int dnum, String pnum, String id) {
		int result = 0;
		String sql = "update member set R_ADMIT = '2' , D_NUM = ?, P_NUM = ? "
				+ "where M_ID = ? ";
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, dnum );
			pstmt.setString(2, pnum );
			pstmt.setString(3, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int idDelete(String id) {
		int result = 0;
		String sql = "delete from member "
				+ "where M_ID = ? ";
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				pstmt.setString(1, id);
				result = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return result;
	}

	public int clearblock(String id) {
		int result = 0;
		String sql = "update member set M_STATUS = '1' "
				+ "where M_ID = ? ";
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int getListCount(String field, String search_word) {
		int x = 0;
		String sql = "select count(*) from member "
				+ "where " + field + " like ? ";
		System.out.println(sql);
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, "%" + search_word + "%");
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					x = rs.getInt(1);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount()에러: " + ex);
		}
		
		return x;
	}

	public List<Member> getList(String field, String search_word, int page, int limit) {
		
		List<Member> list = new ArrayList<Member>();
		String sql = "select * from(select b.*, rownum rnum "
							+ "					from(select * from member where " + field + "like ? "
							+ "						 order by M_NAME)b "
							+ "	  		   		where rownum <= ? ) "
							+ "   where rnum >= ? and rnum <= ? ";
		
		System.out.println(sql);
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, "%"+ search_word + "%");
			
			//읽기 시작할 row 번호
			int startrow = (page - 1);
			int endrow = startrow + limit - 1;
			
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					Member m = new Member();
					m.setM_NUM(rs.getInt(1));
					m.setM_NAME(rs.getString(2));
					m.setM_ID(rs.getString(3));
					m.setM_PASS(rs.getString(4));
					m.setE_NUM(rs.getInt(5));
					m.setVERIFY_EMAIL(rs.getString(6));
					m.setM_HIREDATE(rs.getDate(7));
					m.setD_NUM(rs.getInt(8));
					m.setP_NUM(rs.getString(9));
					m.setR_ADMIT(rs.getString(10));
					m.setM_STATUS(rs.getString(11));
					m.setCHAT_STATUS(rs.getString(12));
					m.setM_ADMIN(rs.getString(13));
					
					list.add(m);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("에러:" + ex);
		}
		
		return list;
	}

	public Member memberinfo(String id) {
		Member m = null;
		String sql = "select * from member where M_ID = ? ";
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					m = new Member();
					m.setM_NUM(rs.getInt(1));
					m.setM_NAME(rs.getString(2));
					m.setM_ID(rs.getString(3));
					m.setM_PASS(rs.getString(4));
					m.setE_NUM(rs.getInt(5));
					m.setVERIFY_EMAIL(rs.getString(6));
					m.setM_HIREDATE(rs.getDate(7));
					m.setD_NUM(rs.getInt(8));
					m.setP_NUM(rs.getString(9));
					m.setR_ADMIT(rs.getString(10));
					m.setM_STATUS(rs.getString(11));
					m.setCHAT_STATUS(rs.getString(12));
					m.setM_ADMIN(rs.getString(13));
					m.setM_PROFILEFILE(rs.getString(14));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return m;
	}
	
	public ArrayList<Dept> deptinfo() {
		
		ArrayList<Dept> deptlist = new ArrayList<Dept>();
		String sql = "select * from dept order by D_LEVEL, D_NUM ";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					Dept d = new Dept();
					d.setD_num(rs.getInt(1));
					d.setD_name(rs.getString(2));
					d.setD_level(rs.getInt(3));
					d.setD_color(rs.getString(4));
					d.setD_upperlevel(rs.getInt(5));
					
					deptlist.add(d);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return deptlist;
	}

	public int update(Member m) {
		int result = 0;
		
		String pass_sql= "M_PASS=" + m.getM_PASS() + ",";
		if(m.getM_PASS().equals("")) {
			pass_sql="";
		}
		String sql = "update member "
				+ "set M_NAME = ?, M_ID =?, E_NUM = ?, "  + pass_sql + " M_PROFILEFILE = ?  "
				+ "where M_ID = ? ";
		System.out.println(sql);
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, m.getM_NAME());
			pstmt.setString(2, m.getM_ID());
			pstmt.setInt(3, m.getE_NUM());
			pstmt.setString(4, m.getM_PROFILEFILE());
			pstmt.setString(5, m.getM_ID());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Position> jobinfo() {
		
		ArrayList<Position> position = new ArrayList<Position>();
		String sql = "select * from position order by P_NUM ";
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				
				try(ResultSet rs = pstmt.executeQuery()){
					while(rs.next()) {
						Position p = new Position();
						p.setP_NUM(rs.getString(1));
						p.setM_JOB(rs.getString(2));
						position.add(p);
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return position;
	}

		
	
} //class end
