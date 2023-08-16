package net.approval.db;

public class Approval {
	private int approval_num;
	private String approval_subject;
	private String approval_writer;
	private String approval_date;
	private int approval_period;
	private int approval_template;
	private String approval_document;
	private String approval_content;
	
	private String approval_template_str;
	

	public int getApproval_num() {
		return approval_num;
	}

	public void setApproval_num(int approval_num) {
		this.approval_num = approval_num;
	}

	public String getApproval_subject() {
		return approval_subject;
	}

	public void setApproval_subject(String approval_subject) {
		this.approval_subject = approval_subject;
	}

	public String getApproval_writer() {
		return approval_writer;
	}

	public void setApproval_writer(String approval_writer) {
		this.approval_writer = approval_writer;
	}

	public String getApproval_date() {
		return approval_date;
	}

	public void setApproval_date(String approval_date) {
		this.approval_date = approval_date;
	}

	public int getApproval_period() {
		return approval_period;
	}

	public void setApproval_period(int approval_period) {
		this.approval_period = approval_period;
	}

	public int getApproval_template() {
		return approval_template;
	}

	public void setApproval_template(int approval_template) {
		this.approval_template = approval_template;
	}

	public String getApproval_document() {
		return approval_document;
	}

	public void setApproval_document(String approval_document) {
		this.approval_document = approval_document;
	}

	public String getApproval_content() {
		return approval_content;
	}

	public void setApproval_content(String approval_content) {
		this.approval_content = approval_content;
	}

	public String getApproval_template_str() {
		return approval_template_str;
	}

	public void setApproval_template_str(String approval_template_str) {
		this.approval_template_str = approval_template_str;
	}

}
