package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImpl implements IAsteroidSplitter {
    AsteroidPlugin asteroidPlugin = new AsteroidPlugin();

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        if (e instanceof Asteroid) {
            if (e.getRadius() >= 5) {
                Entity asteroid1 = asteroidPlugin.createSmallerAsteroid(e, true);
                Entity asteroid2 = asteroidPlugin.createSmallerAsteroid(e, false);
                world.addEntity(asteroid1);
                world.addEntity(asteroid2);
            }
        }
    }
}
