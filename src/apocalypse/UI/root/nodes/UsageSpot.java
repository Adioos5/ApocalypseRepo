package apocalypse.UI.root.nodes;


import apocalypse.interfaces.Handler;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class UsageSpot {

    private ImageView img;
    private Text text;
    private double width;
    private double height;
    private double x;
    private double y;
    private Handler handler;
    private Notification notification;

    public UsageSpot(ImageView img, Text text, double width,double height, double x, double y,Notification notification, Handler handler) {

        this.img = img;
        this.text = text;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.handler = handler;
        this.notification = notification;

        img.setFitWidth(width);
        img.setFitHeight(height);
        img.setX(x);
        img.setY(y);

        text.setFont(Font.font("Monospaced",25));
        text.setFill(Color.WHITE);

        double textX = (img.getX()+img.getFitWidth()/2)-((text.getText().length()*15)/2);

        text.setX(textX);
        text.setY(y-10);
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
