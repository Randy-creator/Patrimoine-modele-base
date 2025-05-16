package org.example.models;

import java.time.LocalDate;

public final class TrainDeVie extends Possession {

    public TrainDeVie(String nomDeLaPossession, LocalDate aDateDe, Argent valeur) {
        super(nomDeLaPossession, aDateDe, valeur);
    }

    @Override
    public Possession projectionFuture(LocalDate dateFuture) {
        if (dateFuture.isBefore(this.getADateDe())) {
            return new TrainDeVie(
                    nom,
                    this.getADateDe(),
                    new Argent(0d, this.getValeur().getDevise())
            );
        }


        return new TrainDeVie(nom,
                this.getADateDe(),
                new Argent(this.getValeur().getMontant(),
                        this.getValeur().getDevise()));
    }
}
