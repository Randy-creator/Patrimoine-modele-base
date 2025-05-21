package org.example.models;

import lombok.Getter;

@Getter
public enum Devise {
    EURO(4000d),
    US_DOLLAR(4000d),
    ARIARY(1d);

    private final double valeurEnAriary;

    Devise(double valeurEnAriary) {
        this.valeurEnAriary = valeurEnAriary;
    }

}
