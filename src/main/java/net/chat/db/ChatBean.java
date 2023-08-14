package net.chat.db;

import java.sql.Date;

public class ChatBean {

	
	
	private int chat_id;
	private String chat_room_name;
	private int chat_from;
	private int chat_to;
	private String chat_content;
	private Date chat_create_at;
	private String chat_file_adr;
	
	public String getChat_room_name() {
		return chat_room_name;
	}
	public void setChat_room_name(String chat_room_name) {
		this.chat_room_name = chat_room_name;
	}
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public int getChat_from() {
		return chat_from;
	}
	public void setChat_from(int chat_from) {
		this.chat_from = chat_from;
	}
	public int getChat_to() {
		return chat_to;
	}
	public void setChat_to(int chat_to) {
		this.chat_to = chat_to;
	}
	public String getChat_content() {
		return chat_content;
	}
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public Date getChat_create_at() {
		return chat_create_at;
	}
	public void setChat_create_at(Date chat_create_at) {
		this.chat_create_at = chat_create_at;
	}
	public String getChat_file_adr() {
		return chat_file_adr;
	}
	public void setChat_file_adr(String chat_file_adr) {
		this.chat_file_adr = chat_file_adr;
	}
}
