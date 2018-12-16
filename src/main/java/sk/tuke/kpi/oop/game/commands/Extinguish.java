/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.Reactor;

/**
 * The type Extinguish.
 */
public class Extinguish implements Command<Reactor> {

    @Override
    public boolean execute(Reactor reactor)
    {
        if (reactor == null) {
            return false;
        }

        return reactor.extinguish();
    }
}
