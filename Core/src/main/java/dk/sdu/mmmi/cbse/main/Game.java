package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private ISoundService soundService;
    private IGUISkinService skinService;
    private IScoringService scoringService;
    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private int newScore;
    private Text scoreText;

    Game(List<IGamePluginService> gamePluginServices, List<IEntityProcessingService> entityProcessingServices, List<IPostEntityProcessingService> postEntityProcessingServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServices = entityProcessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
    }

    @Override
    public void start(Stage window) throws Exception {
        soundService = getSoundService();
        skinService = getSkinService();
        scoringService = getScoringService();

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        scoreText = new Text(10, 20, "Destroyed asteroids: "+ newScore);
        gameWindow.getChildren().add(scoreText);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }

        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : gamePluginServices) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        render();
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
        if (soundService != null) {
            String backgroundMusic = "audios/battleoftheholy.mp3";
            soundService.playMusic(backgroundMusic);
        }
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : entityProcessingServices) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessingServices) {
            postEntityProcessorService.process(gameData, world);
        }

        if (scoringService != null) {
            newScore = scoringService.getScore();
            scoreText.setText("Destroyed asteroids: "+ newScore);
        }
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }
        //deleting the imageViews
        if (skinService != null) {
            Set<String> currentEntityIds = world.getEntities().stream().map(Entity::getID).collect(Collectors.toSet());
            Set<String> previouslyRenderedEntityIds = new HashSet<>(skinService.getActiveEntitiesID());
            previouslyRenderedEntityIds.removeAll(currentEntityIds);

            for (String id : previouslyRenderedEntityIds) {
                skinService.removeImages(id);
            }
        }

        for (Entity entity : world.getEntities()) {
            if (skinService != null) {
                skinService.setupPane(gameWindow);
                skinService.renderImages(entity);
            } else {
                Polygon polygon = polygons.get(entity);
                if (polygon == null) {
                    polygon = new Polygon(entity.getPolygonCoordinates());
                    polygons.put(entity, polygon);
                    gameWindow.getChildren().add(polygon);
                }
                polygon.setTranslateX(entity.getX());
                polygon.setTranslateY(entity.getY());
                polygon.setRotate(entity.getRotation());
            }
        }

    }


    /*
    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

     */

    public ISoundService getSoundService() {
        return ServiceLoader.load(ISoundService.class).stream().map(ServiceLoader.Provider::get).findFirst().orElse(null);
    }

    public IGUISkinService getSkinService() {
        return ServiceLoader.load(IGUISkinService.class).stream().map(ServiceLoader.Provider::get).findFirst().orElse(null);
    }

    public IScoringService getScoringService() {
        return ServiceLoader.load(IScoringService.class).stream().map(ServiceLoader.Provider::get).findFirst().orElse(null);
    }

}
