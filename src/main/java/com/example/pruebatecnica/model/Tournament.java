package com.example.pruebatecnica.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "tournaments")
public class Tournament {
    @Id
    private String id;

    @NotBlank(message = "El nombre del torneo es obligatorio")
    private String name;

    @NotBlank(message = "El deporte es obligatorio")
    private String sport;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private Date startDate;

    private Date endDate;
    private List<Team> teams;

    @Data
    @AllArgsConstructor
    public static class Team {
        private String id;
        private String name;
    }
}
