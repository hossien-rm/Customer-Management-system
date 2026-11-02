package com.cms.config.security;

import com.cms.constant.RoleType;
import com.cms.entity.Users;
import com.cms.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users tempUser = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User details not found for the user " + username));

        String role = tempUser.getRole();

        if (role == null || role.trim().isEmpty()) {
            role = RoleType.CUSTOMER.name();
        }

        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        return new User(tempUser.getUsername(), tempUser.getPassword(), authorities);
    }
}
