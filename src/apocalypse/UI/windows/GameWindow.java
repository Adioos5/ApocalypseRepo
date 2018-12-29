package apocalypse.UI.windows;
import apocalypse.UI.containers.ImageContainer;
import apocalypse.UI.root.RootModifier;
import apocalypse.mechanics.handlers.GameLogicHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameWindow extends Application {

    private ImageContainer imageContainer;
    private RootModifier rootModifier;
    private GameLogicHandler logicHandler;

    public void runApplication() {
        launch(GameWindow.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new Pane()));
        createComponents(stage);

        stage.getScene().setOnKeyPressed(e -> {
            logicHandler.keyPressed(e);
        });
        stage.getScene().setOnKeyReleased(e -> {
            logicHandler.keyReleased(e);
        });

        stage.getScene().setOnMouseMoved(e -> {
            logicHandler.mouseMoved(e);

        });
        stage.getScene().setOnMouseClicked(e -> {
            logicHandler.mouseClicked(e);
        });

        stage.show();
    }

    private void createComponents(Stage stage){
        imageContainer = new ImageContainer();
        rootModifier = new RootModifier(stage);
        logicHandler = new GameLogicHandler(rootModifier,imageContainer);
        logicHandler.initRoot();

        stage.setWidth(1300);
        stage.setHeight(700);
        stage.setTitle("Apocalypse");
        stage.setResizable(false);
    }

}
