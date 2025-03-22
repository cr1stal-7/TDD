package test.java;

import main.java.GameLogic;
import main.java.GameUI;
import main.java.Melody;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
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

    @Test
    public void testAddPlayer() {
        List<String> melodies = List.of("src/main/resources/1.wav");
        List<String> melodyNames = List.of("Моя мелодия");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);
        Melody melody = new Melody();
        GameUI gameUI = new GameUI(gameLogic, melody);
        JPanel playersPanel = gameUI.getPlayersPanel();
        int initialPlayerCount = playersPanel.getComponentCount();
        JButton addPlayerButton = findButtonByText(gameUI, "Добавить игрока");
        assertNotNull(addPlayerButton, "Кнопка 'Добавить игрока' не найдена");
        addPlayerButton.doClick();
        int newPlayerCount = playersPanel.getComponentCount();
        assertEquals(initialPlayerCount + 1, newPlayerCount, "Игрок не был добавлен");

        JPanel playerPanel = (JPanel) playersPanel.getComponent(newPlayerCount - 1);
        Component[] components = playerPanel.getComponents();
        assertTrue(components[0] instanceof JTextField);
        assertTrue(components[1] instanceof JLabel);
        assertTrue(components[2] instanceof JLabel);
        assertTrue(components[3] instanceof JTextField);
        assertTrue(components[4] instanceof JButton);
    }

    private JButton findButtonByText(JFrame frame, String text) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                for (Component subComp : ((JPanel) comp).getComponents()) {
                    if (subComp instanceof JButton && ((JButton) subComp).getText().equals(text)) {
                        return (JButton) subComp;
                    }
                }
            }
        }
        return null;
    }
}
