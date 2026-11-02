package com.cms.service;


import com.cms.dto.CustomerDTO;
import com.cms.entity.Customer;

import java.util.List;


public interface CustomerService {


    Customer create(CustomerDTO dto);

    Customer findCustomerByUser(String username);

    List<Customer> findAll();

    Customer findById(Long theId);

    Customer update(CustomerDTO dto);

    void deleteCustomerById(Long Id);


}
