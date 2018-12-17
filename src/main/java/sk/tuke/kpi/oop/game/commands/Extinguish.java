/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;

import sk.tuke.kpi.oop.game.items.Flammable;

/**
 * The type Extinguish.
 */
public class Extinguish extends AbstractCommand<Flammable> {

    @Override
    protected boolean command(@NotNull Flammable actor, @NotNull Scene scene)
    {
        return actor.extinguish();
    }
}
