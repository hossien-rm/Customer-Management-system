package com.cms.service;

import com.cms.entity.AuditLog;

import java.util.List;


public interface AuditLogService {

    void log(String entityName, Long entityId, String action, String username);

    List<AuditLog> findLogsByActor(String actor);

}
