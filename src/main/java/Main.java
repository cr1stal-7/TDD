package main.java;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        java.util.List<String> melodies = java.util.List.of(
                "src/main/resources/1.wav", "src/main/resources/2.wav", "src/main/resources/3.wav",
                "src/main/resources/4.wav", "src/main/resources/5.wav", "src/main/resources/6.wav",
                "src/main/resources/7.wav", "src/main/resources/8.wav", "src/main/resources/9.wav"
        );
        java.util.List<String> melodyNames = List.of(
                "5sta Family - Моя мелодия", "5sta Family - Я буду", "Гречка - здесь были",
                "Мираж - Новый герой", "Дурной Вкус - Пластинки", "SEREBRO - Мало тебя",
                "МакSим - Трудный возраст", "30.02 - Звёзды в лужах", "Eminem - Mockingbird"
        );
        GameLogic gameLogic = new GameLogic(melodies, melodyNames);
        Melody melodyPlayer = new Melody();
        new GameUI(gameLogic, melodyPlayer);
    }
}
