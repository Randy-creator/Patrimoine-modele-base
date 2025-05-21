package org.example.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public abstract sealed class Possession permits Compte, Materiel, TrainDeVie {
    protected final String nom;
    protected final LocalDate aDateDe;
    protected final Argent valeur;

    public abstract Possession projectionFuture(LocalDate dateFuture);

    public Argent valeurFutur(LocalDate dateFutur, Devise devise) {
        return projectionFuture(dateFutur).getValeur().convertir(devise);
    }
}
