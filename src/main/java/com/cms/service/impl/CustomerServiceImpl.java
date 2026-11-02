package com.cms.service.impl;

import com.cms.dto.CustomerDTO;
import com.cms.entity.Customer;
import com.cms.exception.CustomExceptionHandler;
import com.cms.mapper.CustomerMapper;
import com.cms.repository.CustomerRepository;
import com.cms.service.AuditLogService;
import com.cms.service.CustomerService;
import com.cms.service.UsersService;
import com.cms.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AuditLogService auditLogService;
    private final UsersService usersService;


    @Transactional
    @Override
    public Customer create(CustomerDTO dto) {
        Customer tempCustomer = customerMapper.dtoToEntity(dto);
        tempCustomer.setUser(usersService.findByUsername(UserUtils.getCurrentUsername()));
        Customer customer = customerRepository.save(tempCustomer);
        auditLogService.log(Customer.class.getSimpleName(), customer.getId(), "CREATE", customer.getCreatedBy());
        return customer;

    }

    @Override
    public Customer findCustomerByUser(String username) {
        return customerRepository.findCustomerByUsername(username)
                .orElseThrow(() -> new CustomExceptionHandler("Your username is not found"));
    }

    @Transactional
    @Override
    public Customer update(CustomerDTO dto) {
        Customer customer = customerRepository.findById(dto.getId())
                .orElseThrow(() -> new CustomExceptionHandler("Your customer id is not valid for update"));
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setNationalCode(dto.getNationalCode());

        Customer saved = customerRepository.save(customer);
        auditLogService.log(Customer.class.getSimpleName(), saved.getId(), "UPDATE", saved.getUpdatedBy());
        return saved;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long theId) {
        return customerRepository.findById(theId)
                .orElseThrow(() -> new CustomExceptionHandler("Your customer id is not valid"));
    }


    @Transactional
    @Override
    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomExceptionHandler("This customer id not valid for delete"));
        customerRepository.delete(customer);
        auditLogService.log(Customer.class.getSimpleName(), customer.getId(), "DELETE", customer.getUpdatedBy());
    }


}
