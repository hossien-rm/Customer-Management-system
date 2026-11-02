package com.cms.service.impl;


import com.cms.constant.FactorPaymentType;
import com.cms.constant.FactorType;
import com.cms.dto.ServerDTO;
import com.cms.entity.Factor;
import com.cms.entity.Server;
import com.cms.exception.CustomExceptionHandler;
import com.cms.mapper.FactorMapper;
import com.cms.mapper.ServerMapper;
import com.cms.repository.ServerRepository;
import com.cms.service.CustomerService;
import com.cms.service.FactorService;
import com.cms.service.ServerService;
import com.cms.util.CustomerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final ServerMapper serverMapper;
    private final CustomerService customerService;
    private final FactorService factorService;
    private final FactorMapper factorMapper;
    private final AuditLogServiceImpl auditLogService;


    @Override
    public Server create(ServerDTO dto) {

        Server tempServer = serverMapper.dtoToEntity(dto);

        tempServer.setCustomer(customerService.findCustomerByUser(CustomerUtils.getCurrentCustomer()));

        Server saveServer = serverRepository.save(tempServer);
        Factor factor = new Factor();
        factor.setAmount(tempServer.getPrice());
        factor.setServer(tempServer);
        factor.setCustomer(customerService.findCustomerByUser(CustomerUtils.getCurrentCustomer()));
        factor.setType(FactorType.BUY);
        factor.setPaymentType(FactorPaymentType.PAYING);
        factorService.create(factorMapper.entityToDto(factor));

        auditLogService.log(Server.class.getSimpleName(), saveServer.getId(), "CREATE", saveServer.getCreatedBy());
        return saveServer;
    }

    @Override
    public Server findById(Long theId) {
        return serverRepository.findById(theId)
                .orElseThrow(() -> new CustomExceptionHandler("Your server id is not valid"));
    }

    @Override
    public List<Server> findAll() {
        return serverRepository.findAll();
    }

    @Override
    public List<ServerDTO> getServerByUsername() {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        return serverRepository.findServerByUsername(username)
                .stream()
                .map((entity) -> serverMapper.entityToDto(entity))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Server renew(Long serverId, LocalDate newEndDate) {
        Server server = serverRepository
                .findById(serverId).orElseThrow(() -> new CustomExceptionHandler("Your server id for renew is not valid"));

        if (!newEndDate.isAfter(server.getEndDate())) {
            throw new CustomExceptionHandler("Your new date is before previous date.");
        }

        Factor factor = new Factor();
        factor.setAmount(server.getPrice());
        factor.setCustomer(server.getCustomer());
        factor.setServer(server);
        factor.setType(FactorType.RENEW);
        factor.setPaymentType(FactorPaymentType.PAYING);
        factorService.create(factorMapper.entityToDto(factor));

        server.setEndDate(newEndDate);

        Server saved = serverRepository.save(server);
        auditLogService.log(Server.class.getSimpleName(), saved.getId(), "UPDATE", saved.getUpdatedBy());
        return saved;
    }

    @Transactional
    @Override
    public void deleteById(Long theId) {
        Server tempServer = serverRepository.findById(theId)
                .orElseThrow(() -> new CustomExceptionHandler("This server id not valid for delete"));
        serverRepository.delete(tempServer);
        auditLogService.log(Server.class.getSimpleName(), tempServer.getId(), "DELETE", tempServer.getUpdatedBy());
    }


}
