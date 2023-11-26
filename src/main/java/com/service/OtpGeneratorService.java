package com.service;

import org.springframework.stereotype.Service;

@Service
public class OtpGeneratorService {

	public String getNumericOtp() {
		String otp = "" ;
		 
		for(int i=1;i<=6;i++) {
			otp = otp + (int)(Math.random()*10);
		}
		
		return otp;
	}
}
