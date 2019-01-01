package apocalypse.mechanics.handlers;

import apocalypse.UI.containers.ImageContainer;
import apocalypse.UI.root.RootModifier;
import apocalypse.interfaces.Handler;
import apocalypse.mechanics.HandlersSwitcher;
import javafx.scene.image.ImageView;

public class MysteriousChestHandler implements Handler {

    private RootModifier rootModifier;
    private ImageContainer imageContainer;
    private HandlersSwitcher handlersSwitcher;
    private int type;

    public MysteriousChestHandler(RootModifier rootModifier, ImageContainer imageContainer, HandlersSwitcher handlersSwitcher,int type){
        this.rootModifier = rootModifier;
        this.imageContainer = imageContainer;
        this.handlersSwitcher = handlersSwitcher;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void launchHandler() {
        rootModifier.add(new ImageView(imageContainer.getPavement()));
    }

    @Override
    public void disableHandler() {

    }
}
