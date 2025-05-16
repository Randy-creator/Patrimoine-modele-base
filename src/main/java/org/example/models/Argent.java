package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Argent {
    private final Double montant;
    private final Devise devise;

    public Argent additionner(Argent that) {
        return new Argent(this.getMontant() + that.getMontant(),
                this.getDevise());
    }

    public Argent soustraire(Argent that) {
        return new Argent(this.getMontant() - that.getMontant(),
                this.getDevise());
    }

    public Argent multiplier(double facteur) {
        return new Argent(this.getMontant() * facteur,
                this.getDevise());
    }

    public static Argent ariary(double montant) {
        return new Argent(montant, Devise.ARIARY);
    }
    public static Argent euro(double montant) {
        return new Argent(montant, Devise.EURO);
    }
    public static Argent dollar(double montant) {
        return new Argent(montant, Devise.US_DOLLAR);
    }
}
