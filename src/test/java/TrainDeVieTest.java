import org.example.models.Compte;
import org.example.models.TrainDeVie;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.example.models.Argent.ariary;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainDeVieTest {

    @Test
    void projection_future_de_train_de_vie() {
        Compte compteCourant = new Compte("compte courant",
                LocalDate.of(2024, 5, 13),
                ariary(600_000d));

        TrainDeVie trainDeVie = new TrainDeVie("vie quotidienne",
                LocalDate.of(2024, 5, 13),
                ariary(500_000d),
                compteCourant,
                1,
                LocalDate.of(2024, 5, 13));

        var actual = trainDeVie.projectionFuture(LocalDate.of(2024,
                6,
                26));

        assertEquals(100_000d, actual.getFinanceur().getValeur().getMontant());
    }
}
