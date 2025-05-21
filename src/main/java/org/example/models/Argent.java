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
        var converti = that.convertir(this.devise);

        return new Argent(this.getMontant() + converti.getMontant(),
                this.getDevise());
    }

    public Argent soustraire(Argent that) {
        var converti = that.convertir(this.devise);
        return new Argent(this.getMontant() - converti.getMontant(),
                this.getDevise());
    }

    public Argent convertir(Devise nouvelleDevise) {
        return new Argent(
                (this.getMontant() * this.devise.getValeurEnAriary())
                        / nouvelleDevise.getValeurEnAriary(),
                nouvelleDevise);
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
