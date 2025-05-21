package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for processing entities after the game has been set up!
 * Which is used for detecting collision in this project
 */
public interface IPostEntityProcessingService {

    /**
     * Processes the entities after the main processing (IEntityProcessingService)
     *
     * Preconditions: Gamedata and world must not be null!
     * Postconditions: The entities are updated/processed, such as being removed because of collision
     *
     * @param gameData (The current game data, which is not used directly regarding collision)
     * @param world (The world containing the entities, that should be processed/removed because of collision)
     */
    void process(GameData gameData, World world);
}
