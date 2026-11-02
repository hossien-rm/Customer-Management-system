package com.cms.contorller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import com.cms.dto.AuditLogDTO;
import com.cms.mapper.AuditLogMapper;
import com.cms.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Audit logs", description = "View recorded audit activity.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuditLogController {


    private final AuditLogService auditLogService;
    private final AuditLogMapper auditLogMapper;

    @Operation(summary = "List audit logs", description = "Returns all logs by username. Requires ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/log/{actor}")
    public List<AuditLogDTO> findLogsByActor(@PathVariable String actor) {
        return auditLogMapper.listEntityToDto(auditLogService.findLogsByActor(actor));
    }

}
