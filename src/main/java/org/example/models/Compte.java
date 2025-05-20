package org.example.models;

import java.time.LocalDate;
import java.util.HashSet;
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

        Argent sommeTotaleTrainDeVie = trainDeVies.stream()
                .map(trainDeVie ->
                trainDeVie.projectionFuture(dateFuture).valeur
                ).reduce(ariary(0), Argent::additionner);

        return  new Compte(
                nom,
                dateFuture,
                valeur.soustraire(sommeTotaleTrainDeVie),
                dateDeCreation,
                this.trainDeVies
        );
    }

    public void financer(TrainDeVie trainDeVie) {
        trainDeVies.add(trainDeVie);
    }
}
