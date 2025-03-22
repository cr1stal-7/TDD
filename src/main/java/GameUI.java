package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameUI extends JFrame {
    private GameLogic gameLogic;
    private Melody melody;
    private JPanel playersPanel;

    private JPanel melodiesPanel;

    private JButton startButton;
    private JButton addPlayerButton;
    private List<Component> hiddenComponents;
    private int playerCount = 0;
    private boolean isMelodySelected = false;

    public GameUI(GameLogic gameLogic, Melody melody) {
        this.gameLogic = gameLogic;
        this.melody = melody;
        this.hiddenComponents = new ArrayList<>();

        setTitle("Угадай мелодию");
        setSize(740, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel addPlayerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addPlayerButton = new JButton("Добавить игрока");
        addPlayerButton.setPreferredSize(new Dimension(200, 35));
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayer();
            }
        });
        addPlayerPanel.add(addPlayerButton);
        addPlayerPanel.add(Box.createRigidArea(new Dimension(280, 0)));

        startButton = new JButton("НАЧАТЬ ИГРУ");
        startButton.setPreferredSize(new Dimension(200, 35));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        addPlayerPanel.add(startButton);
        add(addPlayerPanel, BorderLayout.NORTH);

        playersPanel = new JPanel();
        playersPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(playersPanel, BorderLayout.CENTER);

        melodiesPanel = new JPanel();
        melodiesPanel.setLayout(new GridLayout(3, 3, 10, 10));
        add(melodiesPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addPlayer() {
        if (playerCount < 3) {
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

            JTextField nameField = new JTextField(25);
            JLabel scoreLabel = new JLabel("Баллы: 0");
            JTextField melodyField = new JTextField(25);
            JLabel answerLabel = new JLabel("Ответ:");
            JButton submitButton = new JButton("Отправить");

            hiddenComponents.add(melodyField);
            hiddenComponents.add(answerLabel);
            hiddenComponents.add(submitButton);
            for (Component component : hiddenComponents) {
                component.setVisible(false);
            }

            playerPanel.add(nameField);
            playerPanel.add(scoreLabel);
            playerPanel.add(answerLabel);
            playerPanel.add(melodyField);
            playerPanel.add(submitButton);

            playersPanel.add(playerPanel);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!isMelodySelected) {
                        JOptionPane.showMessageDialog(GameUI.this, "Мелодия не выбрана!", "Ошибка", JOptionPane.WARNING_MESSAGE);
                        melodyField.setText("");
                        return;
                    }
                    String answer = melodyField.getText();
                    if (gameLogic.checkAnswer(answer)) {
                        int score = Integer.parseInt(scoreLabel.getText().split(": ")[1]);
                        scoreLabel.setText("Баллы: " + (score + 1));
                        melody.stopMelody();
                        isMelodySelected = false;
                        updateMelodyButton(gameLogic.getCurrentMelodyIndex());
                    }
                    melodyField.setText("");
                }
            });

            playerCount++;
            revalidate();
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Максимальное количество игроков (3) достигнуто!");
        }
    }

    private void updateMelodyButton(int melodyIndex) {
        JButton melodyButton = (JButton) melodiesPanel.getComponent(melodyIndex);
        JLabel melodyLabel = new JLabel(gameLogic.getMelodyNames().get(melodyIndex), SwingConstants.CENTER);
        melodyLabel.setPreferredSize(new Dimension(200, 100));
        melodyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        melodiesPanel.remove(melodyButton);
        melodiesPanel.add(melodyLabel, melodyIndex);
        melodiesPanel.revalidate();
        melodiesPanel.repaint();
    }

    public JPanel getPlayersPanel() {
        return playersPanel;
    }

    private void startGame() {
        for (Component component : playersPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel playerPanel = (JPanel) component;
                JTextField nameField = (JTextField) playerPanel.getComponent(0);
                nameField.setEditable(false);
            }
        }
        melodiesPanel.removeAll();
        for (String music : gameLogic.getMelodies()) {
            JButton melodyButton = new JButton("Мелодия " + (gameLogic.getMelodies().indexOf(music) + 1));
            melodyButton.setPreferredSize(new Dimension(200, 100));
            int melodyIndex = gameLogic.getMelodies().indexOf(music);
            melodyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameLogic.setCurrentMelodyIndex(melodyIndex);
                    melody.playMelody(music);
                    isMelodySelected = true;
                }
            });
            melodiesPanel.add(melodyButton);
        }
        for (Component component : hiddenComponents) {
            component.setVisible(true);
        }
        revalidate();
        repaint();
    }
}