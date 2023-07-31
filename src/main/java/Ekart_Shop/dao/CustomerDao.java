package Ekart_Shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Ekart_Shop.dto.Customer;
import Ekart_Shop.repository.CustomerRepository;

@Repository
public class CustomerDao {
	
	@Autowired
	CustomerRepository  repository;

	public  Customer findByEmail(String email) {
		return	repository.findByEmail(email);
		}
		
		public Customer findByMobile(long mobile) {
			return repository.findByMobile(mobile);
		}
		public Customer save(Customer customer) {
			return repository.save(customer);
			
		}
}
