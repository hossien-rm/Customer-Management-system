package com.cms.contorller;

import com.cms.dto.CustomerDTO;
import com.cms.dto.FactorDTO;
import com.cms.dto.ServerDTO;
import com.cms.mapper.CustomerMapper;
import com.cms.service.CustomerService;
import com.cms.service.FactorService;
import com.cms.service.ServerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Customers", description = "Manage customers and view their servers and billing factors.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final ServerService serverService;
    private final FactorService factorService;


    @Operation(summary = "Create a customer", description = "Creates a customer from the supplied details. Requires authentication.")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PostMapping("/customer")
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO dto) {
        return customerMapper.entityToDto(customerService.create(dto));

    }

    @Operation(summary = "List customers", description = "Returns all customers. Requires ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customer")
    public List<CustomerDTO> findAllCustomer() {
        return customerMapper.listEntityToDto(customerService.findAll());
    }


    @Operation(summary = "Get a customer", description = "Returns a customer by ID. Requires ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customer/{theId}")
    public CustomerDTO findByIdCustomer(@PathVariable Long theId) {
        return customerMapper.entityToDto(customerService.findById(theId));
    }


    @Operation(summary = "List customer servers", description = "Returns servers for a customer ID. Requires CUSTOMER or ADMIN.")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/customer/server")
    public List<ServerDTO> findAllServerByCustomerId() {
        return serverService.getServerByUsername();
    }

    @Operation(summary = "List customer factors", description = "Returns billing factors for a customer ID. Requires CUSTOMER or ADMIN.")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/customer/factor")
    public List<FactorDTO> findAllFactorByCustomerId() {
        return factorService.getFactorByUsername();
    }

    @Operation(summary = "Update a customer", description = "Updates the customer identified by the ID in the request body. Requires CUSTOMER.")
    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/customer")
    public CustomerDTO updateCustomer(@Valid @RequestBody CustomerDTO dto) {
        return customerMapper.entityToDto(customerService.update(dto));
    }

    @Operation(summary = "Delete a customer", description = "Deletes a customer by ID. Requires CUSTOMER.")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @DeleteMapping("/customer/{theId}")
    public void deleteCustomer(@PathVariable Long theId) {
        customerService.deleteCustomerById(theId);
    }
}
