/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Repairable;

/**
 * The type Hammer.
 */
public class Hammer extends BreakableTool<Repairable> implements Collectible {

    /**
     * Instantiates a new Hammer.
     */
    public Hammer()
    {
        super(1);
        setAnimation(new Animation("sprites/hammer.png", 16, 16));
    }

    @Override
    public void useWith(Repairable actor)
    {
        if (actor == null) {
            return;
        }

        if (!actor.repair()) {
            return;
        }

        super.useWith(actor);
    }

    @Override
    public Class<Repairable> getUsingActorClass()
    {
        return Repairable.class;
    }
}
