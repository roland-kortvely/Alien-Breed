/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.DefectiveLight;

/**
 * The type Wrench.
 */
public class Wrench extends BreakableTool<DefectiveLight> implements Collectible {

    /**
     * Instantiates a new Wrench.
     */
    public Wrench()
    {
        super(2);
        setAnimation(new Animation("sprites/wrench.png", 16, 16));
    }

    @Override
    public void useWith(DefectiveLight actor)
    {
        if (this.getRemainingUses() <= 0) {
            return;
        }

        if (actor == null) {
            return;
        }

        if (!actor.repair()) {
            return;
        }

        this.setRemainingUses(this.getRemainingUses() - 1);
    }

    @Override
    public Class<DefectiveLight> getUsingActorClass()
    {
        return DefectiveLight.class;
    }
}
