package test.java;

import main.java.GameLogic;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    @Test
    public void testGameLogicCreation() {
        List<String> melodies = List.of("src/main/resources/1.wav");
        List<String> melodyNames = List.of("Моя мелодия");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);

        assertEquals(melodies, gameLogic.getMelodies());
        assertEquals(melodyNames, gameLogic.getMelodyNames());
    }

    @Test
    public void testSetAndGetCurrentMelodyIndex() {
        List<String> melodies = List.of("src/main/resources/1.wav");
        List<String> melodyNames = List.of("Моя мелодия");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);

        gameLogic.setCurrentMelodyIndex(0);
        assertEquals(0, gameLogic.getCurrentMelodyIndex());
    }
}