package com.cms.service;

import com.cms.dto.UsersDTO;
import com.cms.entity.Users;

import java.util.List;

public interface UsersService {

    Users create(UsersDTO dto);

    List<Users> findAll();

    Users findById(Long theId);

    Users findByUsername(String username);

    Users changeRole(Long theId, String role);

    void deleteById(Long theId);
}
