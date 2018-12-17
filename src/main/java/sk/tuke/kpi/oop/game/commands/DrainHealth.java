/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;

import sk.tuke.kpi.oop.game.characters.Alive;

/**
 * The type Drain health.
 */
public class DrainHealth extends AbstractCommand<Alive> {

    private int decrement;

    /**
     * Drain health from Alive actor.
     *
     * @param decrement the decrement
     */
    public DrainHealth(int decrement)
    {
        this.decrement = decrement;
    }

    @Override
    protected boolean command(@NotNull Alive actor, @NotNull Scene scene)
    {
        actor.getHealth().drain(this.decrement);
        return true;
    }
}
