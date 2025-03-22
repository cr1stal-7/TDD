package main.java;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Melody {
    private Clip currentClip;

    public void playMelody(String melodyPath) {
        try {
            File audioFile = new File(melodyPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying() {
        return currentClip != null && currentClip.isRunning();
    }

    public void stopMelody() {
        if (isPlaying()) {
            currentClip.stop();
        }
    }
}
