package apocalypse.mechanics.handlers;


import apocalypse.UI.root.RootModifier;
import apocalypse.UI.root.nodes.*;
import apocalypse.UI.containers.ImageContainer;
import apocalypse.interfaces.Handler;
import apocalypse.mechanics.HandlersSwitcher;
import apocalypse.mechanics.readers.TileMapReader;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameLogicHandler implements Handler {

    private RootModifier rootModifier;
    private ImageContainer imageContainer;
    private HandlersSwitcher handlersSwitcher;
    private UsageSpot craftingTable;
    private UsageSpot mysteriousChest;
    private UsageSpot drugShop;
    private UsageSpot ammoShop;

    private int tileSize = 48;
    private double enemySpeed = 1;
    private double shotSpeed = 12;
    private double mouseX = 0;
    private double mouseY = 0;
    private double enemyAppearanceChances = 0.05;
    private float playerAngle;
    private int[][] map;

    private boolean moveRight = false;
    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean moveDown = false;

    private Player player;

    private MediaPlayer mediaPlayer;

    private List<UsageSpot>usageSpots = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Shot> shots = new ArrayList<>();
    private List<ImageView> movableTiles = new ArrayList<>();
    private List<Text> movableTexts = new ArrayList<>();
    private List<ImageView> bloodTraces = new ArrayList<>();

    private AnimationTimer timer;

    public GameLogicHandler(RootModifier rootModifier, ImageContainer imageContainer, HandlersSwitcher handlersSwitcher) {
        this.rootModifier = rootModifier;
        this.imageContainer = imageContainer;
        this.handlersSwitcher = handlersSwitcher;
    }

    @Override
    public void launchHandler() {
        initRoot();
    }

    @Override
    public void disableHandler() {
        emptyLists();
        stopProcesses();
    }

    public void initRoot() {
        initTimer();
        initMusic();
        initMap();
        rootModifier.add(initPlayer());

        timer.start();
    }

    public void keyPressed(KeyEvent e) {
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
            case C:
                for (ImageView bloodTrace : bloodTraces) {
                    rootModifier.remove(bloodTrace);
                }
                for (Enemy enemy : enemies) {
                    rootModifier.remove(enemy.getImg());
                }
                bloodTraces.clear();
                enemies.clear();

        }
    }

    public void keyReleased(KeyEvent e) {
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
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        //playerRotation
        playerAngle = (float) Math.toDegrees(Math.atan2(mouseY - player.getImg().getY(), mouseX - player.getImg().getX()));
        player.getImg().setRotate(playerAngle);
    }

    public void mouseClicked(MouseEvent e) {
        initShot();
    }

    private void initTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (Math.random() < enemyAppearanceChances) {
                    initEnemy();
                    for (UsageSpot usageSpot:usageSpots) {
                        if (rootModifier.contains(usageSpot.getNotification().getText())) {
                            rootModifier.remove(usageSpot.getNotification().getRectangle());
                            rootModifier.remove(usageSpot.getNotification().getText());

                            rootModifier.add(usageSpot.getNotification().getRectangle());
                            rootModifier.add(usageSpot.getNotification().getText());

                        }
                    }
                }

                for (UsageSpot usageSpot:usageSpots){
                    if(player.getImg().getBoundsInParent().intersects(usageSpot.getImg().getBoundsInParent())&&!rootModifier.contains(usageSpot.getText())){
                        rootModifier.add(usageSpot.getText());
                        rootModifier.add(usageSpot.getNotification().getRectangle());
                        rootModifier.add(usageSpot.getNotification().getText());
                    } else if(!player.getImg().getBoundsInParent().intersects(usageSpot.getImg().getBoundsInParent())&&rootModifier.contains(usageSpot.getText())){
                        rootModifier.remove(usageSpot.getText());
                        rootModifier.remove(usageSpot.getNotification().getRectangle());
                        rootModifier.remove(usageSpot.getNotification().getText());
                    }
                }

                handleMovableTiles();
                handleMovableTexts();
                handleShotMechanics();
                handleEnemiesMovementMechanics();

            }
        };
    }

    public void initShot() {

        ImageView shotRect = new ImageView(imageContainer.getShell());
        double shotAngle = Math.atan2(mouseY - player.getImg().getY(), mouseX - player.getImg().getX());

        Shot shot = new Shot(shotRect, shotAngle);

        double x = player.getImg().getX() + 22;
        double y = player.getImg().getY() + 20;
        shotRect.setX(x);
        shotRect.setY(y);

        shotRect.setRotate(playerAngle);

        shots.add(shot);
        movableTiles.add(shotRect);
        rootModifier.add(shotRect);


    }

    private Node initPlayer() {
        player = new Player(new ImageView(imageContainer.getPlayer()));

        player.getImg().setFitHeight(60);
        player.getImg().setFitWidth(60);
        player.getImg().setX(620);
        player.getImg().setY(320);


        return player.getImg();
    }

    private Enemy initEnemy() {
        Enemy enemy = new Enemy(enemySpeed, new ImageView(imageContainer.getZombie()));
        enemy.getImg().setFitHeight(50);
        enemy.getImg().setFitWidth(50);
        enemy.getImg().setX((Math.random() * ((1300 - 0) + 1)) + 0);

        if (enemy.getImg().getX() < 100 || enemy.getImg().getX() > 1200)
            enemy.getImg().setY((Math.random() * ((700 - 0) + 1)) + 0);
        else if ((int) (Math.random() * 10) < 5) {
            enemy.getImg().setY(0);
        } else {
            enemy.getImg().setY(700);
        }

        enemies.add(enemy);
        movableTiles.add(enemy.getImg());
        rootModifier.add(enemy.getImg());

        return enemy;
    }

    private void initMap() {
        TileMapReader mapReader = new TileMapReader();
        map = mapReader.readMap();

        for (int i = 0; i < map.length; i++) {
            for (int k = 0; k < map[0].length; k++) {
                if (map[i][k] == 0) {

                    Tile grass = new Tile(new ImageView(imageContainer.getGrass()), tileSize, k * tileSize, i * tileSize);

                    movableTiles.add(grass.getImg());
                    rootModifier.add(grass.getImg());
                } else if (map[i][k] == 1) {

                    Tile pavement = new Tile(new ImageView(imageContainer.getPavement()), tileSize, k * tileSize, i * tileSize);

                    movableTiles.add(pavement.getImg());
                    rootModifier.add(pavement.getImg());
                } else if (map[i][k] == 2) {

                    Tile sand = new Tile(new ImageView(imageContainer.getSand()), tileSize, k * tileSize, i * tileSize);

                    movableTiles.add(sand.getImg());
                    rootModifier.add(sand.getImg());
                } else if (map[i][k] == 3) {

                    Tile water = new Tile(new ImageView(imageContainer.getWater()), tileSize, k * tileSize, i * tileSize);

                    movableTiles.add(water.getImg());
                    rootModifier.add(water.getImg());
                }
            }
        }

        craftingTable = new UsageSpot(new ImageView(imageContainer.getCraftingTable()),new Text("Crafting Table"),96,96,2300,740,new Notification(new Rectangle(200,100, Color.BLACK), new Text("Press \"e\" to open crafting table")), new CraftingTableHandler());

        movableTiles.add(craftingTable.getImg());
        movableTexts.add(craftingTable.getText());
        rootModifier.add(craftingTable.getImg());
        rootModifier.add(craftingTable.getText());

        mysteriousChest = new UsageSpot(new ImageView(imageContainer.getMysteriousChest()),new Text("Mysterious Chest"),192,96,1700,740,new Notification(new Rectangle(200,100, Color.BLACK), new Text("Press \"e\" to open mysterious chest")), new MysteriousChestHandler());

        movableTiles.add(mysteriousChest.getImg());
        movableTexts.add(mysteriousChest.getText());
        rootModifier.add(mysteriousChest.getImg());
        rootModifier.add(mysteriousChest.getText());

        usageSpots.add(craftingTable);
        usageSpots.add(mysteriousChest);

    }

    private void initMusic() {

        final URL songPath = getClass().getResource("/apocalypse/media/music/Oliver Heldens - Gecko (Original Mix).mp3");
        Media sound = new Media(songPath.toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                mediaPlayer.play();

            }
        });

        mediaPlayer.play();
    }

    private void handleShotMechanics() {
        try {
            for (Shot shot : shots) {
                shot.getShotRect().setTranslateX(shot.getShotRect().getTranslateX() + Math.cos(shot.getShotAngle()) * shotSpeed);
                shot.getShotRect().setTranslateY(shot.getShotRect().getTranslateY() + Math.sin(shot.getShotAngle()) * shotSpeed);
                for (Enemy enemy : enemies) {
                    if (shot.getShotRect().getBoundsInParent().intersects(enemy.getImg().getBoundsInParent())) {
                        enemy.getImg().setImage(imageContainer.getBlood());
                        enemy.setSpeed(0);

                        enemies.remove(enemy);
                        bloodTraces.add(enemy.getImg());

                        shots.remove(shot);
                        rootModifier.remove(shot.getShotRect());

                        repaint();
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    private void handleEnemiesMovementMechanics() {

        try {
            for (Enemy enemy : enemies) {
                //zombieMovement
                int diffX = (int) (player.getImg().getX() - enemy.getImg().getX());
                int diffY = (int) (player.getImg().getY() - enemy.getImg().getY());

                float angle = (float) Math.atan2(diffY, diffX);

                enemy.getImg().setX(enemy.getImg().getX() + enemy.getSpeed() * Math.cos(angle));
                enemy.getImg().setY(enemy.getImg().getY() + enemy.getSpeed() * Math.sin(angle));

                enemy.getImg().setRotate(Math.toDegrees(angle));
            }

        } catch (Exception e) {

        }
    }

    private void handleMovableTiles() {
        for (ImageView tile : movableTiles) {
            if (moveRight)
                tile.setX(tile.getX() - 5);
            if (moveLeft && movableTiles.get(0).getX() <= 0)
                tile.setX(tile.getX() + 5);
            if (moveDown)
                tile.setY(tile.getY() - 5);
            if (moveUp && movableTiles.get(0).getY() <= 0)
                tile.setY(tile.getY() + 5);
        }
    }
    private void handleMovableTexts() {
        for (Text text:movableTexts) {
            if (moveRight)
                text.setX(text.getX() - 5);
            if (moveLeft && movableTiles.get(0).getX() <= 0)
                text.setX(text.getX() + 5);
            if (moveDown)
                text.setY(text.getY() - 5);
            if (moveUp && movableTiles.get(0).getY() <= 0)
                text.setY(text.getY() + 5);
        }
    }

    private void emptyLists(){
        movableTiles.clear();
        enemies.clear();
        shots.clear();
        bloodTraces.clear();

        rootModifier.clear();
    }

    private void stopProcesses(){
        timer.stop();
        mediaPlayer.stop();
    }

    private void repaint() {

        for(Enemy enemy:enemies){
            rootModifier.remove(enemy.getImg());
            rootModifier.add(enemy.getImg());
        }

        rootModifier.remove(player.getImg());
        rootModifier.add(player.getImg());

        if (rootModifier.contains(craftingTable.getNotification().getText())) {
            rootModifier.remove(craftingTable.getNotification().getRectangle());
            rootModifier.remove(craftingTable.getNotification().getText());

            rootModifier.add(craftingTable.getNotification().getRectangle());
            rootModifier.add(craftingTable.getNotification().getText());
        }
    }


}
