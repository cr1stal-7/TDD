package main.java;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame {
    private GameLogic gameLogic;
    private Melody melody;

    public GameUI(GameLogic gameLogic, Melody melody) {
        this.gameLogic = gameLogic;
        this.melody = melody;

        setTitle("Угадай мелодию");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setVisible(true);
    }
}
