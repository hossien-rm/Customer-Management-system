package com.cms.mapper;

import com.cms.dto.AuditLogDTO;
import com.cms.entity.AuditLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AuditLogMapper {


    AuditLogDTO entityToDto(AuditLog log);

    List<AuditLogDTO> listEntityToDto(List<AuditLog> logs);

    AuditLog dtoToEntity(AuditLogDTO dto);

}
