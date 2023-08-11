package com.customer.customermanagement.repository;

import com.customer.customermanagement.controller.Customer;
import com.customer.customermanagement.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import com.customer.customermanagement.model.customer;

 @Repository

public interface CustomerRepository extends JpaRepository<customer, Long> {

    Customer save(Customer customer);

}
