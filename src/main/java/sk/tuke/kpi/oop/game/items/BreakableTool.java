/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

import sk.tuke.kpi.oop.game.commands.Destroy;
import sk.tuke.kpi.oop.game.interfaces.Collectible;
import sk.tuke.kpi.oop.game.interfaces.Item;

/**
 * The type Breakable tool.
 *
 * @param <A> the type parameter
 */
public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Item<A>, Collectible {

    private int remainingUses;

    /**
     * Instantiates a new Breakable tool.
     *
     * @param uses the uses
     */
    public BreakableTool(int uses)
    {
        this.setRemainingUses(uses);
    }

    @Override
    public void useWith(A actor)
    {
        if (this.getRemainingUses() <= 0) {
            return;
        }

        this.setRemainingUses(this.getRemainingUses() - 1);
    }

    /**
     * Gets remaining uses.
     *
     * @return the remaining uses
     */
    public int getRemainingUses()
    {
        if (this.remainingUses <= 0) {
            new Destroy().execute(this);
        }

        return remainingUses;
    }

    /**
     * Sets remaining uses.
     *
     * @param remainingUses the remaining uses
     */
    public void setRemainingUses(int remainingUses)
    {
        this.remainingUses = remainingUses;

        if (this.remainingUses <= 0) {
            new Destroy().execute(this);
        }
    }
}
