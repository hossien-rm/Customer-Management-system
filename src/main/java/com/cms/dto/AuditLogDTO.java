package com.cms.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class AuditLogDTO {

    private Long id;
    private String entityType;
    private Long entityId;
    private String action;
    private String actor;
    private LocalDateTime eventAt;

}
