package Ekart_Shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ekart_Shop.dto.Merchant;

public interface MerchantRepository  extends JpaRepository<Merchant, String>{

	Merchant findByEmail(String email);

	Merchant findByMobile(long mobile);

}
