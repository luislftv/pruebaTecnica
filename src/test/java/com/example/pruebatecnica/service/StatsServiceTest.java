package com.example.pruebatecnica.service;

import com.example.pruebatecnica.repository.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StatsServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTeamsStats_Success() {
        // Arrange
        String tournamentId = "tournament123";

        MatchRepository.TeamStatsProjection statsMock = mock(MatchRepository.TeamStatsProjection.class);
        when(statsMock.getTeamId()).thenReturn("team1");
        when(statsMock.getTeamName()).thenReturn("Team 1");
        when(statsMock.getMatchesPlayed()).thenReturn(5);
        when(statsMock.getGoalsScored()).thenReturn(15);

        when(matchRepository.getAllTeamsStats(tournamentId)).thenReturn(List.of(statsMock));

        // Act
        List<Map<String, ?>> result = statsService.getAllTeamsStats(tournamentId);

        // Assert
        assertEquals(1, result.size());
        Map<String, ?> teamStats = result.get(0);
        assertEquals("team1", teamStats.get("teamId"));
        assertEquals("Team 1", teamStats.get("teamName"));
        assertEquals(5, teamStats.get("matchesPlayed"));
        assertEquals(15, teamStats.get("goalsScored"));
        verify(matchRepository, times(1)).getAllTeamsStats(tournamentId);
    }
}