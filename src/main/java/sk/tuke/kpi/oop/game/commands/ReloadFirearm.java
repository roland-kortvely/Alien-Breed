/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;

import sk.tuke.kpi.oop.game.interfaces.Armed;

/**
 * Reload firearm of armed actor.
 */
public class ReloadFirearm extends AbstractCommand<Armed> {

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
    protected boolean command(@NotNull Armed actor, @NotNull Scene scene)
    {
        if (actor.getFirearm() == null) {
            return false;
        }

        if (actor.getFirearm().isFull()) {
            return false;
        }

        actor.getFirearm().reload(this.increment);

        return true;
    }
}
