package Ekart_Shop.dto;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Component
@Data
public class Customer {

	@Id
	private String email;
	private String name;
	private long mobile;
	private String password;
	private LocalDate dob;
	private String gender;
	private String address;
	private String token;
	private boolean status;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	ShoppingCart shoppingCart;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Wishlist> wishlists;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<ShoppingOrder> orders;
}
