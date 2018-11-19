/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Ripley> {

    private boolean used;


    public Locker()
    {
        setAnimation(new Animation("sprites/locker.png", 16, 16));
        this.setUsed(false);
    }

    @Override
    public void useWith(Ripley actor)
    {
        if (actor == null) {
            return;
        }

        if (this.isUsed()) {
            return;
        }

        Scene scene = actor.getScene();
        if (scene == null) {
            return;
        }

        this.setUsed(true);

        //TODO:: improve...
        scene.addActor(new Hammer(), actor.getPosX(), actor.getPosY());
    }

    public boolean isUsed()
    {
        return used;
    }

    public void setUsed(boolean used)
    {
        this.used = used;
    }

    @Override
    public Class<Ripley> getUsingActorClass()
    {
        return Ripley.class;
    }
}
