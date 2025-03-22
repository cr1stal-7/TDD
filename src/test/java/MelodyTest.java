package test.java;

import main.java.Melody;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class MelodyTest {

    @Test
    public void testPlayMelody() throws InterruptedException {
        Melody melody = new Melody();
        melody.playMelody("src/main/resources/1.wav");
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
    }

    @Test
    public void testStopMelody() throws InterruptedException {
        Melody melody = new Melody();
        melody.playMelody("src/main/resources/1.wav");
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        assertTrue(melody.isPlaying());
        melody.stopMelody();
        assertFalse(melody.isPlaying());
    }
}
