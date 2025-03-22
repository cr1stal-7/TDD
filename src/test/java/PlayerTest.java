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

    @Test
    public void testInitialScore() {
        Player player = new Player("Bob");
        assertEquals(0, player.getScore());
    }
}