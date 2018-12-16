/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.Repairable;

/**
 * The type Repair.
 */
public class Repair implements Command<Repairable> {

    @Override
    public boolean execute(Repairable repairable)
    {
        if (repairable == null) {
            return false;
        }

        return repairable.repair();
    }
}
