package Ekart_Shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@GetMapping("/")
	public String gotohomepage() {
		return "Home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,ModelMap map) {
		session.invalidate();
		map.put("fail", "Logout Success");
		return "Home";
	}
}
