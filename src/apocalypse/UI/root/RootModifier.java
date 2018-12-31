package apocalypse.UI.root;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RootModifier {

    private Pane root;

    public RootModifier(Stage stage){
        root = (Pane) stage.getScene().getRoot();
    }

    public void add(Node element){
        root.getChildren().add(element);
    }

    public void remove(Node element){
        root.getChildren().remove(element);
    }

    public void clear(){
        root.getChildren().clear();
    }

    public boolean contains(Object o){
        return root.getChildren().contains(o);
    }

}
