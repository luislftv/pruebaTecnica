package com.example.pruebatecnica.service;

import com.example.pruebatecnica.model.Tournament;
import com.example.pruebatecnica.repository.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private TournamentService tournamentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTournament_Success() {
        // Arrange
        Tournament tournament = new Tournament();
        tournament.setName("Champions League");
        tournament.setSport("FOOTBALL");
        tournament.setStartDate(new Date());
        tournament.setTeams(List.of(new Tournament.Team("team1", "Real Madrid")));

        when(tournamentRepository.save(any(Tournament.class))).thenReturn(tournament);

        // Act
        Tournament savedTournament = tournamentService.createTournament(tournament);

        // Assert
        assertEquals("Champions League", savedTournament.getName());
        assertEquals("FOOTBALL", savedTournament.getSport());
        verify(tournamentRepository, times(1)).save(tournament);
    }
}
