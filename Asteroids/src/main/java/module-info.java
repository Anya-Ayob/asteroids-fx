import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IScoringService;

module Asteroid {
    requires Common;
    requires CommonAsteroids;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidProcessor;
    provides IAsteroidSplitter with dk.sdu.mmmi.cbse.asteroid.AsteroidSplitterImpl;
    uses IScoringService;
}