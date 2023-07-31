package Ekart_Shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Ekart_Shop.dto.Payment;
import Ekart_Shop.helper.Login;
import Ekart_Shop.service.AdminService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/login")
	public String gotoLogin( ModelMap map) {
		map.put("name", "Admin");
		return "AdminLogin";
	}
	
	@PostMapping("/login")
	public String login( Login login,ModelMap map,HttpSession session) {
		return adminService.login(login,map,session);
	}
	@GetMapping("/product-approve")
	public String viewAllProducts(HttpSession session,ModelMap map) {
		if(session.getAttribute("admin")==null)
		{
			map.put("fail","Session Expired Login Again");
			return "AdminLogin";
		}else{
		
			return adminService.featchAllProducts(map);
		}
	}
	@GetMapping("/Product-changestatus/{id}")
	public String changestatus(@PathVariable int id,ModelMap map,HttpSession session) {
		if(session.getAttribute("admin")==null)
		{
			map.put("fail","Session Expired Login Again");
			return "AdminLogin";
		}else{
		
			return adminService.changestatus(id,map);
		}
	}
	@GetMapping("/merchant-approve")
	public String approvemerchant(HttpSession session,ModelMap map) {
		if(session.getAttribute("admin")==null)
		{
			map.put("fail","Session Expired Login Again");
			return "AdminLogin";
		}else{
		
			return adminService.approvemerchant(map);
		}
	}
	
	@GetMapping("/customer-approve")
	public String approvecustomer(HttpSession session,ModelMap map) {
		if(session.getAttribute("admin")==null)
		{
			map.put("fail","Session Expired Login Again");
			return "AdminLogin";
		}else{
			return adminService.approvecustomer(map);
		}
	}
	@GetMapping("/payment-add")
	public String loadAddPaymentPage(ModelMap map,HttpSession session) {
		if(session.getAttribute("admin")==null) {
			map.put("fail", "session Expired Login Again");
			return "AdminLogin";
			}else {
				return "AddPaymentOption";
		}
	}
	@PostMapping("/payment-add")
	public String addPaymentPage(Payment payment,ModelMap map,HttpSession session) {
		if(session.getAttribute("admin")==null) {
			map.put("fail", "session Expired Login Again");
			return "AdminLogin";
		}else {
			return adminService.addPaymentPage(payment,map);
		}
	}
}
