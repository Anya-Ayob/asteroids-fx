package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author corfixen
 */
public class AsteroidPlugin implements IGamePluginService {
    private final ScheduledExecutorService sheduler = new ScheduledThreadPoolExecutor(1);

    @Override
    public void start(GameData gameData, World world) {
        sheduler.scheduleAtFixedRate(() -> {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        Random rnd = new Random();
        int size = rnd.nextInt(10) + 5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(0);
        asteroid.setY(0);
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        return asteroid;
    }

    public Entity createSmallerAsteroid(Entity a, boolean firstHalf) {
        Entity asteroid = new Asteroid();
        int size = (int) (a.getRadius()/2);
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setRadius(size);
        if (firstHalf) {
            asteroid.setX(a.getX()+size);
            asteroid.setY(a.getY()+size);
            asteroid.setRotation(a.getRotation()*1.5);
        } else {
            asteroid.setX(a.getX()-size);
            asteroid.setY(a.getY()-size);
            asteroid.setRotation(a.getRotation()*-1.5);
        }
        return asteroid;
    }
}
