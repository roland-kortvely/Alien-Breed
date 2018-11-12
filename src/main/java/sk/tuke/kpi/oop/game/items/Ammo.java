package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Ammo extends AbstractActor implements Usable<Ripley> {

    public Ammo()
    {
        setAnimation(new Animation("sprites/ammo.png", 16, 16));
    }

    @Override
    public void useWith(Ripley actor)
    {
        if (actor == null) {
            return;
        }

        if (actor.getAmmo() >= 500) {
            return;
        }

        actor.increaseAmmo(50);

        Scene scene = this.getScene();

        if (scene != null) {
            scene.removeActor(this);
        }
    }
}
