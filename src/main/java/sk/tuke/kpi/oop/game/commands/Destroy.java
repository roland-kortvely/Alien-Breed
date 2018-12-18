/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

/**
 * Command to destroy object.
 */
public class Destroy extends AbstractCommand<Actor> {

    @Override
    protected boolean command(@NotNull Actor actor, @NotNull Scene scene)
    {
        scene.cancelActions(actor);
        scene.removeActor(actor);
        return true;
    }
}
