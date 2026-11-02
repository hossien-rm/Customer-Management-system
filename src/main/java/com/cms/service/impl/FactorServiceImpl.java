package com.cms.service.impl;


import com.cms.dto.FactorDTO;
import com.cms.entity.Factor;
import com.cms.entity.Server;
import com.cms.exception.CustomExceptionHandler;
import com.cms.mapper.FactorMapper;
import com.cms.repository.FactorRepository;
import com.cms.service.CustomerService;
import com.cms.service.FactorService;
import com.cms.service.ServerService;
import com.cms.util.CustomerUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactorServiceImpl implements FactorService {

    private final FactorRepository factorRepository;
    private final FactorMapper factorMapper;
    private final CustomerService customerService;
    private final ServerService serverService;
    private final AuditLogServiceImpl auditLogService;

    public FactorServiceImpl(FactorRepository factorRepository,
                             FactorMapper factorMapper, CustomerService customerService,
                             @Lazy ServerService serverService,
                             AuditLogServiceImpl auditLogService) {
        this.factorRepository = factorRepository;
        this.factorMapper = factorMapper;
        this.customerService = customerService;
        this.serverService = serverService;
        this.auditLogService = auditLogService;
    }


    @Transactional
    @Override
    public Factor create(FactorDTO dto) {

        Factor tempFactor = factorMapper.dtoToEntity(dto);

        Server server = serverService.findById(dto.getServerId());

        tempFactor.setCustomer(customerService.findCustomerByUser(CustomerUtils.getCurrentCustomer()));
        tempFactor.setServer(server);

        Factor factor = factorRepository.save(tempFactor);
        auditLogService.log(Factor.class.getSimpleName(), factor.getId(), "CREATE", factor.getCreatedBy());
        return factor;
    }

    @Override
    public List<Factor> findAll() {
        return factorRepository.findAll();
    }

    @Override
    public List<Factor> findFactor(String username) {
        return factorRepository.findFactorByUsername(username);
    }

    @Override
    public List<FactorDTO> getFactorByUsername() {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return factorRepository.findFactorByUsername(username)
                .stream()
                .map((entity) -> factorMapper.entityToDto(entity))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteById(Long theId) {
        Factor tempFactor = factorRepository.findById(theId)
                .orElseThrow(() -> new CustomExceptionHandler("This factor id not valid for delete"));
        factorRepository.delete(tempFactor);
        auditLogService.log(Factor.class.getSimpleName(), tempFactor.getId(), "DELETE", tempFactor.getUpdatedBy());
    }


}
