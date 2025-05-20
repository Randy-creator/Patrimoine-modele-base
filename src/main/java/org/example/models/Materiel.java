package org.example.models;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public final class Materiel extends Possession {
    private final Double tauxDAppreciation;
    private final LocalDate dateDAcquisition;

    public Materiel(String nom, LocalDate aDateDe, Argent valeur, Double tauxDAppreciation, LocalDate dateDAcquisition) {
        super(nom, aDateDe, valeur);
        this.tauxDAppreciation = tauxDAppreciation;
        this.dateDAcquisition = dateDAcquisition;
    }

    @Override
    public Materiel projectionFuture(LocalDate dateFuture) {
        if (dateFuture.isBefore(dateDAcquisition)) {
            return new Materiel(nom
                    , dateFuture, new Argent(0d, this.getValeur().getDevise()),
                    this.tauxDAppreciation,
                    this.dateDAcquisition);
        }

        int anneeEcoulee = dateFuture.getYear() - this.dateDAcquisition.getYear();

        Argent valeurDeLAmortissement = valeur.multiplier(tauxDAppreciation);

        Argent valeurFuture = valeur.soustraire(valeurDeLAmortissement.multiplier(anneeEcoulee));

        return new Materiel(nom,
                dateFuture,
                valeurFuture,
                this.tauxDAppreciation, this.dateDAcquisition);
    }
}
