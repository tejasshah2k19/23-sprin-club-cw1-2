package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.EmailService;
import com.service.OtpGeneratorService;

@Controller
public class SessionController {

	@Autowired
	OtpGeneratorService otpService;
	
	@Autowired
	EmailService emailService;
	
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
	
	
	
	
}
