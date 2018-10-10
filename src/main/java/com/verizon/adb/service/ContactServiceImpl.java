package com.verizon.adb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.adb.dao.ContactRepository;
import com.verizon.adb.model.Contact;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRepository contactRepo;

	@Override
	public Contact getContactById(long contactId) {
        Contact contact=null;
		
		if(contactRepo.existsById(contactId)) {
			contact=contactRepo.findById(contactId).get();
		}
		/*Optional<Contact> optContact=contactRepo.findById(contactId);
		if(optContact.isPresent()) {
			contact=optContact.get();
		}*/
		return contact;
	}

	@Override
	public List<Contact> getAllContacts() {
		// TODO Auto-generated method stub
		return contactRepo.findAll();
	}

	@Override
	public Contact addContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactRepo.save(contact);
	}

	@Override
	public Contact updateContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactRepo.save(contact);           //save is used for both add and update. if adding persist will be used and if contact is found for updating then merge is used.
	}

	@Override
	public boolean deleteContact(long contactId) {
		// TODO Auto-generated method stub
		boolean isDeleted=false;
		if(contactRepo.existsById(contactId)){
			contactRepo.deleteById(contactId);     //returns void and can throw exception. for that check is to be applied
			isDeleted=true;
		}
		
		return isDeleted;
	}

	@Override
	public boolean existsByMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		return contactRepo.existsByMobileNumber(mobileNumber);
	}

	@Override
	public boolean existsByEmailId(String emailId) {
		// TODO Auto-generated method stub
		return contactRepo.existsByEmailId(emailId);
	}

	@Override
	public Contact findByMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		return contactRepo.findByMobileNumber(mobileNumber);
	}

	@Override
	public Contact findByEmailId(String emailId) {
		// TODO Auto-generated method stub
		return contactRepo.findByEmailId(emailId);
	}

	@Override
	public List<Contact> findAllByLastName(String lastName) {
		// TODO Auto-generated method stub
		return contactRepo.findAllByLastName(lastName);
	}
	
	
	
	
}
