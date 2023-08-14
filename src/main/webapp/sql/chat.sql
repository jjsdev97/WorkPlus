create table chat {
	 CHAT_ID       NOT NULL NUMBER
	 CHAT_FROM   VARCHAR2(30)
 	 CHAT_TO     VARCHAR2(30)
 	 CHAT_CONTENT     VARCHAR2(800)
 	 CHAT_CREATE_AT    DATE
 	 CHAT_FILE_ADR    VARCHAR2(100)
 	 CHAT_ROOM_NAME    VARCHAR2(100)
}





select * from chat;

ALTER TABLE chat MODIFY CHAT_FROM VARCHAR2(30);
ALTER TABLE chat MODIFY CHAT_TO VARCHAR2(30);

insert into chat 
(CHAT_ID, CHAT_FROM, CHAT_TO)
values (1,  'gws12', 'nois12' );

TRUNCATE table chat;

create table CHAT_FRIEND_BOOKMARK(
    FBOOKMARK_NUM NUMBER(20) PRIMARY KEY, 
    C_SUBJECT VARCHAR2(30),
    C_OBJECT VARCHAR2(30)
);

insert into CHAT_FRIEND_BOOKMARK
(FBOOKMARK_NUM, C_SUBJECT, C_OBJECT)
values (1,  'gws12', 'nois12' );

select * from CHAT_FRIEND_BOOKMARK;

TRUNCATE table CHAT_FRIEND_BOOKMARK;

select m_profilefile, m_name, chat_status, d_name, m_job, m_id 
from member 
left join dept on member.d_num = dept.d_num 
left join position on member.p_num = position.p_num 
where member.m_id != 'admin'
order by M_NAME;
