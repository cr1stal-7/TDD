package test.java;

import main.java.GameLogic;
import main.java.GameUI;
import main.java.Melody;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GameUITest {

    @Test
    public void testGameUICreation() {
        List<String> melodies = List.of("src/main/resources/1.wav");
        List<String> melodyNames = List.of("Моя мелодия");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);
        Melody melody = new Melody();
        GameUI gameUI = new GameUI(gameLogic, melody);

        assertNotNull(gameUI);
    }
}
