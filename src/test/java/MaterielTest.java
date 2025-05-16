import org.example.models.Argent;
import org.example.models.Devise;
import org.example.models.Materiel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.example.models.Argent.ariary;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaterielTest {

    @Test
    void projection_future_d_un_materiel() {
        var ordinateur = new Materiel("ordinateur",
                LocalDate.of(2021, 10, 26),
                ariary(2_000_000d),
                0.1d,
                LocalDate.of(2021, 10, 26));

        Materiel ordinateur1Jan22 = ordinateur.projectionFuture(LocalDate.of(
                2022, 1, 1
        ));
        Materiel ordinateur1Jan23 = ordinateur.projectionFuture(LocalDate.of(
                2023, 1, 1
        ));

        assertEquals(1_800_000d, ordinateur1Jan22.getValeur().getMontant());
        assertEquals(1_620_000d, ordinateur1Jan23.getValeur().getMontant());
    }
}
