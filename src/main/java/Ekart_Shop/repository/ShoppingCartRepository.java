package Ekart_Shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ekart_Shop.dto.ShoppingCart;


public interface ShoppingCartRepository  extends JpaRepository<ShoppingCart, Integer>{

}
