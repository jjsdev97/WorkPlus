
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
R_ADMIT			VARCHAR2(3) CHECK(R_ADMIT = '1' OR R_ADMIT = '2'),	 -- 가입승인(기본값1(승인 전) / 승인완료2)
M_STATUS		VARCHAR2(3) CHECK(M_STATUS = '1' OR M_STATUS = '2'), -- 이용정지(기본값1(이용가능) / 이용정지2)
CHAT_STATUS		VARCHAR2(15),							 -- 접속상태
M_ADMIN			VARCHAR2(30)	NOT NULL,		 		 -- USER(기본값), ADMIN
M_PROFILEFILE	VARCHAR2(50)							 -- 첨부될 프로필 사진명
F_BOOKMARK		
);

alter table member
modify P_NUM VARCHAR2(30);

drop table member purge;

select * from member;

delete from member where P_NUM = 3;

insert into member
values(1, '관리자', 'admin', '1', 100001, 'admin@nave.com', sysdate , 0, NULL, 2, 1 , 'offline','ADMIN', null);
insert into member
values(1, '관리자', 'admin', '1', 100001, 'admin@nave.com', NULL , 0, NULL, NULL, NULL , NULL,'ADMIN' );
insert into member
values(12, '과장', 'k1571', '1', 101242, 'aa456@nave.com', sysdate , 0, NULL, 1, 2 , 'offline','user' , null);

insert into member
values(25, '강직책', 'kimm211', '1', 102203, 'bj456@nave.com', sysdate , 0, 0, 1, 1 , 'offline','user',null, null );

insert into member
values(25, '강직책', 'kang211', '1', 106603, 'bj456@nave.com', sysdate , 0, 0, 1, 1 , 'offline','user',null, null );

delete from member;


select M_ID from member where M_ID = 'jspp1';

--1. 가입대기 인원수 구하기CD
SELECT COUNT(*)
FROM MEMBER
WHERE R_ADMIT = '1';

--2. 이용중지 인원수 구하기
SELECT COUNT(*)
FROM MEMBER
WHERE M_STATUS = '1';

--3. 승인완료 인원수 구하기
SELECT COUNT(*)
FROM MEMBER
WHERE R_ADMIT = '2'

select (SELECT COUNT(*) 		FROM MEMBER		WHERE R_ADMIT = '1' and  M_ID != 'admin' ) wait
       , (SELECT COUNT(*)   	 FROM MEMBER    WHERE M_STATUS = '1' and  M_ID != 'admin' ) stop
       , (SELECT COUNT(*)		FROM MEMBER		WHERE R_ADMIT = '2' and  M_ID != 'admin' ) complete
FROM DUAL;       

SELECT * 		FROM MEMBER		WHERE R_ADMIT = '1'  order by M_NAME;  --totallist1
SELECT * 		FROM MEMBER		WHERE R_ADMIT = '2'  order by M_NAME;  --totallist3
SELECT * 		FROM MEMBER		WHERE M_STATUS = '2' order by M_NAME;  --totallist2

String member_list_sql = "SELECT * 		FROM MEMBER		WHERE "
				 + sql + "  order by M_NAME";




select * 
from ( select rownum rnum, j.*
		from(
			  SELECT * 		FROM MEMBER		WHERE R_ADMIT = '1'  order by M_NAME
				)j
			where rownum <= ?
		 )
where rnum>=? and rnum<=?;
