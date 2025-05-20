import org.example.models.Compte;
import org.example.models.TrainDeVie;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.example.models.Argent.ariary;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompteTest {

    @Test
    void projection_future_compte() {
        Compte compteCourant = new Compte("compte courant",
                LocalDate.of(2024, 5, 13),
                ariary(600_000d),
                LocalDate.of(2024, 5, 13)
        );

        TrainDeVie trainDeVie = new TrainDeVie("vie quotidienne",
                LocalDate.of(2024, 5, 13),
                ariary(500_000d),
                compteCourant,
                1,
                LocalDate.of(2024, 5, 13));

        var actual = compteCourant.projectionFuture(LocalDate.of(
                2024,
                7,
                26));

        assertEquals(-400_000d, actual.getValeur().getMontant());
    }
}
