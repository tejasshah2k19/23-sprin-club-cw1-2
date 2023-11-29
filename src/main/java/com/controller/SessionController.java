package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.UserBean;
import com.service.EmailService;
import com.service.OtpGeneratorService;

@Controller
public class SessionController {

	@Autowired
	OtpGeneratorService otpService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String login() {
		return "Login";
	}
	
	@GetMapping("/forgotpassword")
	public String forgotPassword()
	{
		return "ForgotPassword";
	}
	
	@PostMapping("/sendotp")
	public String sendotp(@RequestParam("email") String email) {
		//check email is exists in db or not 
		
		//generate otp 
		String otp = otpService.getNumericOtp();
		System.out.println("otp => "+otp);
		
		//send otp via email 
		emailService.sendEmailForForgetPasswordOtp(email, otp);	
		
		//userDao.setOtp(email,otp) -> update users 
		return "ChangePassword";
	}
	
	@PostMapping("/saveuser")
	public String saveUser(UserBean user) {
		String pwd = user.getPassword();//plain text 
		
		String ePWd = passwordEncoder.encode(pwd);
		user.setPassword(ePWd); 
		
		//dbSave; 
		
		return "Login";
	}
	
	@PostMapping("/authenticate")
	public String authenticate(UserBean user) {
		
		//db-> user all info -> using email
			UserBean db = null;// dao.method(email)
		//using email if we not found any data then - invalid credentials 
		
		//if data found 
		boolean ans = passwordEncoder.matches(user.getPassword(), db.getPassword());//pwd,ePwd
		
		
		return "Login";
	}
	
}
