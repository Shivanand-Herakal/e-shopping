package Ekart_Shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Ekart_Shop.dto.Merchant;
import Ekart_Shop.dto.Product;
import Ekart_Shop.repository.MerchantRepository;
import Ekart_Shop.repository.ProductRepository;

@Repository
public class MerchantDao {

	@Autowired
	MerchantRepository repository;
	
	@Autowired
	ProductRepository productRepository;
	
	public  Merchant findByEmail(String email) {
	return	repository.findByEmail(email);
	}
	
	public Merchant findByMobile(long mobile) {
		return repository.findByMobile(mobile);
	}

	public Merchant save(Merchant merchant) {
		return repository.save(merchant);
		
	}
	public Product findProductByName(String name) {
		return productRepository.findByName(name);
	}

	public Product findProductById(int id) {
		return productRepository.findById(id).orElse(null);
	}

	public void removeProduct(Product product) {
	  productRepository.delete(product);
		
	}




}
