package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor> {

    public Hammer()
    {
        super(1);
        setAnimation(new Animation("sprites/hammer.png", 16, 16));
    }

    @Override
    public void useWith(Reactor actor)
    {
        if (actor == null) {
            return;
        }

        if (!actor.repair()) {
            return;
        }

        super.useWith(actor);
    }
}
