package application.service;

import application.beans.User;

public class EmailService implements SendEmail{


	private String email;
	private String vCode;
	private String container;

	@Override
	public void sendEmail(String email,String container) {
		
		System.out.println("Send an email to "+email + ". "+"Context: "+ container);
		
	}



	
}
