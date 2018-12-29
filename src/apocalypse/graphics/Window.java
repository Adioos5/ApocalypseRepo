package apocalypse.graphics;

import apocalypse.elements.Enemy;
import apocalypse.elements.Shot;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import apocalypse.mechanics.TileMapReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Window extends Application {

    private Pane root;
    private ImageView player;
    private ImageView enemy;
    private AnimationTimer timer;
    private double enemySpeed = 1;
    private double mouseX = 0;
    private double mouseY = 0;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean moveDown = false;
    private float playerAngle;
    private List<Shot> shots = new ArrayList<>();
    private int tileSize = 48;
    private int[][] map;
    private List<Enemy> enemies = new ArrayList<>();
    private List<ImageView> tiles = new ArrayList<>();
    private MediaPlayer mediaPlayer;

    public Window() {

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (Math.random() < 0.01)
                    initEnemy();
                for (ImageView tile : tiles) {
                    if (moveRight)
                        tile.setX(tile.getX() - 5);
                    if (moveLeft && tiles.get(0).getX() <= 0)
                        tile.setX(tile.getX() + 5);
                    if (moveDown)
                        tile.setY(tile.getY() - 5);
                    if (moveUp && tiles.get(0).getY() <= 0)
                        tile.setY(tile.getY() + 5);
                }
                try {
                    for (Shot shot : shots) {
                        shot.getShotRect().setTranslateX(shot.getShotRect().getTranslateX() + Math.cos(shot.getShotAngle()) * 12);
                        shot.getShotRect().setTranslateY(shot.getShotRect().getTranslateY() + Math.sin(shot.getShotAngle()) * 12);
                        for (Enemy enemy : enemies) {
                            if (shot.getShotRect().getBoundsInParent().intersects(enemy.getImg().getBoundsInParent())) {
                                enemy.getImg().setImage(new Image("apocalypse/images/blood.png"));
                                enemy.setSpeed(0);
                                enemies.remove(enemy);
                                root.getChildren().remove(shot.getShotRect());
                                shots.remove(shot);
                                root.getChildren().remove(player);
                                root.getChildren().add(player);
                            }
                        }
                    }
                    for (Enemy enemy : enemies) {
                        //zombieMovement
                        int diffX = (int) (player.getX() - enemy.getImg().getX());
                        int diffY = (int) (player.getY() - enemy.getImg().getY());

                        float angle = (float) Math.atan2(diffY, diffX);

                        enemy.getImg().setX(enemy.getImg().getX() + enemy.getSpeed() * Math.cos(angle));
                        enemy.getImg().setY(enemy.getImg().getY() + enemy.getSpeed() * Math.sin(angle));

                        enemy.getImg().setRotate(Math.toDegrees(angle));
                    }
                } catch (Exception e) {

                }
            }
        };
    }


    public void runApplication() {
        launch(Window.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createRoot()));
        stage.setWidth(1300);
        stage.setHeight(700);
        stage.setTitle("Apocalypse");
        stage.setResizable(false);
        stage.getScene().setOnKeyPressed(e -> {


            switch (e.getCode()) {
                case W:
                    moveUp = true;
                    break;
                case S:
                    moveDown = true;
                    break;
                case D:
                    moveRight = true;
                    break;
                case A:
                    moveLeft = true;
                    break;

            }

        });
        stage.getScene().setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W:
                    moveUp = false;
                    break;
                case S:
                    moveDown = false;
                    break;
                case D:
                    moveRight = false;
                    break;
                case A:
                    moveLeft = false;
                    break;
            }
        });
        stage.getScene().setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();

            //playerRotation
            playerAngle = (float) Math.toDegrees(Math.atan2(mouseY - player.getY(), mouseX - player.getX()));
            player.setRotate(playerAngle);

        });
        stage.getScene().setOnMouseClicked(e -> {
            initShot();
        });
        stage.show();
    }

    private Node initShot() {
        ImageView shotRect = new ImageView(new Image("apocalypse/images/ammo.png"));
        double shotAngle = Math.atan2(mouseY - player.getY(), mouseX - player.getX());

        Shot shot = new Shot(shotRect, shotAngle);

        double x = player.getX() + 22;
        double y = player.getY() + 20;
        shotRect.setX(x);
        shotRect.setY(y);

        shotRect.setRotate(playerAngle);

        shots.add(shot);
        tiles.add(shotRect);
        root.getChildren().add(shotRect);

        return shotRect;
    }

    private Node initPlayer() {
        player = new ImageView(new Image("apocalypse/images/NAA Infantry.png"));
        player.setFitHeight(60);
        player.setFitWidth(60);
        player.setX(620);
        player.setY(320);


        return player;
    }

    private Enemy initEnemy() {
        Enemy enemy = new Enemy(enemySpeed, new ImageView(new Image("apocalypse/images/zombiee.png")));
        enemy.getImg().setFitHeight(50);
        enemy.getImg().setFitWidth(50);
        enemy.getImg().setX((Math.random() * ((700 - 0) + 1)) + 0);
        enemy.getImg().setY(0);

        enemies.add(enemy);
        tiles.add(enemy.getImg());
        root.getChildren().add(enemy.getImg());

        return enemy;
    }

    private Pane createRoot() {
        root = new Pane();

        final URL songPath = getClass().getResource("/apocalypse/music/Oliver Heldens - Gecko (Original Mix).mp3");
        Media sound = new Media(songPath.toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                mediaPlayer.play();

            }
        });

        mediaPlayer.play();

        TileMapReader mapReader = new TileMapReader();
        map = mapReader.readMap();

        for (int i = 0; i < map.length; i++) {
            for (int k = 0; k < map[0].length; k++) {
                if (map[i][k] == 0) {
                    ImageView img = new ImageView(new Image("apocalypse/images/night grass.png"));

                    img.setX(k * tileSize);
                    img.setY(i * tileSize);
                    img.setFitWidth(tileSize);
                    img.setFitHeight(tileSize);
                    tiles.add(img);
                    root.getChildren().add(img);
                } else if (map[i][k] == 1) {
                    ImageView img = new ImageView(new Image("apocalypse/images/night pavement.png"));

                    img.setX(k * tileSize);
                    img.setY(i * tileSize);
                    img.setFitWidth(tileSize);
                    img.setFitHeight(tileSize);
                    tiles.add(img);
                    root.getChildren().add(img);
                } else if (map[i][k] == 2) {
                    ImageView img = new ImageView(new Image("apocalypse/images/night sand.png"));

                    img.setX(k * tileSize);
                    img.setY(i * tileSize);
                    img.setFitWidth(tileSize);
                    img.setFitHeight(tileSize);
                    tiles.add(img);
                    root.getChildren().add(img);
                } else if (map[i][k] == 3) {
                    ImageView img = new ImageView(new Image("apocalypse/images/night water.png"));

                    img.setX(k * tileSize);
                    img.setY(i * tileSize);
                    img.setFitWidth(tileSize);
                    img.setFitHeight(tileSize);
                    tiles.add(img);
                    root.getChildren().add(img);
                }
            }
        }

        root.getChildren().add(initPlayer());

        timer.start();
        return root;
    }
}
