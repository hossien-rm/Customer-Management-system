package com.cms.service;

import com.cms.dto.FactorDTO;
import com.cms.entity.Factor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FactorService {

    Factor create(FactorDTO dto);

    List<Factor> findAll();

    List<FactorDTO> getFactorByUsername();

    List<Factor> findFactor(String username);

    @Transactional
    void deleteById(Long theId);
}
