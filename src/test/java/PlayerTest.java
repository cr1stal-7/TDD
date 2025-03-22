package test.java;

import main.java.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerCreation() {
        Player player = new Player("Alice");
        assertEquals("Alice", player.getName());
    }
}