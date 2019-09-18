package org.agent.service.contact;

import java.util.List;

import org.agent.dao.contact.ContactMapper;
import org.agent.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactMapper contactMapper;

	@Override
	public List<Contact> getContactList(Contact contact) {
		// TODO Auto-generated method stub
		return contactMapper.getContactList(contact);
	}

	@Override
	public int addContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactMapper.addContact(contact);
	}

	@Override
	public int modifyContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactMapper.modifyContact(contact);
	}

	@Override
	public int deleteContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactMapper.deleteContact(contact);
	}

}
