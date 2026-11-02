package com.cms.service.impl;

import com.cms.entity.AuditLog;
import com.cms.repository.AuditLogRepository;
import com.cms.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public void log(String entityName, Long entityId, String action, String username) {
        AuditLog log = new AuditLog();
        log.setEntityType(entityName);
        log.setEntityId(entityId);
        log.setAction(action);
        log.setActor(username == null ? "ANONYMOUS" : username);
        log.setEventAt(LocalDateTime.now());
        auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> findLogsByActor(String actor) {
        return auditLogRepository.findLogsByUsername(actor);
    }


}
