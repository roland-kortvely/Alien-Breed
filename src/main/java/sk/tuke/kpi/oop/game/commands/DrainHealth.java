/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.characters.Alive;

/**
 * The type Drain health.
 */
public class DrainHealth implements Command<Alive> {

    private int decrement;

    /**
     * Instantiates a new Drain health.
     *
     * @param decrement the decrement
     */
    public DrainHealth(int decrement)
    {
        this.decrement = decrement;
    }

    @Override
    public boolean execute(Alive alive)
    {
        if (alive == null) {
            return false;
        }

        alive.getHealth().drain(this.decrement);

        return true;
    }
}
