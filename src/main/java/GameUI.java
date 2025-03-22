package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI extends JFrame {
    private GameLogic gameLogic;
    private Melody melody;
    private JPanel playersPanel;
    private JButton addPlayerButton;

    public GameUI(GameLogic gameLogic, Melody melody) {
        this.gameLogic = gameLogic;
        this.melody = melody;

        setTitle("Угадай мелодию");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel addPlayerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addPlayerButton = new JButton("Добавить игрока");
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayer();
            }
        });
        addPlayerPanel.add(addPlayerButton);
        add(addPlayerPanel, BorderLayout.NORTH);

        playersPanel = new JPanel();
        playersPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(playersPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addPlayer() {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField(10);
        JLabel scoreLabel = new JLabel("Баллы: 0");
        JTextField melodyField = new JTextField(15);
        JButton submitButton = new JButton("Отправить");

        playerPanel.add(nameField);
        playerPanel.add(scoreLabel);
        playerPanel.add(new JLabel("Ответ:"));
        playerPanel.add(melodyField);
        playerPanel.add(submitButton);

        playersPanel.add(playerPanel);

        revalidate();
        repaint();
    }

    public JPanel getPlayersPanel() {
        return playersPanel;
    }
}