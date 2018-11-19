/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A> {

    private int remainingUses;

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

    public int getRemainingUses()
    {
        Scene scene = this.getScene();

        if (this.remainingUses <= 0 && scene != null) {
            scene.removeActor(this);
        }

        return remainingUses;
    }

    public void setRemainingUses(int remainingUses)
    {
        Scene scene = this.getScene();

        this.remainingUses = remainingUses;

        if (this.remainingUses <= 0 && scene != null) {
            scene.removeActor(this);
        }
    }
}
