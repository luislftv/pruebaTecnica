package com.example.pruebatecnica.controller;

import com.example.pruebatecnica.model.Tournament;
import com.example.pruebatecnica.service.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@Tag(name = "Tournaments", description = "Endpoints para la gestión de torneos")
public class TournamentController {
    private final TournamentService service;

    public TournamentController(TournamentService service) {
        this.service = service;
    }

    @Operation(summary = "Crear nuevo torneo", description = "Registra un torneo en mongo")
    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return service.createTournament(tournament);
    }

    @Operation(summary = " Listar torneos", description = "Lista todos los torneos")
    @GetMapping
    public List<Tournament> getAllTournaments() {
        return service.getAllTournaments();
    }

    @Operation(summary = " Obtener detalle de un torneo", description = "Retorna los detalles de un torneo con el id")
    @GetMapping("/{id}")
    public Tournament getTournamentById(@Parameter(description = "ID del torneo", example = "65c1aaf0....") @PathVariable String id) {

        return service.getTournamentById(id);
    }
}