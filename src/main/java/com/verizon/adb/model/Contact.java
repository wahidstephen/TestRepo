package com.verizon.adb.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.lang.NonNull;

@Entity
@Table(name="contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long contactId;
	
	@NotEmpty(message="first name cant be empty")
	@Size(min=5,max=25,message="firstname can be between 5 to 25 chars")
	private String firstName;
	
	@NotEmpty(message="last name cant be empty")
	@Size(min=5,max=25,message="lastname can be between 5 to 25 chars")
	private String lastName;
	
	//@NotEmpty(message="date of birth cant be empty")
	@Column(name="dob")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate dateOfBirth;
	@Email
	@NotNull
	private String emailId;
	@Column(name="mno")
	@NotEmpty
	@Pattern(regexp="\\d{10}",message="mobile number can only be 10 digits")
	private String mobileNumber;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	
}
