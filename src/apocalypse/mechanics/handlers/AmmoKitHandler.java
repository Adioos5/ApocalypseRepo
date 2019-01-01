package apocalypse.mechanics.handlers;

import apocalypse.UI.containers.ImageContainer;
import apocalypse.UI.root.RootModifier;
import apocalypse.interfaces.Handler;
import apocalypse.mechanics.HandlersSwitcher;

public class AmmoKitHandler implements Handler {

    private RootModifier rootModifier;
    private ImageContainer imageContainer;
    private HandlersSwitcher handlersSwitcher;
    private int type;

    public AmmoKitHandler(RootModifier rootModifier, ImageContainer imageContainer, HandlersSwitcher handlersSwitcher,int type){
        this.rootModifier = rootModifier;
        this.imageContainer = imageContainer;
        this.handlersSwitcher = handlersSwitcher;
        this.type = type;
    }

    @Override
    public void launchHandler() {

    }

    @Override
    public void disableHandler() {

    }

    @Override
    public int getType() {
        return 0;
    }
}
