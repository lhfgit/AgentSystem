package org.agent.pojo;

/**
 * 联系人
 * 
 * @author lhf
 *
 */
public class Contact extends Base {
	private Integer customId;
	private String contactName;
	private String contactTel;
	private String contactFax;
	private String contactEmail;
	private String contactRole;

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Integer customId, String contactName, String contactTel, String contactFax, String contactEmail,
			String contactRole) {
		super();
		this.customId = customId;
		this.contactName = contactName;
		this.contactTel = contactTel;
		this.contactFax = contactFax;
		this.contactEmail = contactEmail;
		this.contactRole = contactRole;
	}

	@Override
	public String toString() {
		return "Contact [customId=" + customId + ", contactName=" + contactName + ", contactTel=" + contactTel
				+ ", contactFax=" + contactFax + ", contactEmail=" + contactEmail + ", contactRole=" + contactRole
				+ ", getId()=" + getId() + "]";
	}

	public Integer getCustomId() {
		return customId;
	}

	public void setCustomId(Integer customId) {
		this.customId = customId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactFax() {
		return contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactRole() {
		return contactRole;
	}

	public void setContactRole(String contactRole) {
		this.contactRole = contactRole;
	}

}
