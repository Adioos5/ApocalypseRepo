package apocalypse.UI.root.nodes;


import javafx.scene.image.ImageView;

public class Tile {

    private ImageView img;
    private double size;
    private double x;
    private double y;

    public Tile(ImageView img, double size, double x, double y) {
        this.img = img;
        this.size = size;
        this.x = x;
        this.y = y;

        img.setX(x);
        img.setY(y);
        img.setFitWidth(size);
        img.setFitHeight(size);
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
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
}
