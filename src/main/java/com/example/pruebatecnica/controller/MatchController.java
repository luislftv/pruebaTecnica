package com.example.pruebatecnica.controller;

import com.example.pruebatecnica.model.Match;
import com.example.pruebatecnica.service.MatchService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<?> createMatch(@PathVariable String tournamentId, @Valid @RequestBody Match match) {
        try {
            match.setTournamentId(tournamentId);
            Match savedMatch = matchService.createMatch(match);
            return ResponseEntity.ok(savedMatch);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Match>> getMatchesByTournamentId(@PathVariable String tournamentId) {
        List<Match> matches = matchService.getMatchesByTournamentId(tournamentId);
        return ResponseEntity.ok(matches);
    }

    @PutMapping("/{matchId}/score")
    public ResponseEntity<Match> updateMatchScore(
            @PathVariable String matchId,
            @RequestBody ScoreUpdateRequest scoreUpdateRequest) {

        Match updatedMatch = matchService.updateMatchScore(matchId, scoreUpdateRequest.getHomeScore(), scoreUpdateRequest.getAwayScore());
        if (updatedMatch != null) {
            return ResponseEntity.ok(updatedMatch);
        }
        return ResponseEntity.notFound().build();
    }
}

 @Data
 class ScoreUpdateRequest {
    private int homeScore;
    private int awayScore;

}