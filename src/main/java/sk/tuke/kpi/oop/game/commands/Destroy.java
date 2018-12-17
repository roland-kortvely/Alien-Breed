/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

/**
 * The type Destroy.
 */
public class Destroy implements Command<Actor> {

    @Override
    public boolean execute(Actor actor)
    {
        if (actor == null) {
            return false;
        }

        Scene scene = actor.getScene();
        if (scene == null) {
            return false;
        }

        scene.cancelActions(actor);
        scene.removeActor(actor);

        return true;
    }
}
