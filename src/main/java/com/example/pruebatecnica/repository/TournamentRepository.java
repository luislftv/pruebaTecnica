package com.example.pruebatecnica.repository;

import com.example.pruebatecnica.model.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TournamentRepository extends MongoRepository<Tournament, String> {
}