package org.agent.service.contact;

import java.util.List;

import org.agent.pojo.Contact;

public interface ContactService {
	public List<Contact> getContactList(Contact contact);

	public int addContact(Contact contact);

	public int modifyContact(Contact contact);

	public int deleteContact(Contact contact);

}
