package Ekart_Shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ekart_Shop.dto.Customer;
import Ekart_Shop.dto.Merchant;

public interface CustomerRepository  extends JpaRepository<Customer, String>{

	Customer findByEmail(String email);

	Customer findByMobile(long mobile);



}
