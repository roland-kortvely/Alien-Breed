/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.items.Flammable;

/**
 * The type Extinguish.
 */
public class Extinguish implements Command<Flammable> {

    @Override
    public boolean execute(Flammable flammable)
    {
        if (flammable == null) {
            return false;
        }

        return flammable.extinguish();
    }
}
