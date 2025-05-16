package org.example.models;

import java.time.LocalDate;

public final class Compte extends Possession {
    public Compte(String nomDeLaPossession, LocalDate aDateDe, Argent valeur) {
        super(nomDeLaPossession, aDateDe, valeur);
    }

    @Override
    public Possession projectionFuture(LocalDate dateFuture) {
//        Compte compte = switch (dateFuture) {
//            case dateFuture.isBefore(this.aDateDe) -> new Compte(this.getNomDeLaPossession(),
//                    this.getADateDe(),
//                    new Argent(0d,
//                            this.getValeur().getDevise()));
//
//            default -> new Compte(this.getNomDeLaPossession(),
//                    this.getADateDe(),
//                    new Argent(this.getValeur().getMontant(),
//                            this.getValeur().getDevise()));

        if (dateFuture.isBefore(this.getADateDe())) {
            return new Compte(
                    this.getNomDeLaPossession(),
                    this.getADateDe(),
                    new Argent(0d, this.getValeur().getDevise())
            );
        }

        return new Compte(this.getNomDeLaPossession(),
                this.getADateDe(),
                new Argent(this.getValeur().getMontant(),
                        this.getValeur().getDevise()));
    }
}
