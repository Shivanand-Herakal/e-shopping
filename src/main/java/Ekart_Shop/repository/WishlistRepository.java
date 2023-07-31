package Ekart_Shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ekart_Shop.dto.Wishlist;

public interface WishlistRepository  extends JpaRepository<Wishlist, Integer>{

	Wishlist findByName(String name);

}
