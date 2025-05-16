package org.example.models;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import static java.time.temporal.ChronoUnit.MONTHS;
import static org.example.models.Argent.ariary;

@Getter
public final class TrainDeVie extends Possession {
    private final Compte financeur;
    private final int jourDOperation;
    private final LocalDate debutDeLaPonction;

    public TrainDeVie(String nom, LocalDate aDateDe, Argent valeur, Compte financeur, int jourDOperation, LocalDate debutDeLaPonction) {
        super(nom, aDateDe, valeur);
        this.financeur = financeur;
        this.jourDOperation = jourDOperation;
        this.debutDeLaPonction = debutDeLaPonction;
    }

    @Override
    public TrainDeVie projectionFuture(LocalDate dateFuture) {
        if (dateFuture.isBefore(debutDeLaPonction)) {
            return new TrainDeVie(
                    nom,
                    dateFuture,
                    ariary(0d),
                    financeur,
                    jourDOperation,
                    debutDeLaPonction
            );
        }

        long moisEcoulee = MONTHS.between(debutDeLaPonction, dateFuture);

        if (dateFuture.getDayOfMonth() >= jourDOperation) {
            Argent valeurFuture = financeur.getValeur()
                    .soustraire(valeur.multiplier(moisEcoulee));
        }
        return null;
    }
}
