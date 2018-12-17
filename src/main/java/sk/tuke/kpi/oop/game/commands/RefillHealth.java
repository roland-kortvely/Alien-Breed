/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.characters.Alive;

/**
 * Refill health of alive actor.
 */
public class RefillHealth implements Command<Alive> {

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
    public boolean execute(Alive alive)
    {
        if (alive == null) {
            return false;
        }

        if (alive.getHealth().isFull()) {
            return false;
        }

        alive.getHealth().refill(this.increment);

        return true;
    }
}
