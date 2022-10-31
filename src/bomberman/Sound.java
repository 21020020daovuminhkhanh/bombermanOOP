package bomberman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/Battle.mid");
        soundURL[1] = getClass().getResource("/sounds/Bonus.mid");
        soundURL[2] = getClass().getResource("/sounds/Die.mid");
        soundURL[3] = getClass().getResource("/sounds/Explosion.mid");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioIS = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioIS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
