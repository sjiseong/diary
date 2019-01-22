package com.inc.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {
	@Pattern(regexp="[A-Za-z0-9]{5,20}",
			 message="알파벳 대소문자 및 숫자, 5 ~ 20글자")
	private String id;
	@Pattern(regexp="(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$])[A-Za-z0-9!@#$]{5,20}",
			 message="알파벳 대소문자 및 숫자 및 특수문자(!@#$)를 포함하는 5 ~ 20글자")
	private String password;
	private String passwordConfirm;
	@Pattern(regexp="[가-힣]{2,10}",
			 message="한글, 2~10글자")
	private String name;
	@Pattern(regexp="[A-Za-z0-9]{3,15}@[A-Za-z.]{3,10}",
			 message="ex)diary@diary.com")
	private String email;
	private String emailCode;
	@NotNull(message="성별을 선택해 주세요")
	@Pattern(regexp="[fm]", message="잘못된 선택")
	private String gender;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
