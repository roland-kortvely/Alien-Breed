/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

/**
 * The type Shift.
 *
 * @param <A> the type parameter
 */
public class Shift<A extends Keeper<Collectible>> extends AbstractAction<A> {

    private A actor;

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            this.setDone(true);
            return;
        }

        this.getActor().getContainer().shift();
    }

    @Nullable
    @Override
    public A getActor()
    {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable A actor)
    {
        this.actor = actor;
    }
}
