import dk.sdu.mmmi.cbse.common.services.IGUISkinService;
import dk.sdu.mmmi.cbse.skin.ImageRender;


module Skin {
    opens images;
    provides IGUISkinService with ImageRender;
    requires CommonAsteroids;
    requires CommonBullet;
    requires CommonPlayer;
    requires CommonEnemy;
    requires Common;
    requires javafx.graphics;
}