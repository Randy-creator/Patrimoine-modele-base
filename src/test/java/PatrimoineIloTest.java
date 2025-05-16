import org.example.models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;

public class PatrimoineIloTest {

    @Test
    void patrimoineDeIlo() {
        Personne ilo = new Personne("ilo");
        Patrimoine patrimoineDeIlo = new Patrimoine(ilo, LocalDate.of(2024, Month.JUNE, 26), Set.of());
        LocalDate aDateDe = LocalDate.of(2024, 5, 13);

        Compte espece = new Compte("espece",
                aDateDe,
                new Argent(400_000d, Devise.ARIARY));


        Compte compteEpargne = new Compte("compte epargne"
                , aDateDe,
                new Argent(200_000d, Devise.ARIARY));


        Compte compteCourant = new Compte("compte courant",
                aDateDe,
                new Argent(600_000d, Devise.ARIARY));


        Materiel ordinateur = new Materiel("ordinateur",
                aDateDe,
                new Argent(2_000_000d, Devise.ARIARY),
                10d,
                LocalDate.of(2021, Month.OCTOBER, 26));

        Materiel effetVestimentaire = new Materiel("effet vestimentaire",
                aDateDe,
                new Argent(1_000_000d, Devise.ARIARY),
                20d,
                LocalDate.of(2024, Month.JANUARY, 1));
    }
}
