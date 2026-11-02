package com.cms.mapper;

import com.cms.dto.CustomerDTO;
import com.cms.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {


    CustomerDTO entityToDto(Customer customer);

    List<CustomerDTO> listEntityToDto(List<Customer> customers);

    Customer dtoToEntity(CustomerDTO dto);


}
