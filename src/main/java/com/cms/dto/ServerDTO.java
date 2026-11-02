package com.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServerDTO {

    private Long id;
    private String serviceName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private String customerName;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String createdBy;
    private String updatedBy;
}
