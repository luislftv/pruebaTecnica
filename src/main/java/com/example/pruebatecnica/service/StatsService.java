package com.example.pruebatecnica.service;

import com.example.pruebatecnica.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final MatchRepository matchRepository;

    public StatsService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Map<String, ?>> getAllTeamsStats(String tournamentId) {
        List<MatchRepository.TeamStatsProjection> stats = matchRepository.getAllTeamsStats(tournamentId);

        System.out.println(stats);
        return stats.stream()
                .map(stat -> Map.of(
                        "teamId", requireNonNullElse(stat.getTeamId(), "unknown"),
                        "teamName", requireNonNullElse(stat.getTeamName(), "unknown"),
                        "matchesPlayed", stat.getMatchesPlayed(),
                        "goalsScored", stat.getGoalsScored()
                ))
                .collect(Collectors.toList());
    }

    private <T> T requireNonNullElse(T obj, T defaultObj) {
        return (obj != null) ? obj : defaultObj;
    }
}