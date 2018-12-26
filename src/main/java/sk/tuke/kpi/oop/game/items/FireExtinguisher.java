/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.commands.Extinguish;
import sk.tuke.kpi.oop.game.interfaces.Flammable;

/**
 * The type Fire extinguisher.
 */
public class FireExtinguisher extends BreakableTool<Flammable> {

    /**
     * Instantiates a new Fire extinguisher.
     */
    public FireExtinguisher()
    {
        super(1);
        setAnimation(new Animation("sprites/extinguisher.png", 16, 16));
    }

    @Override
    public void useWith(Flammable flammable)
    {
        if (!(new Extinguish()).execute(flammable)) {
            return;
        }

        super.useWith(flammable);
    }

    @Override
    public Class<Flammable> getUsingActorClass()
    {
        return Flammable.class;
    }
}
