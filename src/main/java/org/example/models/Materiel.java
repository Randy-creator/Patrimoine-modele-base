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
            return
                    new Materiel(this.getNomDeLaPossession(),
                            dateFuture,
                            new Argent(0d, Devise.US_DOLLAR),
                            this.tauxDAppreciation,
                            this.dateDAcquisition);
        }
        Materiel materiel = null;

        while (differenceDeDate > 0) {
            Double valeurActuelle = this.getValeur().getMontant() * 1 - (this.tauxDAppreciation / 100);

            materiel = new Materiel(this.getNomDeLaPossession(),
                    dateFuture,
                    new Argent(valeurActuelle, Devise.US_DOLLAR),
                    this.tauxDAppreciation,
                    this.dateDAcquisition);
        }
        return materiel;
    }

    public static void main(String[] args) {
        Materiel tshirt = new Materiel("t-shirt"
                , LocalDate.of(2026, 01, 01),
                new Argent(20000d, Devise.ARIARY),
                20d,
                LocalDate.of(2025, 01, 01));

        System.out.println(tshirt);
    }
}
