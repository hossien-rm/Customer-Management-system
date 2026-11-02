package com.cms.dto;

import com.cms.constant.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

    private Long id;
    private String title;
    private TicketStatus status;
    private String descriptions;
    private String reply;
    private String supportName;
    private String customerName;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String createdBy;
    private String updatedBy;
}
