package com.cms.contorller;

import com.cms.config.security.JWTTokenGeneratorFilter;
import com.cms.constant.ApplicationConstant;
import com.cms.dto.RequestLoginDTO;
import com.cms.dto.ResponseLoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Sign in and obtain a JWT for protected APIs.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class loginController {

    private final AuthenticationManager authenticationManager;
    private final Environment env;


    @Operation(summary = "Login", description = "Authenticates a username and password. Returns jwtToken in the response body and Authorizations1 header.")
    @SecurityRequirements
    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody RequestLoginDTO requestLoginDTO){
        Authentication requestAuthentication=
                new UsernamePasswordAuthenticationToken(requestLoginDTO.getUsername(),requestLoginDTO.getPassword());

        Authentication responseAuthentication=authenticationManager.authenticate(requestAuthentication);

        if(null != responseAuthentication && responseAuthentication.isAuthenticated()){

            SecurityContextHolder.getContext().setAuthentication(responseAuthentication);

            String jwt= JWTTokenGeneratorFilter.generateToken(responseAuthentication,env);

            return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstant.JWT_HEADER,jwt)
                    .body(new ResponseLoginDTO(HttpStatus.OK.getReasonPhrase(),jwt));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}

