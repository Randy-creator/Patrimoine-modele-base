package org.example.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.example.models.Argent.ariary;


public final class Compte extends Possession {
    private final Set<TrainDeVie> trainDeVies;
    private final LocalDate dateDeCreation;

    public Compte(String nomDeLaPossession, LocalDate aDateDe, Argent valeur, LocalDate dateDeCreation) {
        super(nomDeLaPossession, aDateDe, valeur);
        this.trainDeVies = new HashSet<>();
        this.dateDeCreation = dateDeCreation;
    }

    private Compte(String nomDeLaPossession, LocalDate aDateDe, Argent valeur, LocalDate dateDeCreation, Set<TrainDeVie> trainDeVies) {
        super(nomDeLaPossession, aDateDe, valeur);
        this.dateDeCreation = dateDeCreation;
        this.trainDeVies = trainDeVies;
    }


    @Override
    public Compte projectionFuture(LocalDate dateFuture) {
        if (dateDeCreation.isAfter(dateFuture)) {
            return new Compte(
                    nom,
                    dateFuture,
                    ariary(0d),
                    dateDeCreation
                    );
        }

        Argent financementFutur = calculerFinancementFutur(dateFuture);
        return  new Compte(
                nom,
                dateFuture,
                valeur.soustraire(financementFutur),
                dateDeCreation,
                this.trainDeVies
        );
    }

    private Argent calculerFinancementFutur(LocalDate dateFuture) {
        Argent acc = ariary(0);
        Iterator<TrainDeVie> iterator = trainDeVies.iterator();
        while(iterator.hasNext()) {
            Argent valeur1 = iterator.next().projectionFuture(dateFuture).valeur;
            acc = acc.additionner(valeur1);
        }
        return acc;
    }

    public void financer(TrainDeVie trainDeVie) {
        trainDeVies.add(trainDeVie);
    }
}
