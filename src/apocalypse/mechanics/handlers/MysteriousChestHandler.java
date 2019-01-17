package apocalypse.mechanics.handlers;

import apocalypse.UI.containers.ImageContainer;
import apocalypse.UI.root.RootModifier;
import apocalypse.UI.root.nodes.Button;
import apocalypse.UI.root.nodes.Notification;
import apocalypse.interfaces.Handler;
import apocalypse.mechanics.HandlersSwitcher;
import apocalypse.mechanics.threads.Sleeper;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class MysteriousChestHandler implements Handler {

    private RootModifier rootModifier;
    private ImageContainer imageContainer;
    private HandlersSwitcher handlersSwitcher;
    private ImageView randomedItemImg;
    private int type;
    private Button randomButton;
    private Notification notification;
    private List<Image> items = new ArrayList<>();

    public MysteriousChestHandler(RootModifier rootModifier, ImageContainer imageContainer, HandlersSwitcher handlersSwitcher,int type){
        this.rootModifier = rootModifier;
        this.imageContainer = imageContainer;
        this.handlersSwitcher = handlersSwitcher;
        this.type = type;

        items.add(imageContainer.getWeapon1());
        items.add(imageContainer.getWeapon2());
        items.add(imageContainer.getWeapon3());

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void mouseMoved(MouseEvent e){

        double mouseX = e.getX();
        double mouseY = e.getY();

        if (new Rectangle(mouseX,mouseY,1,1).getBoundsInParent().intersects(randomButton.getRectangle().getBoundsInParent())){
            randomButton.setRectangleColor(Color.GREEN);
        } else {
            randomButton.setRectangleColor(Color.BLUE);
        }

    }

    public void mouseClicked(MouseEvent e){
        double mouseX = e.getX();
        double mouseY = e.getY();

        if (new Rectangle(mouseX,mouseY,1,1).getBoundsInParent().intersects(randomButton.getRectangle().getBoundsInParent())){
            if(rootModifier.contains(notification.getRectangle())&& rootModifier.contains(notification.getRectangle())) {
                rootModifier.remove(notification.getRectangle());
                rootModifier.remove(notification.getText());
            }
            randomAnItem();
        }
    }

    private void randomAnItem(){

            new Thread(()-> {
                try {
                    int x = 0;
                    while(x!=5) {
                        for (Image item : items) {
                            Thread.currentThread().sleep(100);
                            randomedItemImg.setImage(item);
                        }
                        x+=1;
                    }
                    randomedItemImg.setImage(items.get((int) ((Math.random() * ((3 - 1) + 1)) + 0)));
                    Thread.currentThread().sleep(1000);
                    randomedItemImg.setImage(imageContainer.getQuestionMark());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
    }

    @Override
    public void launchHandler(){
        initRoot();
    }

    @Override
    public void disableHandler() {

    }

    private void initRoot(){
        //background
        Rectangle rect = new Rectangle(1300,700, Color.YELLOW);

        //text
        Text txt = new Text("Mysterious Chest");
        txt.setFill(Color.RED);
        txt.setX(430);
        txt.setY(100);
        txt.setFont(Font.font("Monospaced",50));

        notification = new Notification(new Rectangle(200,100,Color.BLACK), new Text("Mysterious chest is a spot where you  can random a really cool gadget! Just click the \"Random\" button to ensure on your own!"));
        randomButton = new Button(new Rectangle(560,500,200,100),Color.BLUE,new Text("Random"),Color.WHITE);
        randomedItemImg = new ImageView(imageContainer.getQuestionMark());

        randomedItemImg.setX(560);
        randomedItemImg.setY(220);
        randomedItemImg.setFitWidth(200);
        randomedItemImg.setFitHeight(200);

        rootModifier.add(rect);
        rootModifier.add(txt);
        rootModifier.add(notification.getRectangle());
        rootModifier.add(notification.getText());
        rootModifier.add(randomButton.getRectangle());
        rootModifier.add(randomButton.getText());
        rootModifier.add(randomedItemImg);
    }
}
