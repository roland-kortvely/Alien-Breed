/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.interfaces.Alive;
import sk.tuke.kpi.oop.game.commands.Destroy;
import sk.tuke.kpi.oop.game.commands.RefillHealth;
import sk.tuke.kpi.oop.game.interfaces.Item;

/**
 * The type Energy.
 */
public class Energy extends AbstractActor implements Item<Alive> {

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
        if (!(new RefillHealth(50)).execute(actor)) {
            return;
        }

        new Destroy().execute(this);
    }

    @Override
    public Class<Alive> getUsingActorClass()
    {
        return Alive.class;
    }
}
