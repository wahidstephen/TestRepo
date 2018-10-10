package com.verizon.adb.restapi;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.adb.model.Contact;
import com.verizon.adb.service.ContactService;

@RestController
@CrossOrigin
@RequestMapping("/contacts")
public class ContactApi {

	@Autowired
	private ContactService contactService;
	
	@GetMapping
	public ResponseEntity<List<Contact>> getAllContacts(){
		return new ResponseEntity<List<Contact>>(contactService.getAllContacts(), HttpStatus.OK);
	}
	
	/*@GetMapping("{field}/{srhValue}")
	public ResponseEntity<List<Contact>> getAllContacts(@PathVariable("field") String fieldBy,@PathVariable("srhValue") String searchValue){
		
		List<Contact> results=null;
		
		switch(fieldBy) {
		case "mobileNumber":
			Contact cByMobNum =contactService.findByMobileNumber(searchValue);
			if(cByMobNum!=null) {
				results=Collections.singletonList(cByMobNum);
			}
			break;
		case "emailId":
			Contact cByEmail =contactService.findByEmailId(searchValue);
			if(cByEmail!=null) {
				results=Collections.singletonList(cByEmail);
			}
			break;
		case "lastName":
			results =contactService.findAllByLastName(searchValue);
			
			break;
		}
		
		ResponseEntity<List<Contact>> resp=null;
		
		if(results==null) {
			resp=new ResponseEntity<List<Contact>>(HttpStatus.BAD_REQUEST);
		}
		else if(results.size()==0) {
			resp=new ResponseEntity<List<Contact>>(HttpStatus.NOT_FOUND);
		}
		else {
			resp=new ResponseEntity<List<Contact>>(results,HttpStatus.OK);
		}
		
		return resp;
	}*/
	
	
	/*@GetMapping("{field}/{srhValue}")
	public ResponseEntity<List<Contact>> getAllContacts(@PathVariable("field") String fieldBy,@PathVariable("srhValue") String searchValue){
		
		
		ResponseEntity<List<Contact>> resp=null;
		
		switch(fieldBy) {
		case "mobileNumber":
			Contact cByMobNum =contactService.findByMobileNumber(searchValue);
			if(cByMobNum!=null) {
				resp=new ResponseEntity<> (Collections.singletonList(cByMobNum),HttpStatus.OK);
			}
			else {
				resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		case "emailId":
			Contact cByEmail =contactService.findByEmailId(searchValue);
			if(cByEmail!=null) {
				resp=new ResponseEntity<>(Collections.singletonList(cByEmail),HttpStatus.OK);
			}
			else {
				resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		case "lastName":
			List<Contact> results =contactService.findAllByLastName(searchValue);
			if(results!=null && results.size()!=0) {
				resp=new ResponseEntity<>(results,HttpStatus.OK);
			}
			else {
				resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
			
		default:
			resp=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			break;
		}
		
		
		return resp;
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable("id") long contactId){
		ResponseEntity<Contact> resp;
		Contact c=contactService.getContactById(contactId);
		if(c==null) {
			resp=new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
		else {
			resp=new ResponseEntity<>(c,HttpStatus.OK);
		}
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
		ResponseEntity<Contact> resp=null;
		
		if(contactService.existsByEmailId(contact.getEmailId())) {
			resp=new ResponseEntity<Contact>(HttpStatus.ALREADY_REPORTED);
		}
		if(contactService.existsByMobileNumber(contact.getMobileNumber())) {
			resp=new ResponseEntity<Contact>(HttpStatus.ALREADY_REPORTED);		
			}
		
		if(resp==null) {
			Contact c=contactService.addContact(contact);
			
			if(c==null) {
				resp=new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
			}
			else {
				resp=new ResponseEntity<Contact>(c,HttpStatus.OK);
			}
		}
		
		return resp;
	}
	
	@PutMapping
	public ResponseEntity<Contact> updateContact(@RequestBody Contact contact){
		ResponseEntity<Contact> resp=null;
		
		if(contactService.existsByEmailId(contact.getEmailId())) {
			resp=new ResponseEntity<Contact>(HttpStatus.ALREADY_REPORTED);
		}
		if(contactService.existsByMobileNumber(contact.getMobileNumber())) {
			resp=new ResponseEntity<Contact>(HttpStatus.ALREADY_REPORTED);		
			}
		
		if(resp==null) {
			Contact c=contactService.updateContact(contact);
			
			if(c==null) {
				resp=new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
			}
			else {
				resp=new ResponseEntity<Contact>(c,HttpStatus.OK);
			}
		}
		
		return resp;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable("id") long contactId){
		ResponseEntity<Void> resp=null;
		
		if(contactService.deleteContact(contactId)) {
			resp=new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
}
