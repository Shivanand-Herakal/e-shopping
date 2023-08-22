package Ekart_Shop.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.razorpay.Account;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import Ekart_Shop.dao.CustomerDao;
import Ekart_Shop.dto.Customer;
import Ekart_Shop.dto.Item;
import Ekart_Shop.dto.Payment;
import Ekart_Shop.dto.Product;
import Ekart_Shop.dto.ShoppingCart;
import Ekart_Shop.dto.ShoppingOrder;
import Ekart_Shop.dto.Wishlist;
import Ekart_Shop.helper.Login;
import Ekart_Shop.helper.SendMail;
import Ekart_Shop.repository.PaymentRepository;
import Ekart_Shop.repository.ProductRepository;
import Ekart_Shop.repository.ShoppingCartRepository;
import Ekart_Shop.repository.WishlistRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	SendMail mail;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	Item item;

	@Autowired
	ShoppingCart shoppingCart;

	@Autowired
	WishlistRepository wishlistRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ShoppingCartRepository cartRepository;

	public String signup1(Customer customer, String date, ModelMap map) throws IOException {
		customer.setDob(LocalDate.parse(date));
		if (customerDao.findByEmail(customer.getEmail()) != null
				|| customerDao.findByMobile(customer.getMobile()) != null) {
			map.put("fail", "email and mobile shpuld be not to repeated");
			return "Customersignup";
		}
		String token = "EKART" + new Random().nextInt(10000, 999999);
		customer.setToken(token);

		// logic for sending the mail
		if (mail.sendLink(customer)) {
			customerDao.save(customer);
			map.put("pass", "Verification Link send to Email Succesfull");
			return "CustomerLogin";
		} else {
			map.put("fail", "Something went wrong");
			return "Customersignup";
		}
	}

	public String verifyLink(String email, String token, ModelMap map) {
		Customer customer = customerDao.findByEmail(email);
		if (customer.getToken().equals(token)) {
			customer.setStatus(true);
			customer.setToken(null);
			customerDao.save(customer);
			map.put("pass", "Account Created Succesfuully");
			return "CustomerLogin";
		} else {
			map.put("fail", "Incorrect Link");
			return "CustomerLogin";
		}
	}

	public String login(Login login, HttpSession session, ModelMap map) {
		Customer customer = customerDao.findByEmail(login.getEmail());
		if (customer == null) {
			map.put("fail", "Email dosen't exits");
			return "CustomerLogin";
		} else {
			if (customer.getPassword().equals(login.getPassword())) {
				if (customer.isStatus()) {
					session.setAttribute("customer", customer);
					map.put("pass", "Login success");
					return "CustomerHome";
				} else {
					map.put("fail", "Mail Verification Pending,click On Forgot Password");
					return "CustomerLogin";
				}
			} else {

				map.put("fail", "Incorrect Password");
				return "CustomerLogin";
			}
		}
	}

	public String forgotLink(String email, ModelMap map) {
		Customer customer = customerDao.findByEmail(email);
		if (customer == null) {
			map.put("fail", "Email dosen't exits");
			return "CustomerLogin";
		} else {
			String token = "EKART" + new Random().nextInt(10000, 999999);
			customer.setToken(token);
			customerDao.save(customer);
			// logic for sending the mail
			if (mail.sendResetLink(customer)) {
				Customer customer2 = customerDao.save(customer);
				map.put("customer", customer2);
				map.put("pass", "Verification Link send to Email Succesfull");
				return "CustomerLogin";
			} else {
				map.put("fail", "Something went wrong");
				return "Customersignup";
			}
		}
	}

	public String resetPassword(String email, String token, ModelMap map) {
		Customer customer = customerDao.findByEmail(email);
		if (customer.getToken().equals(token)) {
			customer.setStatus(true);
			customer.setToken(null);
			map.put("customer", customerDao.save(customer));
			return "CustomerResetPassword";
		} else {
			map.put("fail", "Something went wrong");
			return "CustomerLogin";
		}
	}

	public String setpassword(String email, String password, ModelMap map) {
		Customer customer = customerDao.findByEmail(email);
		customer.setPassword(password);
		customerDao.save(customer);
		map.put("pass", "Password Set Success");
		return "CustomerLogin";
	}

	public String fetchProducts(ModelMap model, HttpSession session) {
		List<Product> list = productRepository.findByStatus(true);
		if (session.getAttribute("customer") == null) {
			model.put("fail", "Session Expired Login Again");
			return "CustomerLogin";
		} else {
			if (list.isEmpty()) {
				model.put("fail", "No Products Present");
				return "CustomerHome";
			} else {
				model.put("products", list);
				return "CustomerDisplayProduct";
			}
		}
	}

	public String addToCart(ModelMap map, HttpSession session, int id) {
		Customer customer = (Customer) session.getAttribute("customer");

		if (customer == null) {
			map.put("fail", "First Login to Add Product");
			return "CustomerLogin";
		} else {
			Product product = productRepository.findById(id).orElse(null);
			if (product.getStock() >= 1) {

				ShoppingCart cart = customer.getShoppingCart();
				if (cart == null) {
					cart = this.shoppingCart;
				}
				List<Item> items = cart.getItems();
				if (items == null) {
					items = new ArrayList<>();
				}

				if (items.isEmpty()) {
					item.setDescription(product.getDescription());
					item.setImage(product.getImage());
					item.setName(product.getName());
					item.setPrice(product.getPrice());
					item.setQuantity(1);
					items.add(item);
				} else {
					boolean flag = false;
					for (Item item : items) {
						if (item.getName().equals(product.getName())) {
							item.setQuantity(item.getQuantity() + 1);
							item.setPrice(item.getPrice() + product.getPrice());
							item.setDescription(product.getDescription());
							item.setImage(product.getImage());
							flag = false;
							break;
						} else {
							flag = true;
						}
					}
					if (flag) {
						item.setDescription(product.getDescription());
						item.setImage(product.getImage());
						item.setName(product.getName());
						item.setPrice(product.getPrice());
						item.setQuantity(1);
						items.add(item);
					}
				}
				cart.setItems(items);
				customer.setShoppingCart(cart);

				product.setStock(product.getStock() - 1);
				productRepository.save(product);

				session.removeAttribute("customer");
				session.setAttribute("customer", customerDao.save(customer));

				map.put("pass", "Product Added Successful");
				return "CustomerHome";
			} else {
				map.put("fail", "Out Of Stock");
				return "CustomerHome";
			}
		}
	}

	public String viewCart(ModelMap model, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Cart");
			return "CustomerLogin";
		} else {

			if (customer.getShoppingCart() == null || customer.getShoppingCart().getItems() == null
					|| customer.getShoppingCart().getItems().isEmpty()) {
				model.put("fail", "No Items in cart");
				return "CustomerHome";
			} else {
				List<Item> items = customer.getShoppingCart().getItems();
				model.put("items", items);
				return "CustomerDisplayCart";
			}
		}
	}

	public String removeFromCart(HttpSession session, ModelMap map, int id) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			map.put("fail", "Invalid Session");
			return "CustomerLogin";
		} else {
			List<Item> items = customer.getShoppingCart().getItems();
			Item item = null;
			boolean flag = false;
			for (Item item1 : items) {
				if (item1.getId() == id) {
					item = item1;
					if (item1.getQuantity() > 1) {
						item1.setPrice(item1.getPrice() - (item1.getPrice() / item1.getQuantity()));
						item1.setQuantity(item1.getQuantity() - 1);
						break;
					} else {
						flag = true;
						break;
					}
				}

			}
			if (flag) {
				items.remove(item);
			}

			Product product = productRepository.findByName(item.getName());
			product.setStock(product.getStock() + 1);
			productRepository.save(product);

			session.removeAttribute("customer");
			session.setAttribute("customer", customerDao.save(customer));

			map.put("pass", "Product Removed from Cart Success");
			return "CustomerHome";

		}
	}

	public String loadWishlist(ModelMap model, HttpSession session, int id) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to Add Product to Wishlist");
			return "CustomerLogin";
		} else {
			model.put("id", id);
			model.put("wishlists", customer.getWishlists());
			return "SelectWishlist";
		}
	}

	public String gotoWishlist(ModelMap model, HttpSession session, int id) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to Create Wishlist");
			return "CustomerLogin";
		} else {
			model.put("id", id);
			return "CreateWishlist";
		}
	}

	public String createWishlist(ModelMap model, HttpSession session, int id, String name) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to Create Wishlist");
			return "CustomerLogin";
		} else {

			if (wishlistRepository.findByName(name) == null) {
				Wishlist wishlist = new Wishlist();
				wishlist.setName(name);

				Product product = productRepository.findById(id).orElse(null);
				List<Wishlist> list = customer.getWishlists();
				if (list == null) {
					list = new ArrayList<>();
				}

				if (product != null) {
					List<Product> products = new ArrayList<>();
					products.add(product);
					wishlist.setProducts(products);

					list.add(wishlist);

					customer.setWishlists(list);

					session.removeAttribute("customer");
					session.setAttribute("customer", customerDao.save(customer));

					model.put("pass", "WishList Creation Success and Product added to Wishlist");
				} else {

					list.add(wishlist);

					customer.setWishlists(list);
					session.removeAttribute("customer");
					session.setAttribute("customer", customerDao.save(customer));

					model.put("pass", "WishList Creation Success");
				}
				return "CustomerHome";
			} else {
				model.put("fail", "WishList Already Exists");
				return "CustomerHome";
			}
		}

	}
//	rzp_test_WOkek02qzU8zkJShiva
	public String viewWishlist(ModelMap model, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Wishlist");
			return "CustomerLogin";
		} else {
			List<Wishlist> list = customer.getWishlists();
			if (list == null || list.isEmpty()) {
				model.put("fail", "No Wishlist Found");
				return "WishlistHome";
			} else {
				model.put("list", list);
				return "ViewWishlist";
			}
		}
	}

	public String viewWishlistProducts(int id, ModelMap model, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Wishlist");
			return "CustomerLogin";
		} else {
			Wishlist wishlist = wishlistRepository.findById(id).orElse(null);
			if (wishlist.getProducts() == null || wishlist.getProducts().isEmpty()) {
				model.put("fail", "No items present");
				return "WishlistHome";
			} else {
				model.put("id", wishlist.getId());
				model.put("list", wishlist.getProducts());
				return "ViewWishlistProducts";
			}
		}
	}

	public String addToWishList(ModelMap model, HttpSession session, int wid, int pid) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Wishlist");
			return "CustomerLogin";
		} else {
			Wishlist wishlist = wishlistRepository.findById(wid).orElse(null);
			Product product = productRepository.findById(pid).orElse(null);

			List<Product> list = wishlist.getProducts();
			if (list == null) {
				list = new ArrayList<>();
			}
			boolean flag = true;
			for (Product product2 : list) {
				if (product2 == product) {
					flag = false;
					break;
				}
			}
			if (flag) {
				list.add(product);

				wishlist.setProducts(list);
				wishlistRepository.save(wishlist);

				model.put("pass", "Item Added to Wish list");
			} else {
				model.put("pass", "Item Already Exists in Wishlist");
			}
			return "WishlistHome";
		}
	}

	public String removeFromWishList(ModelMap model, HttpSession session, int wid, int pid) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Wishlist");
			return "CustomerLogin";
		} else {
			Wishlist wishlist = wishlistRepository.findById(wid).orElse(null);
			Product product = productRepository.findById(pid).orElse(null);
			wishlist.getProducts().remove(product);
			wishlistRepository.save(wishlist);

			model.put("pass", "Item Removed from Wish list");
			return "WishlistHome";
		}
	}

	public String removeWishList(ModelMap model, HttpSession session, int wid) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Wishlist");
			return "CustomerLogin";
		} else {
			Wishlist wishlist = wishlistRepository.findById(wid).orElse(null);
			Wishlist wishlist2 = null;
			for (Wishlist wishlist3 : customer.getWishlists()) {
				if (wishlist3.getName().equals(wishlist.getName())) {
					wishlist2 = wishlist3;
				}
			}

			customer.getWishlists().remove(wishlist2);
			session.setAttribute("customer", customerDao.save(customer));
			wishlistRepository.delete(wishlist);

			model.put("pass", "Wishlist deleted Success");
			return "WishlistHome";

		}
	}

	public String checkPayment(ModelMap model, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Wishlist");
			return "CustomerLogin";
		} else {
			List<Payment> payments = paymentRepository.findAll();
			if (payments.isEmpty()) {
				model.put("fail", "Sorry you can not place order, There is an internal errortry after some time");
				return "CustomerHome";
			} else {
				model.put("list", payments);
				return "SelectPaymentOption";
			}
		}
	}

	public String checkAddress(ModelMap model, HttpSession session, int pid) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Wishlist");
			return "CustomerLogin";
		} else {
			model.put("address", customer.getAddress());
			model.put("pid", pid);
			return "OrderAddress";
		}
	}

	public String submitOrder(ModelMap model, HttpSession session, int pid, String address) throws RazorpayException {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Wishlist");
			return "CustomerLogin";
		} else {
			Payment payment = paymentRepository.findById(pid).orElse(null);
			ShoppingOrder order = new ShoppingOrder();
			order.setAddress(address);
			order.setPaymentMode(payment.getName());
			order.setDeliveryDate(LocalDate.now().plusDays(3));
			ShoppingCart cart = customer.getShoppingCart();
			if (cart == null) {
				model.put("fail", "First add items");
				return "CustomerHome";
			}
			if (cart.getItems() == null || cart.getItems().isEmpty()) {
				model.put("fail", "First add items");
				return "CustomerHome";
			}
			double total = 0;
			for (Item item : cart.getItems()) {
				total = total + item.getPrice();
			}
			order.setTotalPrice(total);
			order.setItems(cart.getItems());

			if (payment.getName().equalsIgnoreCase("RazorPay")) {
				JSONObject object = new JSONObject();
				object.put("currency", "INR");
				object.put("amount", total * 100);

				RazorpayClient client = new RazorpayClient("rzp_test_WOkek02qzU8zkJ", "wHpnWVu3jNVB59aehItjTchA");
				Order order1=client.orders.create(object);
				
				order.setCurrency("INR");
				order.setOrderId(order1.get("id"));
				model.put("order", order);
				model.put("key", "rzp_test_WOkek02qzU8zkJ");
				return "ProceedToPay";
			} else {
				List<ShoppingOrder> list = customer.getOrders();
				if (list == null) {
					list = new ArrayList<>();
				}
				list.add(order);
				customer.setOrders(list);
				customer.setAddress(address);
				cart.setItems(null);
				customer.setShoppingCart(null);
				Customer customer1 = customerDao.save(customer);
				cartRepository.delete(cart);
				session.removeAttribute("customer");
				session.setAttribute("customer", customer1);
				model.put("order", order);
				model.put("customer", customer1);
				model.put("pass", "Order Placed Success");
				return "PrintRecipt";
			}
		}
	}

	public String viewOrders(ModelMap model, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			model.put("fail", "First Login to view Wishlist");
			return "CustomerLogin";
		} else {
			List<ShoppingOrder> list = customer.getOrders();
			if (list == null || list.isEmpty()) {
				model.put("fail", "No Orders Yet");
				return "CustomerHome";
			} else {
				model.put("orders", customer.getOrders());
				return "ViewOrders";
			}
		}
	}

}
