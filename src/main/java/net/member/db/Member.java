package net.member.db;

import java.sql.Date;

public class Member {
	private int M_NUM;				//회원번호
	private String M_NAME;			//이름
	private String M_ID;			//회원 아이디
	private String M_PASS;			//회원 비밀번호
	private int E_NUM;				//사원번호
	private String VERIFY_EMAIL;	//이메일(외부 인증 이메일)
	private Date M_HIREDATE;		//입사일
	private int D_NUM;				//부서번호(FK DEPT)
	private String P_NUM;			//직책 ENUM 클래스
	private String R_ADMIT;		//가입승인
	private String M_STATUS;		//이용정지
	private String CHAT_STATUS;		//접속상태
	private String M_ADMIN;			//어드민관리 - USER(기본값), ADMIN
	private String M_PROFILEFILE;	//프로필 파일 저장
	private String D_NAME;			//조인 부서명
	private String M_JOB;			//조인 직책명
	private String c_object;		//조인 bookmark 친구 
	
	public String getC_object() {
		return c_object;
	}
	public void setC_object(String c_object) {
		this.c_object = c_object;
	}
	public String getM_JOB() {
		return M_JOB;
	}
	public void setM_JOB(String m_JOB) {
		M_JOB = m_JOB;
	}
	public String getD_NAME() {
		return D_NAME;
	}
	public void setD_NAME(String d_NAME) {
		D_NAME = d_NAME;
	}
	public String getM_PROFILEFILE() {
		return M_PROFILEFILE;
	}
	public void setM_PROFILEFILE(String m_PROFILEFILE) {
		M_PROFILEFILE = m_PROFILEFILE;
	}
	public String getR_ADMIT() {
		return R_ADMIT;
	}
	public String getM_STATUS() {
		return M_STATUS;
	}
	public int getM_NUM() {
		return M_NUM;
	}
	public void setM_NUM(int m_NUM) {
		M_NUM = m_NUM;
	}
	public String getM_NAME() {
		return M_NAME;
	}
	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public String getM_PASS() {
		return M_PASS;
	}
	public void setM_PASS(String m_PASS) {
		M_PASS = m_PASS;
	}
	public int getE_NUM() {
		return E_NUM;
	}
	public void setE_NUM(int e_NUM) {
		E_NUM = e_NUM;
	}
	public String getVERIFY_EMAIL() {
		return VERIFY_EMAIL;
	}
	public void setVERIFY_EMAIL(String vERIFY_EMAIL) {
		VERIFY_EMAIL = vERIFY_EMAIL;
	}
	public Date getM_HIREDATE() {
		return M_HIREDATE;
	}
	public void setM_HIREDATE(Date m_HIREDATE) {
		M_HIREDATE = m_HIREDATE;
	}
	public int getD_NUM() {
		return D_NUM;
	}
	public void setD_NUM(int d_NUM) {
		D_NUM = d_NUM;
	}
	public String getP_NUM() {
		return P_NUM;
	}
	public void setP_NUM(String p_NUM) {
		P_NUM = p_NUM;
	}
	public String isR_ADMIT() {
		return R_ADMIT;
	}
	public void setR_ADMIT(String r_ADMIT) {
		R_ADMIT = r_ADMIT;
	}
	public String isM_STATUS() {
		return M_STATUS;
	}
	public void setM_STATUS(String m_STATUS) {
		M_STATUS = m_STATUS;
	}
	public String getCHAT_STATUS() {
		return CHAT_STATUS;
	}
	public void setCHAT_STATUS(String cHAT_STATUS) {
		CHAT_STATUS = cHAT_STATUS;
	}
	public String getM_ADMIN() {
		return M_ADMIN;
	}
	public void setM_ADMIN(String m_ADMIN) {
		M_ADMIN = m_ADMIN;
	}
	
	
	
}
