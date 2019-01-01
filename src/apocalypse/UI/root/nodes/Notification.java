package apocalypse.UI.root.nodes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Notification {

    private Rectangle rectangle;
    private Text text;
    private List<String> lines = new ArrayList<>();
    private static final int lettersPixelsHeight = 13;
    private static final int lettersPixelsWidth = 10;

    public Notification(Rectangle rectangle, Text text){
        this.rectangle = rectangle;
        this.text = text;

        setBasicNotificaionOptions();
        adjustTextAndRectangle();

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

    public void setBasicNotificaionOptions(){
        rectangle.setX(50);
        rectangle.setY(50);

        text.setFont(Font.font("Monospaced",15));
        text.setFill(Color.WHITE);
        text.setX(rectangle.getX());
        text.setY(rectangle.getY()+15);
    }

    // Ale jazda XDD
    public void adjustTextAndRectangle(){

        //create lines and var x, which isn't a full line
        String x = "";
        for (int i = 0;i<text.getText().length();i++){
            if(x.length()*lettersPixelsWidth>rectangle.getWidth()){
                List<String> l = new LinkedList<>(Arrays.asList(x.split(" ")));
                String m = l.get(l.size()-1);
                l.remove(l.size()-1);
                String r = "";
                for(String n:l){
                    r+=n+" ";

                }
                r.substring(r.length()-2,r.length()-1);
                lines.add(r);
                x=m;
            }
            x+=text.getText().charAt(i);
        }
        //set text and adjust rectangle height depending on lines size in pixels
        if(lines.isEmpty()){
            text.setText(x);
            rectangle.setHeight(30+lettersPixelsHeight);

        } else {
            String n = "";
            for (int i = 0; i < lines.size(); i++) {
                n += lines.get(i) + "\n";
            }
            n+=x;
            text.setText(n);
            if(x!="")
                rectangle.setHeight(30+lines.size()*lettersPixelsHeight+lettersPixelsHeight);
            else
                rectangle.setHeight(30+lines.size()*lettersPixelsHeight);
        }
    }
}
