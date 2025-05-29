package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import javafx.scene.layout.Pane;

import java.util.Set;

/**
 * Adds skin/asset for entities/the world
 */
public interface IGUISkinService {

    /**
     * Renders the entities with images, based on the Entity type!
     * @param entity
     */
    void renderImages(Entity entity);

    /**
     * Sets up the Pane for the image views
     * @param pane
     */
    void setupPane(Pane pane);

    /**
     * Removes the images of the entities again based on their id (after collision/death)
     * @param id
     */
    void removeImages(String id);

    /**
     * Returns the IDs of the entities currently in the imageViews map!
     * @return
     */
    Set<String> getActiveEntitiesID();
}
