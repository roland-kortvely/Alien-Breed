/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.objects.Repairable;
import sk.tuke.kpi.oop.game.commands.Repair;

/**
 * The type Hammer.
 */
public class Hammer extends BreakableTool<Repairable> {

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
       if (!(new Repair()).execute(actor)) {
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
