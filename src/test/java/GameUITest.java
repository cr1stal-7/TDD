package test.java;

import main.java.GameLogic;
import main.java.GameUI;
import main.java.Melody;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void testMaxPlayers() {
        List<String> melodies = List.of("src/main/resources/1.wav");
        List<String> melodyNames = List.of("Моя мелодия");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);
        Melody melody = new Melody();
        GameUI gameUI = new GameUI(gameLogic, melody);
        JPanel playersPanel = gameUI.getPlayersPanel();
        for (int i = 0; i < 3; i++) {
            JButton addPlayerButton = findButtonByText(gameUI, "Добавить игрока");
            assertNotNull(addPlayerButton, "Кнопка 'Добавить игрока' не найдена");
            addPlayerButton.doClick();
        }
        assertEquals(3, playersPanel.getComponentCount(), "Количество игроков должно быть 3");
        JButton addPlayerButton = findButtonByText(gameUI, "Добавить игрока");
        assertNotNull(addPlayerButton, "Кнопка 'Добавить игрока' не найдена");
        addPlayerButton.doClick();
        assertEquals(3, playersPanel.getComponentCount(), "Количество игроков не должно превышать 3");
    }


    @Test
    public void testStartGameAddsMelodyButtons() {
        List<String> melodies = List.of("src/main/resources/1.wav", "src/main/resources/2.wav");
        List<String> melodyNames = List.of("Моя мелодия", "Я буду");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);
        Melody melody = new Melody();
        GameUI gameUI = new GameUI(gameLogic, melody);

        JButton startButton = findButtonByText(gameUI, "НАЧАТЬ ИГРУ");
        assertNotNull(startButton, "Кнопка 'НАЧАТЬ ИГРУ' не найдена");
        startButton.doClick();

        JPanel melodiesPanel = (JPanel) gameUI.getContentPane().getComponent(2);
        assertEquals(2, melodiesPanel.getComponentCount(), "На панели мелодий должно быть 2 кнопки");
    }

    @Test
    public void testStartGameEnablesComponents() {
        List<String> melodies = List.of("src/main/resources/1.wav");
        List<String> melodyNames = List.of("Моя мелодия");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);
        Melody melody = new Melody();
        GameUI gameUI = new GameUI(gameLogic, melody);

        JButton addPlayerButton = findButtonByText(gameUI, "Добавить игрока");
        assertNotNull(addPlayerButton, "Кнопка 'Добавить игрока' не найдена");
        addPlayerButton.doClick();
        JPanel playersPanel = gameUI.getPlayersPanel();
        JPanel playerPanel = (JPanel) playersPanel.getComponent(0);

        JTextField melodyField = (JTextField) playerPanel.getComponent(3);
        JButton submitButton = (JButton) playerPanel.getComponent(4);
        assertFalse(melodyField.isVisible(), "Поле для ответа должно быть скрыто до начала игры");
        assertFalse(submitButton.isVisible(), "Кнопка 'Отправить' должна быть скрыто до начала игры");

        JButton startButton = findButtonByText(gameUI, "НАЧАТЬ ИГРУ");
        assertNotNull(startButton, "Кнопка 'НАЧАТЬ ИГРУ' не найдена");
        startButton.doClick();
        assertTrue(melodyField.isVisible(), "Поле для ответа должно быть доступно для редактирования после начала игры");
        assertTrue(submitButton.isVisible(), "Кнопка 'Отправить' должна быть доступна после начала игры");
    }

    @Test
    public void testStartGameDisablesNameFields() {
        List<String> melodies = List.of("src/main/resources/1.wav");
        List<String> melodyNames = List.of("Моя мелодия");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);
        Melody melody = new Melody();
        GameUI gameUI = new GameUI(gameLogic, melody);

        JButton addPlayerButton = findButtonByText(gameUI, "Добавить игрока");
        assertNotNull(addPlayerButton, "Кнопка 'Добавить игрока' не найдена");
        addPlayerButton.doClick();
        JPanel playersPanel = gameUI.getPlayersPanel();
        JPanel playerPanel = (JPanel) playersPanel.getComponent(0);
        JTextField nameField = (JTextField) playerPanel.getComponent(0);
        assertTrue(nameField.isEditable(), "Поле для имени должно быть доступно для редактирования до начала игры");

        JButton startButton = findButtonByText(gameUI, "НАЧАТЬ ИГРУ");
        assertNotNull(startButton, "Кнопка 'НАЧАТЬ ИГРУ' не найдена");
        startButton.doClick();
        assertFalse(nameField.isEditable(), "Поле для имени должно быть недоступно для редактирования после начала игры");
    }

    @Test
    public void testMelodyButtonPlaysMelody() throws InterruptedException {
        List<String> melodies = List.of("src/main/resources/1.wav", "src/main/resources/2.wav");
        List<String> melodyNames = List.of("Моя мелодия", "Я буду");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);
        Melody melody = new Melody();
        GameUI gameUI = new GameUI(gameLogic, melody);

        JButton startButton = findButtonByText(gameUI, "НАЧАТЬ ИГРУ");
        assertNotNull(startButton, "Кнопка 'НАЧАТЬ ИГРУ' не найдена");
        startButton.doClick();

        JPanel melodiesPanel = (JPanel) gameUI.getContentPane().getComponent(2);
        JButton melodyButton = (JButton) melodiesPanel.getComponent(0);
        melodyButton.doClick();
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        assertTrue(melody.isPlaying(), "Мелодия должна воспроизводиться после нажатия на кнопку");
    }

    @Test
    public void testPlayerAnswer() {
        List<String> melodies = List.of("src/main/resources/1.wav");
        List<String> melodyNames = List.of("Моя мелодия");
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);
        Melody melody = new Melody();
        GameUI gameUI = new GameUI(gameLogic, melody);

        JButton addPlayerButton = findButtonByText(gameUI, "Добавить игрока");
        assertNotNull(addPlayerButton, "Кнопка 'Добавить игрока' не найдена");
        addPlayerButton.doClick();
        JPanel playersPanel = gameUI.getPlayersPanel();
        JPanel playerPanel = (JPanel) playersPanel.getComponent(0);

        JLabel scoreLabel = (JLabel) playerPanel.getComponent(1);
        JTextField melodyField = (JTextField) playerPanel.getComponent(3);
        JButton submitButton = (JButton) playerPanel.getComponent(4);

        gameLogic.setCurrentMelodyIndex(0);
        assertEquals("Баллы: 0", scoreLabel.getText(), "Начальный счет должен быть 0");
        melodyField.setText("Неправильный ответ");
        submitButton.doClick();
        assertEquals("Баллы: 0", scoreLabel.getText(), "Счет не должен измениться при неправильном ответе");
        assertEquals("", melodyField.getText(), "Поле для ответа должно очиститься после отправки");

        melodyField.setText("Моя мелодия");
        submitButton.doClick();
        assertEquals("Баллы: 1", scoreLabel.getText(), "Счет должен увеличиться на 1 при правильном ответе");
        assertEquals("", melodyField.getText(), "Поле для ответа должно очиститься после отправки");
        assertFalse(melody.isPlaying(), "Мелодия должна была остановиться после правильного ответа");
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
