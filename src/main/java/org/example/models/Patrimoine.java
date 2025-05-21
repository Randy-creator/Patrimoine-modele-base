package org.example.models;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Patrimoine {
    private final Personne proprietaire;
    private final LocalDate date;
    private final Set<Possession> possessions;

    public Patrimoine projectionFuture(LocalDate dateFuture) {
        Set<Possession> possessionFuture = possessions.stream().map(
                possession -> possession.projectionFuture(dateFuture)
        ).collect(Collectors.toSet());

        return new Patrimoine(proprietaire, dateFuture, possessionFuture);
    }
}
