package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {

    public TimeBomb()
    {
        setAnimation(new Animation("sprites/bomb.png", 16, 16));
    }
}
