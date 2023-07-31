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

import com.razorpay.RazorpayException;

import Ekart_Shop.dto.Customer;
import Ekart_Shop.helper.Login;
import Ekart_Shop.service.CustomerService;
import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@GetMapping("/login")
	public String signup(ModelMap map) {
		map.put("name", "Merchant");
		return "CustomerLogin";
	}
	
	@GetMapping("/forgotpassword")
	public String password() {
		return "CustomerForgotPassword";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "Customersignup";
	}
	
	@PostMapping("/signup1")
	public String signup1(Customer customer,@RequestParam String date,ModelMap map) throws IOException {
		return customerService.signup1(customer, date, map);
	}
	@GetMapping("/verify-otp/{email}/{token}")
	public String verifyLink(@PathVariable String email,@PathVariable String token,ModelMap map) {
		return customerService.verifyLink(email,token,map);
	}
	@PostMapping("/login")
	public String login(Login login,HttpSession session,ModelMap map) {
		return customerService.login(login,session,map);
	}
	@PostMapping("/forgotpasswordsss")
	public String forgotLink(@RequestParam String email,ModelMap map) {
		return customerService.forgotLink(email,map);
	}
	@GetMapping("/reset-password/{email}/{token}")
	public String resetPassword(@PathVariable String email,@PathVariable String token,ModelMap map) {
		return customerService.resetPassword(email,token,map);
	}
	@PostMapping("/reset-password")
	public String setpassword(@RequestParam String email,@RequestParam String password,ModelMap map ) {
		return customerService.setpassword(email,password,map);
	}
	@GetMapping("/products-view")
	public String fetchAllProducts(ModelMap model,HttpSession session)
	{
		return customerService.fetchProducts(model,session);
	}
	@GetMapping("/cart-add/{id}")
	public String addToCart(ModelMap model,HttpSession session,@PathVariable int id)
	{
		return customerService.addToCart(model,session,id);
	}
	
	@GetMapping("/cart-view")
	public String viewCart(ModelMap model,HttpSession session)
	{
		return customerService.viewCart(model,session);
	}
	
	@GetMapping("/cart-remove/{id}")
    public String removeFromCart(HttpSession session,ModelMap map,@PathVariable int id) {
		return customerService.removeFromCart(session,map,id);
	}
	
	@GetMapping("/wishlist-add/{id}")
	public String addToWishlist(ModelMap model, HttpSession session, @PathVariable int id) {
		return customerService.loadWishlist(model, session, id);
	}

	@GetMapping("/wishlist-create/{id}")
	public String gotoWishlist(ModelMap model, HttpSession session, @PathVariable int id) {
		return customerService.gotoWishlist(model, session, id);
	}

	@PostMapping("/wishlist-create/{id}")
	public String createWishlist(ModelMap model, HttpSession session, @PathVariable int id, @RequestParam String name) {
		return customerService.createWishlist(model, session, id, name);
	}

	@GetMapping("/wishlist-view")
	public String viewWishlist(ModelMap model, HttpSession session) {
		return customerService.viewWishlist(model, session);
	}

	@GetMapping("/wishlist/product-view/{id}")
	public String viewWishlistProducts(@PathVariable int id, ModelMap model, HttpSession session) {
		return customerService.viewWishlistProducts(id, model, session);
	}

	@GetMapping("/wishlist-add/{wid}/{pid}")
	public String addToWishList(@PathVariable int wid, @PathVariable int pid, ModelMap model, HttpSession session) {
		return customerService.addToWishList(model, session, wid, pid);
	}

	@GetMapping("/wishlist-remove/{wid}/{pid}")
	public String removeFromWishList(@PathVariable int wid, @PathVariable int pid, ModelMap model,
			HttpSession session) {
		return customerService.removeFromWishList(model, session, wid, pid);
	}

	@GetMapping("/wishlist-delete/{wid}")
	public String removeWishList(@PathVariable int wid, ModelMap model, HttpSession session) {
		return customerService.removeWishList(model, session, wid);
	}

	@GetMapping("/placeorder")
	public String checkPayment(ModelMap model, HttpSession session) {
		return customerService.checkPayment(model, session);
	}

	@PostMapping("/placeorder")
	public String checkAddress(ModelMap model, HttpSession session, @RequestParam int pid) {
		return customerService.checkAddress(model, session, pid);
	}

	@PostMapping("/sumbmitorder")
	public String submitOrder(ModelMap model, HttpSession session, @RequestParam int pid,
			@RequestParam String address) throws RazorpayException {
		return customerService.submitOrder(model, session, pid, address);
	}

	@GetMapping("/orders-view")
	public String viewOrder(ModelMap model, HttpSession session)
	{
		return customerService.viewOrders(model,session);
	}
}





