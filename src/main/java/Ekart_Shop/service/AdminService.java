package Ekart_Shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import Ekart_Shop.dto.Customer;
import Ekart_Shop.dto.Merchant;
import Ekart_Shop.dto.Payment;
import Ekart_Shop.dto.Product;
import Ekart_Shop.helper.Login;
import Ekart_Shop.repository.CustomerRepository;
import Ekart_Shop.repository.MerchantRepository;
import Ekart_Shop.repository.PaymentRepository;
import Ekart_Shop.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	CustomerRepository  customerRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public String login(Login login, ModelMap map, HttpSession session) {
		if(login.getEmail().equals("admin")) 
			{
				if(login.getPassword().equals("admin"))
				{
					session.setAttribute("admin", "admin");
					map.put("pass", "Login success");
					return "AdminHome";
				}else {
					map.put("fail", "Failed to password");
				}
			}else {
				map.put("fail", "Incorrect email");
			}
			return "Login";
		}

	public String featchAllProducts(ModelMap map) {
		List<Product> list=productRepository.findAll();
		if(list.isEmpty())
		{
			map.put("fail","No Products Found");
			return "AdminHome";		
		}else {
			map.put("products", list);
			return "AdminDisplayProduct";
		}
	}

	public String changestatus(int id, ModelMap map) {
		Product product=productRepository.findById(id).orElse(null);
		if(product.isStatus())
		{
			product.setStatus(false);
		}else {
			product.setStatus(true);
		}
		productRepository.save(product);
		map.put("pass", "Change Status successfull");
		List<Product>list=productRepository.findAll();
		if(list.isEmpty())
		{
			map.put("fail","No products changed");
			return "AdmiHome";
		}
		else {
			map.put("products",list);
			return "AdminDisplayProduct";
		}
	}

	public String approvemerchant(ModelMap map) {
		List<Merchant> list=merchantRepository.findAll();
		if(list.isEmpty())
		{
			map.put("fail","No Products Found");
			return "AdminHome";		
		}else {
			map.put("merchants", list);
			return "AdminMerchnatDisplay";
		}
	}

	public String approvecustomer(ModelMap map) {
		List<Customer> list=customerRepository.findAll();
		if(list.isEmpty())
		{
			map.put("fail","No Products Found");
			return "AdminHome";		
		}else {
			map.put("customers", list);
			return "AdminCustomerDisplay";
		}
	}
	
	public String addPaymentPage(Payment payment,ModelMap map) {
		Payment payment2=paymentRepository.findByName(payment.getName());
		if(payment2==null) {
			paymentRepository.save(payment);
			map.put("Pass", "Payment method added succesfully");
			return "AdminHome";
		}else {
			map.put("fail","payment Method Already Exits");
			return "AddPaymentOption";
		}
	}
}
 

