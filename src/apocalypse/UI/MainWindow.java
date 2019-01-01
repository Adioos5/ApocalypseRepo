package apocalypse.UI;
import apocalypse.mechanics.HandlersSwitcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindow extends Application {

    private HandlersSwitcher handlersSwitcher;

    public void runApplication() {
        launch(MainWindow.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new Pane()));

        handlersSwitcher = new HandlersSwitcher(stage);
        handlersSwitcher.recogniseHandlerAndSwitch(1);

        initFrame(stage);

        stage.show();
    }

    private void initFrame(Stage stage){
        stage.setWidth(1300);
        stage.setHeight(700);
        stage.setTitle("Apocalypse");
        stage.setResizable(false);
    }

}
