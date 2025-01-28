package com.example.pruebatecnica.service;

import com.example.pruebatecnica.model.Tournament;
import com.example.pruebatecnica.repository.TournamentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class TournamentService {
    private final TournamentRepository repository;

    public TournamentService(TournamentRepository repository) {

        this.repository = repository;
    }

    public Tournament createTournament(Tournament tournament) {
        return repository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return repository.findAll();
    }

    public Tournament getTournamentById(String id) {
        return repository.findById(id).orElse(null);
    }
}