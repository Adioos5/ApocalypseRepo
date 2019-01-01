package apocalypse.mechanics;

import apocalypse.UI.containers.ImageContainer;
import apocalypse.UI.root.RootModifier;
import apocalypse.mechanics.handlers.AmmoKitHandler;
import apocalypse.mechanics.handlers.FirstAidKitHandler;
import apocalypse.mechanics.handlers.GameLogicHandler;
import apocalypse.mechanics.handlers.MysteriousChestHandler;
import javafx.stage.Stage;

public class HandlersSwitcher {

    private Stage stage;
    private GameLogicHandler gameLogicHandler;
    private MysteriousChestHandler mysteriousChestHandler;
    private FirstAidKitHandler firstAidKitHandler;
    private AmmoKitHandler ammoKitHandler;
    private ImageContainer imageContainer;
    private RootModifier rootModifier;

    public HandlersSwitcher(Stage stage){
        this.stage = stage;
        initComponents();
        initHandlers();
    }

    public AmmoKitHandler getAmmoKitHandler() {
        return ammoKitHandler;
    }

    public void setAmmoKitHandler(AmmoKitHandler ammoKitHandler) {
        this.ammoKitHandler = ammoKitHandler;
    }

    public FirstAidKitHandler getFirstAidKitHandler() {
        return firstAidKitHandler;
    }

    public void setFirstAidKitHandler(FirstAidKitHandler firstAidKitHandler) {
        this.firstAidKitHandler = firstAidKitHandler;
    }

    public GameLogicHandler getGameLogicHandler() {
        return gameLogicHandler;
    }

    public void setGameLogicHandler(GameLogicHandler gameLogicHandler) {
        this.gameLogicHandler = gameLogicHandler;
    }

    public MysteriousChestHandler getMysteriousChestHandler() {
        return mysteriousChestHandler;
    }

    public void setMysteriousChestHandler(MysteriousChestHandler mysteriousChestHandler) {
        this.mysteriousChestHandler = mysteriousChestHandler;
    }

    public void recogniseHandlerAndSwitch(int type){
       switch(type){
           case 1:
               switchOnGameHandler();
               break;
           case 2:
               switchOnMysteriousChestHandler();
               break;
               default:
       }
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

    public void switchOnMysteriousChestHandler(){

        stage.getScene().setOnMouseMoved(e -> {
            gameLogicHandler.mouseMoved(e);

        });
        stage.getScene().setOnMouseClicked(e -> {
            gameLogicHandler.mouseClicked(e);
        });

        mysteriousChestHandler.launchHandler();
    }

    private void initComponents(){
        imageContainer = new ImageContainer();
        rootModifier = new RootModifier(stage);
    }
    private void initHandlers(){
        gameLogicHandler = new GameLogicHandler(rootModifier,imageContainer,this,1);
        mysteriousChestHandler = new MysteriousChestHandler(rootModifier,imageContainer,this,2);
        firstAidKitHandler = new FirstAidKitHandler(rootModifier,imageContainer,this,3);
        ammoKitHandler = new AmmoKitHandler(rootModifier,imageContainer,this,4);
    }

}
