/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.Keeper;

/**
 * The type Drop.
 *
 * @param <A> the type parameter
 */
public class Drop<A extends Actor> extends AbstractAction<A> {

    private Keeper<A> actor;

    @Override
    public void execute(float deltaTime)
    {
        if (this.actor == null) {
            this.setDone(true);
            return;
        }

        Scene scene = this.actor.getScene();
        if (scene == null) {
            this.setDone(true);
            return;
        }

        System.out.println("h");

        try {
            ActorContainer<A> container = this.actor.getContainer();
            if (container == null) {
                this.setDone(true);
                return;
            }

            A actor = container.peek();
            if (actor == null) {
                this.setDone(true);
                return;
            }

            scene.addActor(actor, this.actor.getPosX(), this.actor.getPosY());
            container.remove(actor);
        } catch (Exception ex) {
            scene.getGame().getOverlay().drawText(ex.getMessage(), 0, 0).showFor(2);
        }

        this.setDone(true);
    }

    /**
     * Schedule on disposable.
     *
     * @param actor the actor
     *
     * @return the disposable
     */
    public Disposable scheduleOn(@NotNull Keeper<A> actor)
    {
        Scene scene = actor.getScene();
        if (scene == null) {
            return null;
        }

        this.actor = actor;

        return super.scheduleOn(scene);
    }
}
