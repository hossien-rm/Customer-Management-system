package com.cms.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersDTO {

    private Long id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String createdBy;
    private String updatedBy;
}
