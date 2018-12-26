/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;

import sk.tuke.kpi.oop.game.interfaces.Repairable;

/**
 * Repair repairable object.
 */
public class Repair extends AbstractCommand<Repairable> {

    @Override
    protected boolean command(@NotNull Repairable actor, @NotNull Scene scene)
    {
        return actor.repair();
    }
}
