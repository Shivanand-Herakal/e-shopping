package Ekart_Shop.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import Ekart_Shop.dao.MerchantDao;
import Ekart_Shop.dto.Merchant;
import Ekart_Shop.dto.Product;
import Ekart_Shop.helper.SendMail;
import Ekart_Shop.repository.ProductRepository;
import jakarta.mail.Session;
import jakarta.servlet.http.HttpSession;

@Service
public class Merchantservice {

	@Autowired
	MerchantDao merchantDao;

	@Autowired
	SendMail mail;
	@Autowired
    ProductRepository productRepository;
	
	public String signup(Merchant merchant, String date, MultipartFile pic, ModelMap map) throws IOException {
		merchant.setDob(LocalDate.parse(date));
		byte[] picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		merchant.setPicture(picture);

//	Logic for the not repeating both emal&& mobile
		if (merchantDao.findByEmail(merchant.getEmail()) != null
				|| merchantDao.findByMobile(merchant.getMobile()) != null) {
			map.put("fail", "email and mobile shpuld be not to repeated");
			return "Merchantsignup";
		}
		// logic for sending the otp
		int otp = new Random().nextInt(100000, 999999);
		merchant.setOtp(otp);

		// logic for sending the mail
		if (mail.sendotp(merchant)) {
			Merchant merchant2 = merchantDao.save(merchant);
			map.put("merchant", merchant2);
			return "SignupOtp";
		} else {
			map.put("fail", "Something went wrong");
			return "Merchantsignup";
		}
	}

	public String verifyotp(String email, int otp, ModelMap map) {
		Merchant merchant = merchantDao.findByEmail(email);
		if (merchant.getOtp() == otp) {
			merchant.setStatus(true);
			merchant.setOtp(0);
			merchantDao.save(merchant);
			map.put("pass", "Account Created Succesfuully");
			return "MerchantLogin";
		} else {
			map.put("fail", "OTP is incorrect");
			map.put("merchant", merchant);
			return "SignupOtp";
		}
	}

	public String resendotp(String email, ModelMap map) {
		Merchant merchant = merchantDao.findByEmail(email);
		int otp = new Random().nextInt(100000, 999999);
		merchant.setOtp(otp);

		// logic for sending the mail
		if (mail.sendotp(merchant)) {
			Merchant merchant2 = merchantDao.save(merchant);
			map.put("merchant", merchant2);
			map.put("pass", "otp resend sucessfull");
			return "SignupOtp";
		} else {
			map.put("fail", "Something went wrong");
			return "Merchantsignup";
		}
	}

	public String forgotpassword(String email, ModelMap map) {
		Merchant merchant = merchantDao.findByEmail(email);
		if (merchant == null) {
			map.put("fail", "Email Doesn't Exits Signup First");
			return "Merchantsignup";
		} else {
			int otp = new Random().nextInt(100000, 999999);
			merchant.setOtp(otp);

			// logic for sending the mail
			if (mail.sendotp(merchant)) {
				Merchant merchant2 = merchantDao.save(merchant);
				map.put("merchant", merchant2);
				map.put("pass", "otp sent sucessfull");
				return "ForgotPassword";
			} else {
				map.put("fail", "Something went wrong");
				return "MerchantForgotPassword";
			}
		}
	}

	public String forogtotp(String email, int otp, ModelMap map) {
		Merchant merchant = merchantDao.findByEmail(email);
		if (merchant.getOtp() == otp) {
			merchant.setStatus(true);
			merchant.setOtp(0);
			merchantDao.save(merchant);
			map.put("merchant", merchant);
			map.put("pass", "OTP Verfied Succesfuully");
			return "MerchantResetPassword";
		} else {
			map.put("fail", "OTP is incorrect");
			map.put("extra", "Again");
			map.put("merchant", merchant);
			return "ForgotPassword";
		}
	}

	public String resendforgototp(String email, ModelMap map) {
		Merchant merchant = merchantDao.findByEmail(email);
		int otp = new Random().nextInt(100000, 999999);
		merchant.setOtp(otp);

		// logic for sending the mail
		if (mail.sendotp(merchant)) {
			Merchant merchant2 = merchantDao.save(merchant);
			map.put("merchant", merchant2);
			map.put("pass", "otp resend sucessfull");
			return "ForgotPassword";
		} else {
			map.put("fail", "Something went wrong");
			return "MerchantForgotPassword";
		}
	}

	public String setpassword(String email, String password, ModelMap map) {
		Merchant merchant = merchantDao.findByEmail(email);
		merchant.setPassword(password);
		merchantDao.save(merchant);
		map.put("pass", "Password Set Success");
		return "MerchantLogin";
	}

	public String login(String email, String password, ModelMap map, HttpSession session) {
		Merchant merchant = merchantDao.findByEmail(email);
		if (merchant == null) {
			map.put("fail", "Email dosen't exits");
			return "MerchantLogin";
		} else {
			if (merchant.getPassword().equals(password)) {
				if (merchant.isStatus()) {
					session.setAttribute("merchant", merchant);
					map.put("pass", "Login success");
					return "MerchantHome";
				} else {
					map.put("fail", "Mail Verification Pending,click On Forgot Password");
					return "MerchantLogin";
				}
			} else {

				map.put("fail", "Incorrect Password");
				return "MerchantLogin";
			}
		}
	}

	public String addproduct(Product product, ModelMap map, HttpSession session, MultipartFile pic) throws IOException {
		Merchant merchant=(Merchant) session.getAttribute("merchant");
		byte[] picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		
		product.setImage(picture);
		product.setName(merchant.getName()+"-"+product.getName());
		
		Product product2=merchantDao.findProductByName(product.getName());
		if(product2!=null)
		{
			product.setId(product2.getId());
			product.setStock(product.getStock()+product2.getStock());
		}
		List<Product> products=merchant.getProducts();
		if(products==null)
		{
			products=new ArrayList<>();
		}
		products.add(product);
		merchant.setProducts(products);

		session.setAttribute("merchant",merchantDao.save(merchant));
		map.put("pass", "Products Added Succesful");
		return "MerchantHome";
	}

	public String featchAllProducts(ModelMap map, HttpSession session) {
		Merchant merchant=(Merchant)session.getAttribute("merchant");
		if(merchant.getProducts()==null ||merchant.getProducts().isEmpty()) {
			map.put("fail","Products Are Not Available Now");
			return "MerchantHome";
		}else {
			map.put("products", merchant.getProducts());
			return "MerchantDisplayProduct";
		}
	}

	public String deleteProduct(ModelMap map, HttpSession session, int id) {
		Merchant merchant=(Merchant)session.getAttribute("merchant");
		Product product=merchantDao.findProductById(id);
		merchant.getProducts().remove(product);
		merchantDao.save(merchant);
		merchantDao.removeProduct(product);
		map.put("pass", "PRODUCT DELETED SUCCESSFULL");
		if(merchant.getProducts()==null ||merchant.getProducts().isEmpty()) {
			map.put("fail","Products Are Not Available Now");
			return "MerchantHome";
		}else {
			map.put("products", merchant.getProducts());
			return "MerchantDisplayProduct";
		}

	}

	public String updateProduct(ModelMap map, int id) {
		Product product=merchantDao.findProductById(id);
		map.put("product", product);
		return "MerchnatUpdateProduct";
			}

	public String productupdate(Product product, ModelMap map,HttpSession session) {
		product.setImage(merchantDao.findProductById(product.getId()).getImage());
		product.setStatus(merchantDao.findProductById(product.getId()).isStatus());
		productRepository.save(product);
		map.put("pass", "Product Updated successfull");
		Merchant merchant1=(Merchant)session.getAttribute("merchant");
		Merchant merchant = merchantDao.findByEmail(merchant1.getEmail());
		session.setAttribute("merchant", merchant);
	if(merchant.getProducts()==null ||merchant.getProducts().isEmpty()) {
		map.put("fail","Products Are Not Available Now");
		return "MerchantHome";
	}else {
		map.put("products", merchant.getProducts());
		map.put("pass","Updated Successfull");
		return "MerchantDisplayProduct";
	}
	}
}
