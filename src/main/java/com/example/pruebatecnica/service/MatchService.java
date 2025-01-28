package com.example.pruebatecnica.service;

import com.example.pruebatecnica.model.Match;
import com.example.pruebatecnica.model.Tournament;
import com.example.pruebatecnica.repository.MatchRepository;
import com.example.pruebatecnica.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MatchService {


    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository, TournamentRepository tournamentRepository) {
        this.matchRepository = matchRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public Match createMatch(Match match) {
        Optional<Tournament> tournamentOpt = tournamentRepository.findById(match.getTournamentId());

        if (tournamentOpt.isEmpty()) {
            throw new IllegalArgumentException("El torneo no existe");
        }

        Tournament tournament = tournamentOpt.get();
        boolean homeTeamExists = tournament.getTeams().stream()
                .anyMatch(team -> team.getId().equals(match.getHomeTeam().getTeamId()));
        boolean awayTeamExists = tournament.getTeams().stream()
                .anyMatch(team -> team.getId().equals(match.getAwayTeam().getTeamId()));

        if (!homeTeamExists || !awayTeamExists) {
            throw new IllegalArgumentException("Uno o ambos equipos no pertenecen al torneo");
        }

        return matchRepository.save(match);
    }

    public List<Match> getMatchesByTournamentId(String tournamentId) {
        return matchRepository.findByTournamentId(tournamentId);
    }

    public Match updateMatchScore(String matchId, int homeScore, int awayScore) {
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            match.getHomeTeam().setScore(homeScore);
            match.getAwayTeam().setScore(awayScore);
            return matchRepository.save(match);
        }
        return null;
    }
}