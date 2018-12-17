/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;

/**
 * The type Info.
 */
public class Info implements Command<Actor> {

    private String message;

    private int duration;

    /**
     * Instantiates a new Info.
     *
     * @param message  the message
     * @param duration the duration
     */
    public Info(String message, int duration)
    {
        this.message = message;
        this.duration = duration;
    }

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

        scene.getGame().getOverlay().drawText(this.message, 15, scene.getGame().getWindowSetup().getHeight() - (GameApplication.STATUS_LINE_OFFSET * 2)).showFor(this.duration);

        return true;
    }
}
