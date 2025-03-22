package test.java;

import main.java.Melody;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;

public class MelodyTest {

    @Test
    public void testPlayMelody() throws InterruptedException {
        Melody melody = new Melody();
        melody.playMelody("src/main/resources/1.wav");
        Thread.sleep(TimeUnit.SECONDS.toMillis(10));
    }
}
