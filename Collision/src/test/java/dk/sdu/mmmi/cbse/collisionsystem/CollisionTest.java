package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollisionTest {
    CollisionDetector collisionDetector;
    World world;
    GameData gameData;

    @BeforeEach
    void setUp() {
        collisionDetector = new CollisionDetector();
        world = new World();
        gameData = new GameData();
    }

    @Test
    void collisionTest() {
        Entity entityA = new Entity();
        Entity entityB = new Entity();

        entityA.setX(100);
        entityA.setY(100);
        entityA.setRadius(10);
        entityB.setX(100);
        entityB.setY(100);
        entityB.setRadius(10);

        world.addEntity(entityA);
        world.addEntity(entityB);

        //starts with checking, if the world contains the entities
        assertTrue("the entityA doesn't exists in the world", world.getEntities().contains(entityA));
        assertTrue("the entityB doesn't exists in the world", world.getEntities().contains(entityB));

        collisionDetector.process(gameData, world);
        assertTrue("The entities didn't collide:", collisionDetector.collides(entityA, entityB));

        //the entities should have been removed now :)
        assertFalse("the entityA isn't removed from the world", world.getEntities().contains(entityA));
        assertFalse("the entityB isn't removed from the world", world.getEntities().contains(entityB));
    }

    @Test
    void noCollisionTest() {
        Entity entityA = new Entity();
        Entity entityB = new Entity();

        entityA.setX(100);
        entityA.setY(100);
        entityA.setRadius(10);
        entityB.setX(150);
        entityB.setY(150);
        entityB.setRadius(10);

        world.addEntity(entityA);
        world.addEntity(entityB);

        //starts with checking, if the world contains the entities
        assertTrue("the entityA doesn't exists in the world", world.getEntities().contains(entityA));
        assertTrue("the entityB doesn't exists in the world", world.getEntities().contains(entityB));

        collisionDetector.process(gameData, world);
        assertFalse("The entities did collide:", collisionDetector.collides(entityA, entityB));

        //The world should still contain the entities :)
        assertTrue("the entityA is removed from the world", world.getEntities().contains(entityA));
        assertTrue("the entityB is removed from the world", world.getEntities().contains(entityB));
    }

    @AfterEach
    void tearDown() {
        world = null;
        gameData = null;
        collisionDetector = null;
    }
}
