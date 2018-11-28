/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.Keeper;

/**
 * The type Shift.
 */
public class Shift extends AbstractAction<Actor> {

    private Keeper<?> actor;

    @Override
    public void execute(float deltaTime)
    {
        if (this.actor == null) {
            this.setDone(true);
            return;
        }

        this.actor.getContainer().shift();

        this.setDone(true);
    }

    /**
     * Schedule on disposable.
     *
     * @param actor the actor
     *
     * @return the disposable
     */
    public Disposable scheduleOn(@NotNull Keeper<?> actor)
    {
        Scene scene = actor.getScene();
        if (scene == null) {
            return null;
        }

        this.actor = actor;

        return super.scheduleOn(scene);
    }
}
