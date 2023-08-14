package net.approval.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonObject;

import net.dept.db.Dept;
import net.member.db.Member;

public class ApprovalDAO {
	private DataSource ds;

	public ApprovalDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB연결 실패: " + ex);
		}
	}

	public ArrayList<Dept> getDeptList() {
		// TODO Auto-generated method stub

		ArrayList<Dept> deptList = new ArrayList<>();

		String sql = "SELECT D_NUM, D_NAME " + "FROM DEPT " + "ORDER BY D_LEVEL, D_NUM ";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery();) {

				while (rs.next()) {
					Dept d = new Dept();
					d.setD_num(rs.getInt(1));
					d.setD_name(rs.getString(2));

					deptList.add(d);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return deptList;
	}

	public ArrayList<Member> getMemberList() {
		// TODO Auto-generated method stub
		ArrayList<Member> memberlist = new ArrayList<>();

		String sql = "SELECT M_NAME, D_NUM, POSITION.M_JOB, M_NUM " + "FROM MEMBER LEFT JOIN POSITION "
				+ "ON MEMBER.P_NUM = POSITION.P_NUM";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery();) {

				while (rs.next()) {
					Member m = new Member();
					m.setM_NAME(rs.getString(1));
					m.setD_NUM(rs.getInt(2));
					m.setP_NUM(rs.getString(3));
					m.setM_NUM(rs.getInt(4));

					memberlist.add(m);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberlist;
	}

	public Member getMemberDetail(String session_id) {
		Member m = new Member();

		String sql = "SELECT M.M_NAME, D.D_NAME, P.M_JOB " + "FROM MEMBER M " + "JOIN POSITION P ON M.P_NUM = P.P_NUM "
				+ "JOIN DEPT D ON M.D_NUM = D.D_NUM " + "WHERE M.M_ID = ?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, session_id);

			try (ResultSet rs = pstmt.executeQuery();) {

				if (rs.next()) {
					m.setM_NAME(rs.getString(1));
					m.setM_ID(rs.getString(2));
					m.setP_NUM(rs.getString(3));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return m;
	}

	public ArrayList<ApprovalTemplate> getTemplateList() {
		// TODO Auto-generated method stub

		ArrayList<ApprovalTemplate> atList = new ArrayList<>();

		String sql = "SELECT * FROM APPROVALTEMPLATE";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery();) {

				while (rs.next()) {
					ApprovalTemplate at = new ApprovalTemplate();
					at.setA_template_num(rs.getInt(1));
					at.setA_template_name(rs.getString(2));
					at.setA_template_content(rs.getString(3));

					atList.add(at);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return atList;
	}

	public String getTemplate(String tnum) {
		// TODO Auto-generated method stub
		String sql = "SELECT A_TEMPLATE_CONTENT FROM APPROVALTEMPLATE "
				+ "WHERE A_TEMPLATE_NUM = ?";
		
		String result = "";

		try (Connection conn = ds.getConnection(); 
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, tnum);
			try (ResultSet rs = pstmt.executeQuery();) {

				if (rs.next()) {
					result = rs.getString(1);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
