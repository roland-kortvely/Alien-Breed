/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.characters.Armed;

/**
 * Reload firearm of armed actor.
 */
public class ReloadFirearm implements Command<Armed> {

    private int increment;

    /**
     * Instantiates a new Reload firearm.
     *
     * @param increment the increment
     */
    public ReloadFirearm(int increment)
    {
        this.increment = increment;
    }

    @Override
    public boolean execute(Armed armed)
    {
        if (armed == null) {
            return false;
        }

        if (armed.getFirearm() == null) {
            return false;
        }

        if (armed.getFirearm().isFull()) {
            return false;
        }

        armed.getFirearm().reload(this.increment);

        return true;
    }
}
