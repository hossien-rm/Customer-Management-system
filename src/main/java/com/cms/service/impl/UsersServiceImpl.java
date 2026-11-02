package com.cms.service.impl;

import com.cms.constant.RoleType;
import com.cms.dto.UsersDTO;
import com.cms.entity.Users;
import com.cms.exception.CustomExceptionHandler;
import com.cms.mapper.UsersMapper;
import com.cms.repository.UsersRepository;
import com.cms.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuditLogServiceImpl auditLogService;

    @Override
    public Users create(UsersDTO dto) {
        if (usersRepository.existsByUsername(dto.getUsername())) {
            throw new CustomExceptionHandler("Username already exists");
        }

        Users tempUser = usersMapper.dtoToEntity(dto);
        String hashingPassword = passwordEncoder.encode(tempUser.getPassword());
        tempUser.setRole(RoleType.CUSTOMER.name());
        tempUser.setPassword(hashingPassword);

        Users user;
        try {
            user = usersRepository.save(tempUser);
        } catch (Exception ex) {
            if (usersRepository.existsByUsername(dto.getUsername())) {  // Another request may have created this username after the initial check.
                throw new CustomExceptionHandler("Username already exists");
            }
            throw ex;
        }
        auditLogService.log(Users.class.getSimpleName(), user.getId(), "CREATE", user.getCreatedBy());
        return user;
    }


    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long theId) {
        return usersRepository.findById(theId)
                .orElseThrow(() -> new CustomExceptionHandler("Your user id is not valid"));
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() ->
                new CustomExceptionHandler("Username is not found"));
    }

    @Override
    public Users changeRole(Long theId, String role) {
        Users tempUser = usersRepository.findById(theId)
                .orElseThrow(() -> new CustomExceptionHandler("Your id for change role is not valid"));

        tempUser.setRole(role);

        Users user = usersRepository.save(tempUser);
        auditLogService.log(Users.class.getSimpleName(), user.getId(), "UPDATE", user.getUpdatedBy());
        return user;
    }


    @Override
    public void deleteById(Long theId) {
        Users user = usersRepository.findById(theId)
                .orElseThrow(() -> new CustomExceptionHandler("Your user is not valid for delete operation"));
        usersRepository.delete(user);
        auditLogService.log(Users.class.getSimpleName(), user.getId(), "DELETE", user.getUpdatedBy());
    }


}
