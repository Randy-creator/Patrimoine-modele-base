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

        this.financeur.financer(this);
        // todo: infinite loop need debug
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

        long nombreDOperation = debutDeLaPonction
                .datesUntil(dateFuture.plusDays(1))
                .filter(date ->
                date.getDayOfMonth() == jourDOperation
                ).count();

        Argent argentFuture = valeur.multiplier(nombreDOperation);

        return new TrainDeVie(
                nom,
                dateFuture,
                argentFuture,
                financeur,
                jourDOperation,
                debutDeLaPonction);
    }
}
