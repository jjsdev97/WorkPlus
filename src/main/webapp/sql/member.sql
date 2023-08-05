
create table MEMBER(
M_NUM			NUMBER(3)				PRIMARY KEY, 	 -- 회원번호
M_NAME			VARCHAR2(30)			NOT NULL,	 	 -- 이름	
M_ID			VARCHAR2(30)			NOT NULL,		 -- 회원 아이디
M_PASS			VARCHAR2(30)			NOT NULL,		 -- 비밀번호
E_NUM			NUMBER(10)				NOT NULL,		 -- 사원번호
VERIFY_EMAIL	VARCHAR2(50)			NOT NULL,		 -- 이메일 (외부 이메일)
M_HIREDATE		DATE,									 -- 입사일 (관리자가 설정)		
D_NUM			NUMBER(5)				NOT NULL,		 -- 부서번호(FK DEPT) > 기본값 0으로 설정
P_NUM			VARCHAR2(30),					 		 -- 직책 ENUM 클래스
R_ADMIT			VARCHAR2(3) CHECK(R_ADMIT = '1' OR R_ADMIT = '2'),	 -- 가입승인
M_STATUS		VARCHAR2(3) CHECK(M_STATUS = '1' OR M_STATUS = '2'), -- 이용정지
CHAT_STATUS		VARCHAR2(15),							 -- 접속상태
M_ADMIN			VARCHAR2(30)	NOT NULL		 		 -- USER(기본값), ADMIN
);

select * from member;

insert into member
values(1, '관리자', 'admin', '1', 100001, 'admin@nave.com', NULL , 0, NULL, NULL, NULL , NULL,'ADMIN' );
insert into member
values(1, '관리자', 'admin', '1', 100001, 'admin@nave.com', NULL , 0, NULL, NULL, NULL , NULL,'ADMIN' );
insert into member
values(1, '관리자', 'admin', '1', 100001, 'admin@nave.com', NULL , 0, NULL, NULL, NULL , NULL,'ADMIN' );

delete from member;


select M_ID from member where M_ID = 'jspp1';