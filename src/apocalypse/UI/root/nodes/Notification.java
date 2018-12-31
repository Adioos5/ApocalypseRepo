package apocalypse.UI.root.nodes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Notification {

    private Rectangle rectangle;
    private Text text;
    private List<String> lines = new ArrayList<>();

    public Notification(Rectangle rectangle, Text text){
        this.rectangle = rectangle;
        this.text = text;

        centerNodes();
        centerText();

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public void centerNodes(){
        rectangle.setX(50);
        rectangle.setY(50);

        text.setFont(Font.font("Monospaced",15));
        text.setFill(Color.WHITE);
        text.setX(rectangle.getX());
        text.setY(rectangle.getY()+15);
    }

    // Ale jazda XDD
    public void centerText(){
        String x = "";
        for (int i = 0;i<text.getText().length();i++){
            if(x.length()*10>rectangle.getWidth()){
                lines.add(x+"-");
                x="";
            }
            x+=text.getText().charAt(i);
        }
        if(lines.isEmpty()){
            text.setText(x);
        }else {
            String n = "";
            for (int i = 0; i < lines.size(); i++) {
                n += lines.get(i) + "\n";
            }
            n+=x;
            text.setText(n);
        }
    }
}
