package com.jsp.QuickBite.payment_module.dao;

import com.jsp.QuickBite.payment_module.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
