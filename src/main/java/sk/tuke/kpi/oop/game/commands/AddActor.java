/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

/**
 * The type Add actor.
 */
public class AddActor extends AbstractCommand<Actor> {

    private int x;
    private int y;

    private Actor object;

    /**
     * Instantiates a new Add actor.
     *
     * @param x the x
     * @param y the y
     */
    public AddActor(Actor object, int x, int y)
    {
        this.x = x;
        this.y = y;

        this.object = object;
    }

    @Override
    protected boolean command(@NotNull Actor actor, @NotNull Scene scene)
    {
        scene.addActor(this.object, this.x, this.y);
        return true;
    }
}
