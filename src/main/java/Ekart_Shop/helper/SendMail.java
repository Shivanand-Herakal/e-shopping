package Ekart_Shop.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import Ekart_Shop.dto.Customer;
import Ekart_Shop.dto.Merchant;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMail {

	@Autowired
	JavaMailSender mailSender;

	public boolean sendotp(Merchant merchant) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom("E-Market");
			helper.setTo(merchant.getEmail());
			helper.setSubject("Verify Your Email For Account Verifocation");

			String gender = null;
			if (merchant.getGender().equals("male"))
				gender = "Mr. ";
			else
				gender = "Ms. ";

			String content = "<h1>Hello " + gender + " " + merchant.getName() + ",<h1>"
					+ "<h1>Thank you for creating an account </h1>" + "<h1>OTP: " + merchant.getOtp();

			helper.setText(content, true);
			mailSender.send(message);
			return true;

		} catch (MessagingException e) {
			return false;

		}

	}

	public boolean sendLink(Customer customer) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom("E-Market");
			helper.setTo(customer.getEmail());
			helper.setSubject("Verify Your Email For Account Verifocation");

			String gender = null;
			if (customer.getGender().equals("male"))
				gender = "Mr. ";
			else
				gender = "Ms. ";
			
			

			String content = "<h1>Hello " + gender + " " + customer.getName() + ",<h1>"
					+ "<h1>Thank you for creating an account ,Click below to verify the your account</h1>"
					+ "<h1>Link:</h1> <a href='http://localhost:8080/customer/verify-otp/" + customer.getEmail() + "/"
					+ customer.getToken() + "'>Click Here</a>";

			helper.setText(content, true);
			mailSender.send(message);
			return true;

		} catch (MessagingException e) {
			return false;

		}
	}

	public boolean sendResetLink(Customer customer) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom("E-Market");
			helper.setTo(customer.getEmail());
			helper.setSubject("Reset Your Password");
			String gender = null;
			if (customer.getGender().equals("male"))
				gender = "Mr. ";
			else
				gender = "Ms. ";

			String content = "<h1>Hello " + gender + " " + customer.getName() + ",<h1>"
					+ "<h1>Thank you for creating an account ,Click below to verify the your account</h1>"
					+ "<h1>Link:</h1> <a href='http://localhost:8080/customer/reset-password/" + customer.getEmail() + "/"
					+ customer.getToken() + "'>Click Here</a>";

			helper.setText(content, true);
			mailSender.send(message);
			return true;

		} catch (MessagingException e) {
			return false;

		}
	}
}
