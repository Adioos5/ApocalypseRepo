package apocalypse.UI.root.nodes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Button {

    private Rectangle rectangle;
    private Color rectangleColor;
    private Text text;
    private Color textColor;

    public Button(Rectangle rectangle, Color rectangleColor, Text text, Color textColor) {
        this.rectangle = rectangle;
        this.rectangleColor = rectangleColor;
        this.text = text;
        this.textColor = textColor;

        rectangle.setFill(rectangleColor);
        text.setFill(textColor);

        double textX = (rectangle.getX()+rectangle.getWidth()/2)-((text.getText().length()*15)/2);

        text.setFont(Font.font("Monospaced",25));
        text.setX(textX);
        text.setY(rectangle.getY()+(rectangle.getHeight()/2));
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Color getRectangleColor() {
        return rectangleColor;
    }

    public void setRectangleColor(Color rectangleColor) {
        rectangle.setFill(rectangleColor);
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        text.setFill(textColor);
    }
}
