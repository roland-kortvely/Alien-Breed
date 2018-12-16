/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.characters.Ripley;

/**
 * The type Refill player health.
 */
public class RefillPlayerHealth implements CommandPlayer {

    @Override
    public boolean execute(Ripley ripley)
    {
        if (ripley == null) {
            return false;
        }

        ripley.getHealth().refill(100);

        return true;
    }
}
