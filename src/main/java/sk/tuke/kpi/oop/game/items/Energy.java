/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.characters.Ripley;

public class Energy extends AbstractActor implements Usable<Ripley> {

    public Energy()
    {
        setAnimation(new Animation("sprites/energy.png", 16, 16));
    }

    @Override
    public void useWith(Ripley actor)
    {
        if (actor == null) {
            return;
        }

        if (actor.getEnergy() >= 100) {
            return;
        }

        actor.setEnergy(100);

        Scene scene = this.getScene();

        if (scene != null) {
            scene.removeActor(this);
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass()
    {
        return Ripley.class;
    }
}
