import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonAsteroids;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
    uses IAsteroidSplitter;
}