package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for starting and stopping game plugins,
 * which can be useful in cases of adding or removing entities from the world.
 */
public interface IGamePluginService {

    /**
     * Initializes the plugin and sets up the world (such as adding relevant entities when starting the game).
     *
     * Preconditions: GameData and World must not be nulL!
     * Postconditions: The relevant entity has been added to the world
     *
     * @param gameData (The current gameData)
     * @param world (the world containing the entities, therefore you can add an entity to the world)
     */
    void start(GameData gameData, World world);

    /**
     * Works as clean up - is responsible for removing the entity
     *
     * Preconditions: GameData and World must not be nulL! + There must be an entity to remove
     * Postconditions: The entity has been removed
     *
     * @param gameData (The current gameData)
     * @param world (the world containing the entities, therefore you remove an entity from the world)
     */
    void stop(GameData gameData, World world);
}
