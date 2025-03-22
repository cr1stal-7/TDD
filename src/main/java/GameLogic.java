package main.java;

import java.util.List;

public class GameLogic {
    private List<String> melodies;
    private List<String> melodyNames;

    public GameLogic(List<String> melodies, List<String> melodyNames) {
        this.melodies = melodies;
        this.melodyNames = melodyNames;
    }

    public List<String> getMelodies() {
        return melodies;
    }

    public List<String> getMelodyNames() {
        return melodyNames;
    }
}