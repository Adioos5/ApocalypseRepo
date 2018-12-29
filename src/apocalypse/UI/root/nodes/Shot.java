package apocalypse.UI.root.nodes;

import javafx.scene.image.ImageView;


public class Shot {
    private ImageView shotRect;
    private double shotAngle;

    public Shot(ImageView shotRect, double shotAngle) {
        this.shotRect = shotRect;
        this.shotAngle = shotAngle;
    }

    public ImageView getShotRect() {
        return shotRect;
    }

    public void setShotRect(ImageView shotRect) {
        this.shotRect = shotRect;
    }

    public double getShotAngle() {
        return shotAngle;
    }

    public void setShotAngle(double shotAngle) {
        this.shotAngle = shotAngle;
    }


}
