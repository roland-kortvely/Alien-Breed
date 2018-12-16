/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.characters.Ripley;

/**
 * The type Reload player firearm.
 */
public class ReloadPlayerFirearm implements CommandPlayer {

    @Override
    public boolean execute(Ripley ripley)
    {
        if (ripley == null) {
            return false;
        }

        if (ripley.getFirearm() == null) {
            return false;
        }

        ripley.getFirearm().reload(50);

        return true;
    }
}
