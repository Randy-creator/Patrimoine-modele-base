package org.example.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public abstract sealed class Possession permits Compte, Materiel, TrainDeVie {
    private final String nomDeLaPossession;
    private final LocalDate aDateDe;
    private final Argent valeur;

    public abstract Possession projectionFuture(LocalDate dateFuture);
}
