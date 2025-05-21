package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.services.ISoundService;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SoundService implements ISoundService {
    private MediaPlayer musicPlayer;
    private final List<MediaPlayer> activeMediaPlayers = new ArrayList<>();

    @Override
    public void playSoundEffect(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null ) {
            throw new IllegalArgumentException("The sound file for '"+ fileName +"' was not found");
        }
        Media media = new Media(resource.toExternalForm());
        MediaPlayer soundEffectPlayer = new MediaPlayer(media);
        activeMediaPlayers.add(soundEffectPlayer);

        soundEffectPlayer.setOnReady(() -> soundEffectPlayer.play());
        soundEffectPlayer.setOnEndOfMedia(() -> {
            soundEffectPlayer.stop();
            activeMediaPlayers.remove(soundEffectPlayer);
        });
    }

    @Override
    public void playMusic(String fileName) {
        stopMusic(); //if still playing :)
        if (musicPlayer == null) {
            URL resource = getClass().getClassLoader().getResource(fileName);
            if (resource == null ) {
                throw new IllegalArgumentException("The sound file for '"+ fileName +"' was not found");
            }
            Media media = new Media(resource.toExternalForm());
            musicPlayer = new MediaPlayer(media);
            musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            musicPlayer.setOnReady(() -> musicPlayer.play());
            activeMediaPlayers.add(musicPlayer);
        }
    }

    @Override
    public void stopMusic() {
        if (musicPlayer != null) {
            musicPlayer.stop();
            activeMediaPlayers.remove(musicPlayer);
        }
    }
}