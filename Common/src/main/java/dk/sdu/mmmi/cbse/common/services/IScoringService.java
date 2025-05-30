package dk.sdu.mmmi.cbse.common.services;

/**
 * Interface for sending and recieving the score, when an asteroid has been destroyed by the player
 */
public interface IScoringService {

    /**
     * Sends the score with the asteroid hitten
     */
    void sendScore(int point);

    /**
     * Retrieves the score
     * @return string of the points
     */
    int getScore();
}
