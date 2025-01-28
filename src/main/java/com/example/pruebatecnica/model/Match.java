package com.example.pruebatecnica.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "matches")
public class Match {
    @Id
    private String id;

    @NotBlank(message = "El ID del torneo es obligatorio")
    private String tournamentId;

    @NotNull(message = "El equipo local es obligatorio")
    private TeamResult homeTeam;

    @NotNull(message = "El equipo visitante es obligatorio")
    private TeamResult awayTeam;

    @NotNull(message = "La fecha del partido es obligatoria")
    private Date date;

    @Data
    public static class TeamResult {
        @NotBlank(message = "El ID del equipo es obligatorio")
        private String teamId;
        private int score;
    }
}
