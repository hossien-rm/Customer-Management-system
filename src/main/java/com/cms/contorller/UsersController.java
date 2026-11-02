package com.cms.contorller;

import com.cms.dto.UsersDTO;
import com.cms.mapper.UsersMapper;
import com.cms.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Register users, view users and manage user roles.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @Operation(summary = "Create a user", description = "Registers a user from the supplied details. Public endpoint.")
    @SecurityRequirements
    @PostMapping("/users")
    public UsersDTO createUser(@RequestBody UsersDTO dto) {
        return usersMapper.entityToDto(usersService.create(dto));
    }

    @Operation(summary = "List users", description = "Returns all users. Public endpoint.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<UsersDTO> findAllUsers() {
        return usersMapper.ListEntityToDto(usersService.findAll());
    }

    @Operation(summary = "Change a user role", description = "Updates the role of a user by ID. Requires ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{theId}/role")
    public UsersDTO changeRoleOfUser(@PathVariable Long theId, @RequestBody String role) {
        return usersMapper.entityToDto(usersService.changeRole(theId, role));
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by ID through the existing /usres path. Requires authentication.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{theId}")
    public void deleteUserById(@PathVariable Long theId) {
        usersService.deleteById(theId);
    }

}
