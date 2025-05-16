package org.example.models;

import java.time.LocalDate;

public final class Compte extends Possession {
    public Compte(String nomDeLaPossession, LocalDate aDateDe, Argent valeur) {
        super(nomDeLaPossession, aDateDe, valeur);
    }

    @Override
    public Possession projectionFuture(LocalDate dateFuture) {
        if (dateFuture.isBefore(this.getADateDe())) {
            return new Compte(
                    nom,
                    this.getADateDe(),
                    new Argent(0d, this.getValeur().getDevise())
            );
        }

        return new Compte(nom,
                this.getADateDe(),
                new Argent(this.getValeur().getMontant(),
                        this.getValeur().getDevise()));
    }
}
