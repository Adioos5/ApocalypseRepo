package apocalypse.mechanics;

import apocalypse.UI.containers.ImageContainer;
import apocalypse.UI.root.RootModifier;
import apocalypse.mechanics.handlers.GameLogicHandler;
import javafx.stage.Stage;

public class HandlersSwitcher {

    private Stage stage;
    private GameLogicHandler gameLogicHandler;
    private ImageContainer imageContainer;
    private RootModifier rootModifier;

    public HandlersSwitcher(Stage stage){
        this.stage = stage;
        initComponents();
        initHandlers();
    }

    public void switchOnGameHandler(){

        stage.getScene().setOnKeyPressed(e -> {
            gameLogicHandler.keyPressed(e);
        });
        stage.getScene().setOnKeyReleased(e -> {
            gameLogicHandler.keyReleased(e);
        });

        stage.getScene().setOnMouseMoved(e -> {
            gameLogicHandler.mouseMoved(e);

        });
        stage.getScene().setOnMouseClicked(e -> {
            gameLogicHandler.mouseClicked(e);
        });

        gameLogicHandler.launchHandler();
    }

    private void initComponents(){
        imageContainer = new ImageContainer();
        rootModifier = new RootModifier(stage);
    }
    private void initHandlers(){
        gameLogicHandler = new GameLogicHandler(rootModifier,imageContainer,this);
    }

}
