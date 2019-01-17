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
    private Image craftingTable;
    private Image mysteriousChest;
    private Image firstAidKit;
    private Image ammoKit;
    private Image questionMark;
    private Image weapon1;
    private Image weapon2;
    private Image weapon3;


    public ImageContainer() {

        shell = new Image("apocalypse/media/images/ammo.png");
        blood = new Image("apocalypse/media/images/blood.png");
        player = new Image("apocalypse/media/images/NAA Infantry.png");
        grass = new Image("apocalypse/media/images/night grass.png");
        sand = new Image("apocalypse/media/images/night sand.png");
        water = new Image("apocalypse/media/images/night water.png");
        pavement = new Image("apocalypse/media/images/night pavement.png");
        zombie = new Image("apocalypse/media/images/zombiee.png");
        craftingTable = new Image("apocalypse/media/images/crafting table.png");
        mysteriousChest = new Image("apocalypse/media/images/mysterious chest.png");
        firstAidKit = new Image("apocalypse/media/images/first aid.png");
        ammoKit = new Image("apocalypse/media/images/ammo kit.png");
        questionMark = new Image("apocalypse/media/images/question mark.png");
        weapon1 = new Image("apocalypse/media/images/w1.png");
        weapon2 = new Image("apocalypse/media/images/w2.png");
        weapon3 = new Image("apocalypse/media/images/w3.png");

    }

    public Image getWeapon1() {
        return weapon1;
    }

    public void setWeapon1(Image weapon1) {
        this.weapon1 = weapon1;
    }

    public Image getWeapon2() {
        return weapon2;
    }

    public void setWeapon2(Image weapon2) {
        this.weapon2 = weapon2;
    }

    public Image getWeapon3() {
        return weapon3;
    }

    public void setWeapon3(Image weapon3) {
        this.weapon3 = weapon3;
    }

    public Image getQuestionMark() {
        return questionMark;
    }

    public void setQuestionMark(Image questionMark) {
        this.questionMark = questionMark;
    }

    public Image getAmmoKit() {
        return ammoKit;
    }

    public void setAmmoKit(Image ammoKit) {
        this.ammoKit = ammoKit;
    }

    public Image getFirstAidKit() {
        return firstAidKit;
    }

    public void setFirstAidKit(Image firstAidKit) {
        this.firstAidKit = firstAidKit;
    }

    public Image getMysteriousChest() {
        return mysteriousChest;
    }

    public void setMysteriousChest(Image mysteriousChest) {
        this.mysteriousChest = mysteriousChest;
    }

    public Image getCraftingTable() {
        return craftingTable;
    }

    public void setCraftingTable(Image craftingTable) {
        this.craftingTable = craftingTable;
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
