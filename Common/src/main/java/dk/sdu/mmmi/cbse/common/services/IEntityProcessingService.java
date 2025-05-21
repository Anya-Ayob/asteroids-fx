package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for processing entities while the game is running,
 * which therefore updates and modifies the entities
 * (example: could be based on input like in Player)
 */
public interface IEntityProcessingService {

    /**
     * processes all entities, such as moving them around on the game window
     *
     * Preconditions: GameData and World must not be null!
     * And the game must contain entities, that can be processed/modified with
     * Postconditions: The entity will after be modified (such as their position) in the game
     *
     * @param gameData (The current gameData,
     *                 which therefore holds information like displaying width and height of the entities)
     * @param world (the world containing the entities, which can be can moved)
     */
    void process(GameData gameData, World world);
}
