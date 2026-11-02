package com.cms.service;


import com.cms.dto.ServerDTO;
import com.cms.entity.Server;

import java.time.LocalDate;
import java.util.List;


public interface ServerService {

    Server create(ServerDTO dto);

    List<Server> findAll();

    List<ServerDTO> getServerByUsername();

    Server renew(Long serverId, LocalDate newDate);

    Server findById(Long theId);

    void deleteById(Long theId);
}
