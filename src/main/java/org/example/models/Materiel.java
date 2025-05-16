package org.example.models;

import java.time.LocalDate;

public final class Materiel extends Possession {
    private final Double tauxDAppreciation;
    private final LocalDate dateDAcquisition;

    public Materiel(String nomDeLaPossession, LocalDate aDateDe, Argent valeur, Double tauxDAppreciation, LocalDate dateDAcquisition) {
        super(nomDeLaPossession, aDateDe, valeur);
        this.tauxDAppreciation = tauxDAppreciation;
        this.dateDAcquisition = dateDAcquisition;
    }

    @Override
    public Possession projectionFuture(LocalDate dateFuture) {
        int differenceDeDate = dateFuture.getYear() - this.dateDAcquisition.getYear();

        if (differenceDeDate < 0) {
            return new Materiel(this.getNomDeLaPossession()
                    , dateFuture, new Argent(0d, this.getValeur().getDevise()),
                    this.tauxDAppreciation,
                    this.dateDAcquisition);
        }


        Double valeurFuture = this.getValeur().getMontant() - (this.getValeur().getMontant() * (Math.pow((this.tauxDAppreciation / 100), differenceDeDate)));

        return new Materiel(this.getNomDeLaPossession(),
                dateFuture,
                new Argent(valeurFuture, this.getValeur().getDevise()),
                this.tauxDAppreciation, this.dateDAcquisition);
    }
}
