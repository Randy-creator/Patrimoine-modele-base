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

        double facteurDAmortissementAnnuel = Math.pow((1 - this.getTauxDAppreciation()), anneeEcoulee);

        Argent valeurFuture = valeur.multiplier(facteurDAmortissementAnnuel);

        return new Materiel(nom,
                dateFuture,
                valeurFuture,
                this.tauxDAppreciation, this.dateDAcquisition);
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2030, 1, 1);
        LocalDate dateDe = LocalDate.of(2025, 1, 1);
        Materiel tshirt = new Materiel("t-shirt",
                dateDe,
                new Argent(20000d, Devise.ARIARY),
                20d,
                LocalDate.of(2025, 1, 1));

        System.out.println(tshirt.projectionFuture(date));
    }
}
