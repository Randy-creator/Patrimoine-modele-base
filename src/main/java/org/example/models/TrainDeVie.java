package org.example.models;

import lombok.Getter;

import java.time.LocalDate;

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
            Argent valeurRestante = financeur.getValeur()
                    .soustraire(valeur.multiplier(moisEcoulee));

            Compte financeurFuture = new Compte(financeur.nom, dateFuture, valeurRestante);

            return new TrainDeVie(nom,
                    aDateDe,
                    financeur.valeur,
                    financeurFuture,
                    jourDOperation,
                    debutDeLaPonction);
        } else {
            Argent valeurRestante = financeur.getValeur()
                    .soustraire(valeur.multiplier(moisEcoulee - 1));

            Compte financeurFuture = new Compte(financeur.nom, dateFuture, valeurRestante);

            return new TrainDeVie(nom
                    , aDateDe
                    , valeurRestante
                    , financeurFuture,
                    jourDOperation,
                    debutDeLaPonction);
        }
    }
}
