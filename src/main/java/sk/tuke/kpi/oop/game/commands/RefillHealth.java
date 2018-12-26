/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;

import sk.tuke.kpi.oop.game.interfaces.Alive;

/**
 * Refill health of alive actor.
 */
public class RefillHealth extends AbstractCommand<Alive> {

    private int increment;

    /**
     * Instantiates a new Refill health.
     *
     * @param increment the increment
     */
    public RefillHealth(int increment)
    {
        this.increment = increment;
    }

    @Override
    protected boolean command(@NotNull Alive actor, @NotNull Scene scene)
    {
        if (actor.getHealth() == null) {
            return false;
        }

        if (actor.getHealth().isFull()) {
            return false;
        }

        actor.getHealth().refill(this.increment);

        return true;
    }
}
