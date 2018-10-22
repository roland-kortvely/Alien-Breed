package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {

    public Teleport() {
        setAnimation(new Animation("sprites/lift.png", 48, 48));
    }
}
