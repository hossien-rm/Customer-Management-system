package com.cms.contorller;

import com.cms.dto.ServerDTO;
import com.cms.mapper.ServerMapper;
import com.cms.service.ServerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Servers", description = "Create, renew, view and delete servers.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServerController {

    private final ServerService serverService;
    private final ServerMapper serverMapper;


    @Operation(summary = "Create a server", description = "Creates a server from the supplied details. Public endpoint.")
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/server")
    public ServerDTO createServer(@RequestBody ServerDTO dto) {
        return serverMapper.entityToDto(serverService.create(dto));
    }

    @Operation(summary = "Renew a server", description = "Updates the server renewal date. Send a JSON string in YYYY-MM-DD format. Public endpoint.")
    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/server/{theId}/renew")
    public ServerDTO renewServer(@PathVariable Long theId, @RequestBody LocalDate date) {
        return serverMapper.entityToDto(serverService.renew(theId, date));
    }

    @Operation(summary = "List servers", description = "Returns all servers. Requires ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/server")
    public List<ServerDTO> findAllServers() {
        return serverMapper.listEntityToDto(serverService.findAll());
    }

    @Operation(summary = "Get a server", description = "Returns a server by ID. Requires ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/server/{theId}")
    public ServerDTO findByIdServer(@PathVariable Long theId) {
        return serverMapper.entityToDto(serverService.findById(theId));
    }

    @Operation(summary = "Delete a server", description = "Deletes a server by ID. Requires authentication.")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @DeleteMapping("/server/{theId}")
    public void deleteByIdSever(@PathVariable Long theId) {
        serverService.deleteById(theId);
    }

}
