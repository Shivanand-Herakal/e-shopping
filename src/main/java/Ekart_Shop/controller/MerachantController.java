package Ekart_Shop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import Ekart_Shop.dto.Merchant;
import Ekart_Shop.dto.Product;
import Ekart_Shop.service.Merchantservice;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/merchant")
public class MerachantController {

	@Autowired
	Merchantservice merchantservice;

	@GetMapping("/login")
	public String login() {
		return "MerchantLogin";
	}

	@GetMapping("/forgotpassword")
	public String password() {
		return "MerchantForgotPassword";
	}

	@GetMapping("/signup")
	public String tosignup() {
		return "Merchantsignup";
	}

	@PostMapping("/signup1")
	public String signup(ModelMap map, Merchant merchant, @RequestParam String date, @RequestParam MultipartFile pic)
			throws IOException {
		return merchantservice.signup(merchant, date, pic, map);
	}

	@PostMapping("/verify-otp/{email}")
	public String verifyOtp(@PathVariable String email, @RequestParam int otp, ModelMap map) {
		return merchantservice.verifyotp(email, otp, map);
	}

	@GetMapping("/resend-opt/{email}")
	public String resendotp(@PathVariable String email, ModelMap map) {
		return merchantservice.resendotp(email, map);
	}

	@PostMapping("/forgotpassword")
	public String forgotpassword(@RequestParam String email, ModelMap map) {
		return merchantservice.forgotpassword(email, map);
	}

	@PostMapping("/forgotpassword/{email}")
	public String forogtotp(@PathVariable String email, @RequestParam int otp, ModelMap map) {
		return merchantservice.forogtotp(email, otp, map);
	}

	@GetMapping("/resend-forgot-opt/{email}")
	public String resendforgototp(@PathVariable String email, ModelMap map) {
		return merchantservice.resendforgototp(email, map);
	}

	@PostMapping("/reset-password")
	public String setpassword(@RequestParam String email, @RequestParam String password, ModelMap map) {
		return merchantservice.setpassword(email, password, map);
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, ModelMap map, HttpSession session) {
		return merchantservice.login(email, password, map, session);
	}
	@GetMapping("/product-add")
	public String gotoaddproductpage(HttpSession session,ModelMap map) {
		if(session.getAttribute("merchant")==null)
		{
			map.put("fail","Session Expired Login Again");
			return "MerchantLogin";
		}else{
			map.put("merchant",session.getAttribute("merchant"));
			return "AddProduct";
		}
	}
	@PostMapping("/product-add")
	public String addproduct(HttpSession session,ModelMap map,Product product,@RequestParam MultipartFile pic) throws IOException {
		if(session.getAttribute("merchant")==null)
		{
			map.put("fail","Session Expired Login Again");
			return "MerchantLogin";
		}else{
		
			return merchantservice.addproduct(product,map,session,pic);
		}
	}
	@GetMapping("/product-view")
	public String featchAllProducts(HttpSession session,ModelMap map) {
		if(session.getAttribute("merchant")==null)
		{
			map.put("fail","Session Expired Login Again");
			return "MerchantLogin";
		}else{
		
			return merchantservice.featchAllProducts(map,session);
		}
	}
	@GetMapping("/product-delete/{id}")
	public String deleteProduct(HttpSession session,ModelMap map,@PathVariable int id) {
		if(session.getAttribute("merchant")==null)
		{
			map.put("fail","Session Expired Login Again");
			return "MerchantLogin";
		}else{
		
			return merchantservice.deleteProduct(map,session,id);
		}
	}
	@GetMapping("/product-update/{id}")
	public String updateProduct(HttpSession session,@PathVariable int id,ModelMap map) {
		if(session.getAttribute("merchant")==null)
		{
			map.put("fail","Session Expired Login Again");
			return "MerchantLogin";
		}else{
		
			return merchantservice.updateProduct(map,id);
		}
	}
	@PostMapping("/product-update")
	public String productupdate(Product product,ModelMap map,HttpSession session)
	{
		if(session.getAttribute("merchant")==null)
		{
			map.put("fail", "Session Expied Login Again");
			return "MerchantLogin";
		}
		else {
		return merchantservice.productupdate(product,map,session);
	}
}
}
