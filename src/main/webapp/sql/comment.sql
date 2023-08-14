drop table comm cascade constraints purge;
create table comm(
   num           number          primary key,
   id            varchar2(30),     -- references member(m_id) 나중에 확인 
   content       varchar2(200),
   reg_date      date,
   comment_board_num   number references board(board_num) on delete cascade, --comm 테이블참조
   comment_re_lev number(1) check(comment_re_lev in (0,1,2)), -- 원문이면 0 답글이면 1
   comment_re_seq number, -- 원문이면 0, 1레벨이면 1레벨 시퀸스 + 1
   comment_re_ref number  --원문은 자신 글번호, 답글이면 원문 글번호
   );
   -- 게시판 글이 삭제되면 참조하는 댓글도 삭제됩니다. --
   -- member가 삭제되면 member 의 댓글도 삭제됩니다. --
   
   drop sequence com_seq;
   
   -- 시퀸스를 생성합니다.
   create sequence com_seq;
   
   delete comm;
   
   select * from comm


   DROP TABLE COMM CASCADE CONSTRAINTS PURGE;

CREATE TABLE COMM(
 NUM NUMBER PRIMARY KEY,
 ID VARCHAR2(30),
 CONTENT VARCHAR2(200),
 REG_DATE DATE,
 COMMENT_BOARD_NUM NUMBER REFERENCES BOARD(BOARD_NUM) ON DELETE CASCADE,
 COMMENT_RE_LEV NUMBER(1) CHECK(COMMENT_RE_LEV IN (0,1,2)),
 COMMENT_RE_SEQ NUMBER,
 COMMENT_RE_REF NUMBER
);

DROP SEQUENCE COM_SEQ;

CREATE SEQUENCE COM_SEQ;

DELETE COMM;

SELECT * FROM COMM;