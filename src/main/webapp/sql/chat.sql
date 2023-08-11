select * from chat;

select * from member;

select * from dept;

select m_profilefile, m_name, chat_status, d_name
from member
left outer join dept
on member.d_num = dept.d_num
order by M_NAME;

select m_profilefile, m_name, chat_status, d_name, 
from member
left outer join dept on member.d_num = dept.d_num
left outer join position on member.p_num = position.p_num
order by m_name;

select m_profilefile, m_name, chat_status, d_name, m_job
from member
left join dept on member.d_num = dept.d_num
left join position on member.p_num = position.p_num
where m_name like '%차은우%';

select m_profilefile, m_name, chat_status, d_name, m_job, m_id
				from member 
				left join dept 
				on member.d_num = dept.d_num 
				left join position 
				on member.p_num = position.p_num 
				order by M_NAME; 