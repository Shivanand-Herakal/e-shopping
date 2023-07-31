package Ekart_Shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ekart_Shop.dto.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {


	Payment findByName(String name);
}
