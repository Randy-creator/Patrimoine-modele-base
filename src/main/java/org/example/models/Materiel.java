package org.example.models;

import lombok.Getter;

import java.time.LocalDate;

@Getter
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


        Double valeurFuture = this.getValeur().getMontant() * Math.pow((1 - this.getTauxDAppreciation() / 100), differenceDeDate);

        return new Materiel(this.getNomDeLaPossession(),
                dateFuture,
                new Argent(valeurFuture, this.getValeur().getDevise()),
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
