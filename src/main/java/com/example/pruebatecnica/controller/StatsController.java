package com.example.pruebatecnica.controller;

import com.example.pruebatecnica.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, ?>>> getAllTeamsStats(@PathVariable String tournamentId) {
        return ResponseEntity.ok(statsService.getAllTeamsStats(tournamentId));
    }
}