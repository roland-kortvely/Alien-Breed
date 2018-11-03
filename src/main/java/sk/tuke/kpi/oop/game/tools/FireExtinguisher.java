package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> {

    public FireExtinguisher()
    {
        super(1);
        setAnimation(new Animation("sprites/extinguisher.png", 16, 16));
    }

    @Override
    public void useWith(Reactor actor)
    {
        super.useWith(actor);

        if (actor == null) {
            return;
        }

        /*
        if (actor.getDamage() < 100) {
            return;
        }
        */

        actor.extinguish();
    }
}
