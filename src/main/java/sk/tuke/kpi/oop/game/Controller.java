package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Controller extends AbstractActor {

    private Reactor reactor;

    public Controller(Reactor reactor)
    {
        this.reactor = reactor;
        setAnimation(new Animation("sprites/switch.png", 16, 16));
    }

    public void toggle()
    {
        if (this.reactor.isRunning()) {
            this.reactor.turnOff();
        } else {
            this.reactor.turnOn();
        }
    }
}
