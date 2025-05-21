package dk.sdu.mmmi.cbse.common.services;

/**
 * Handles the music for the system, such as background music and/or sound effects
 * Can be used different places around in the project
 */
public interface ISoundService {

    /**
     * Takes a soundfile and plays it as a sound effect (once)! It therefore only plays during the duration of the sound effect
     * @param fileName (only path from source root, since the method handles it itself through
     *                 getClass().getClassLoader().getResource(fileName).toExternalForm())
     */
    public void playSoundEffect(String fileName);

    /**
     * Plays music (such as background music) for an indefinite amount of time
     * @param fileName (only path from source root, since the method handles it itself through
     *                 getClass().getClassLoader().getResource(fileName).toExternalForm())
     */
    public void playMusic(String fileName);

    /**
     * Stops the music if something is currently playing (only for Music, not sound effects!)
     */
    public void stopMusic();
}
