package dk.sdu.mmmi.cbse.skin;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.services.IGUISkinService;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class ImageRender implements IGUISkinService {

    private Pane pane;
    private final Map<Class<? extends Entity>, Image> imageMap = new HashMap<>();
    private final Map<String, ImageView> imageViews = new HashMap<>();
    private boolean initialized = false;

    @Override
    public void renderImages(Entity entity) {
        if (pane == null) {
            System.out.println("No pane was found!");
            return;
        }
        ImageView view = imageViews.get(entity.getID());
        if (view == null) {
            Image image = imageMap.get(entity.getClass());
            if (image == null) {
                System.out.println("No image found for " + entity.getClass());
                return;
            }
            view = new ImageView(image);
            view.setFitWidth(entity.getRadius()*5);
            view.setFitHeight(entity.getRadius()*5);
            imageViews.put(entity.getID(), view);
            pane.getChildren().add(view);
        }
        view.setX(entity.getX());
        view.setY(entity.getY());
        view.setRotate(entity.getRotation());
    }

    @Override
    public void setupPane(Pane pane) {
        if (initialized) return;

        this.pane = pane;
        registerImages(Asteroid.class, "images/AsteroidBase.png");
        registerImages(Bullet.class, "images/bullet.png");
        registerImages(Enemy.class, "images/enemyship.png");
        registerImages(Player.class, "images/playership.png");
        initialized = true;

        //setting up the pane background :)
        String backgroundPath = "images/asteroidsbackground.gif";
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(backgroundPath));
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(backgroundImage));
    }

    public void registerImages(Class<? extends Entity> entityType, String filepath){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(filepath));
        if (image == null) {
            throw new RuntimeException("Could not load image " + filepath);
        }
        imageMap.put(entityType, image);
    }

    @Override
    public void removeImages(Entity entity) {
        ImageView imageView = imageViews.remove(entity.getID());
        if (imageView != null && pane != null) {
            pane.getChildren().remove(imageView);
        }
    }
}
