/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.characters.Alive;

/**
 * Refill health of alive actor.
 */
public class RefillHealth implements Command<Alive> {

    @Override
    public boolean execute(Alive alive)
    {
        if (alive == null) {
            return false;
        }

        alive.getHealth().refill(100);

        return true;
    }
}
