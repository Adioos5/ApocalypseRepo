package apocalypse.UI.containers;

import javafx.scene.image.Image;

public class ImageContainer {

    private Image shell;
    private Image blood;
    private Image player;
    private Image grass;
    private Image sand;
    private Image water;
    private Image pavement;
    private Image zombie;

    public ImageContainer() {

        shell = new Image("apocalypse/media/images/ammo.png");
        blood = new Image("apocalypse/media/images/blood.png");
        player = new Image("apocalypse/media/images/NAA Infantry.png");
        grass = new Image("apocalypse/media/images/night grass.png");
        sand = new Image("apocalypse/media/images/night sand.png");
        water = new Image("apocalypse/media/images/night water.png");
        pavement = new Image("apocalypse/media/images/night pavement.png");
        zombie = new Image("apocalypse/media/images/zombiee.png");
    }

    public Image getShell() {
        return shell;
    }

    public void setShell(Image shell) {
        this.shell = shell;
    }

    public Image getBlood() {
        return blood;
    }

    public void setBlood(Image blood) {
        this.blood = blood;
    }

    public Image getPlayer() {
        return player;
    }

    public void setPlayer(Image player) {
        this.player = player;
    }

    public Image getGrass() {
        return grass;
    }

    public void setGrass(Image grass) {
        this.grass = grass;
    }

    public Image getSand() {
        return sand;
    }

    public void setSand(Image sand) {
        this.sand = sand;
    }

    public Image getWater() {
        return water;
    }

    public void setWater(Image water) {
        this.water = water;
    }

    public Image getPavement() {
        return pavement;
    }

    public void setPavement(Image pavement) {
        this.pavement = pavement;
    }

    public Image getZombie() {
        return zombie;
    }

    public void setZombie(Image zombie) {
        this.zombie = zombie;
    }
}
