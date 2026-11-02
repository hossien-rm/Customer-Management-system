package com.cms.dto;


import com.cms.constant.FactorPaymentType;
import com.cms.constant.FactorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FactorDTO {

    private Long id;
    private Double amount;
    private FactorType type;
    private FactorPaymentType paymentType;
    private String customerName;
    private Long serverId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String createdBy;
    private String updatedBy;


}
