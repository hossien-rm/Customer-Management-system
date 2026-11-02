package com.cms.contorller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.cms.dto.FactorDTO;
import com.cms.mapper.FactorMapper;
import com.cms.service.FactorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Factors", description = "Create, view and delete customer billing factors.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FactorController {

    private final FactorService factorService;
    private final FactorMapper factorMapper;


    @Operation(summary = "List factors", description = "Returns all billing factors. Requires ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/factor")
    public List<FactorDTO> findAllFactor() {
        return factorMapper.listEntityToDto(factorService.findAll());
    }

    @Operation(summary = "Get a factor", description = "Returns a billing factor by ID. Requires authentication.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/factor/{username}")
    public List<FactorDTO> findFactorByUsername(@PathVariable String username) {
        return factorMapper.listEntityToDto(factorService.findFactor(username));
    }

    @Operation(summary = "Delete a factor", description = "Deletes a billing factor by ID. Requires ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/factor/{theId}")
    public void deleteFactorById(@PathVariable Long theId) {
        factorService.deleteById(theId);
    }

}
