package org.agent.service.custom;

import java.util.List;

import org.agent.dao.contact.ContactMapper;
import org.agent.dao.custom.CustomMapper;
import org.agent.pojo.Contact;
import org.agent.pojo.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomSereviceImpl implements CustomSerevice {
	@Autowired
	private CustomMapper customMapper;
	@Autowired
	private ContactMapper contactMapper;

	@Override
	public List<Custom> getList(Custom custom) {
		// TODO Auto-generated method stub
		return customMapper.getList(custom);
	}

	@Override
	public int addCustom(Custom custom) {
		// TODO Auto-generated method stub
		return customMapper.addCustom(custom);
	}

	@Override
	public int modifyCustom(Custom custom) {
		// TODO Auto-generated method stub
		return customMapper.modifyCustom(custom);
	}

	@Override
	public int deleteCustom(Custom custom) {
		// TODO Auto-generated method stub
		return customMapper.deleteCustom(custom);
	}

	@Override
	public int count(Custom custom) {
		// TODO Auto-generated method stub
		return customMapper.count(custom);
	}

	@Override
	public int isExitCustomName(Custom custom) {
		// TODO Auto-generated method stub
		return customMapper.isExitCustomName(custom);
	}

	@Override
	public Custom getCustomById(Custom custom) {
		// TODO Auto-generated method stub
		Contact contact = new Contact();
		contact.setCustomId(custom.getId());
		List<Contact> contactList = contactMapper.getContactList(contact);
		// custom.setConstants(contactList);
		custom = customMapper.getCustomById(custom);
		custom.setConstants(contactList);
		System.err.println(custom.getConstants());
		return custom;
	}

	@Override
	public List<Custom> getCustomBySearch(Custom custom) {
		// TODO Auto-generated method stub
		return customMapper.getCustomBySearch(custom);
	}

	@Override
	public int modifyCustomAtatus(Custom custom) {
		// TODO Auto-generated method stub
		return customMapper.modifyCustomAtatus(custom);
	}

	@Transactional
	@Override
	public boolean tx_addCustomContact(Custom custom, List<Contact> contacts) {
		try {
			customMapper.addCustom(custom);
			// int i = 1 / 0;
			for (Contact contact : contacts) {
				contact.setCustomId(custom.getId());
				contactMapper.addContact(contact);
			}

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);

		}
	}

	// @Transactional
	@Override
	public boolean tx_modifyCustomContact(Custom custom, List<Contact> contacts) {
		try {
			customMapper.modifyCustom(custom);
			Contact c = new Contact();
			c.setCustomId(custom.getId());
			// if (contacts != null && contacts.size() > 0) {
			contactMapper.deleteContact(c);// 先删除所有联系人
			for (Contact contact : contacts) {
				if (contacts != null) {
					contact.setCustomId(custom.getId());
					contactMapper.addContact(contact);// 添加更新后的联系人
				}
			}

			// }
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

}
