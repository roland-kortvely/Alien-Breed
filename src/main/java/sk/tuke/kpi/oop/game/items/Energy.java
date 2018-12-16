/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.commands.RefillHealth;

/**
 * The type Energy.
 */
public class Energy extends AbstractActor implements Usable<Alive> {

    /**
     * Instantiates a new Energy.
     */
    public Energy()
    {
        setAnimation(new Animation("sprites/energy.png", 16, 16));
    }

    @Override
    public void useWith(Alive actor)
    {
        if (!(new RefillHealth()).execute(actor)) {
            return;
        }

        Scene scene = this.getScene();
        if (scene != null) {
            scene.removeActor(this);
        }
    }

    @Override
    public Class<Alive> getUsingActorClass()
    {
        return Alive.class;
    }
}
