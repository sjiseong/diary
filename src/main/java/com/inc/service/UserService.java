package com.inc.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.inc.dao.UserDao;
import com.inc.domain.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public User findOne(String id) {
		return userDao.findOne(id);
	}

	public boolean emailDupCheck(String email) {
		User user = userDao.findOneByEmail(email);
		if(user != null) {
			return true;
		}
		return false;
	}

	public String sendCertifyEmail(String email) throws Exception {
		String from = "commitnpush@google.com";
		String subject = "[Diary] 인증메일";
		String emailCode = getRandomCode();
		String content = 
				"주식회사 Diary입니다. 인증코드는 ["+emailCode+"]입니다. "+
				"인증코드를 입력하여 회원가입을 완료해 주세요";
		
		
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper =
				new MimeMessageHelper(msg, true, "UTF-8");
		helper.setFrom(from);
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(content);
		javaMailSender.send(msg);
		
		return emailCode;
	}
	
	// ex)"1572" "9710"
	private String getRandomCode() {
		String randomCode = "";
		for(int i = 0; i < 4; i++) {
			randomCode += (int)(Math.random()*9);
		}
		return randomCode;
	}

	public void signup(@Valid User user) {
		userDao.signup(user);
	}
	
	
}




