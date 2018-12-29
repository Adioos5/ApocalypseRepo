package apocalypse.UI.root.nodes;

import javafx.scene.image.ImageView;

public class Player {

    private ImageView img;

    public Player(ImageView playerImg) {
        this.img = playerImg;
    }

    public ImageView getImg() {
        return img;
    }

    public void setrImg(ImageView img) {
        this.img = img;
    }
}
