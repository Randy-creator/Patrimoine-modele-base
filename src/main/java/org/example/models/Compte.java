package org.example.models;

import java.time.LocalDate;

public final class Compte extends Possession{
    public Compte(String nomDeLaPossession, LocalDate aDateDe, Argent valeur) {
        super(nomDeLaPossession, aDateDe, valeur);
    }

    @Override
    public Possession projectionFuture(LocalDate dateFuture) {
        return null;
    }
}
