package apocalypse.UI.root.nodes;

import javafx.scene.image.ImageView;

public class Enemy {

    private double speed;
    private ImageView img;

    public Enemy(double speed, ImageView img) {
        this.speed = speed;
        this.img = img;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
