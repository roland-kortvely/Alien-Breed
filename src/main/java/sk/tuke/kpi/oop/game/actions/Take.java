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

import java.util.Optional;

/**
 * The type Take.
 *
 * @param <A> the type parameter
 */
public class Take<A extends Actor> extends AbstractAction<A> {

    private Keeper<A> actor;

    private Class<A> takeableActorsClass;

    /**
     * Instantiates a new Take.
     *
     * @param takeableActorsClass the takeable actors class
     */
    public Take(Class<A> takeableActorsClass)
    {
        this.takeableActorsClass = takeableActorsClass;
    }

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

        Optional<?> q = scene.getActors().stream()
            .filter(actor -> this.takeableActorsClass.isInstance(actor))
            .filter(actor -> actor.intersects(this.actor))
            .findFirst();

        if (!q.isPresent()) {
            this.setDone(true);
            return;
        }

        A query = takeableActorsClass.cast(q.get());

        try {
            ActorContainer<A> container = this.actor.getContainer();
            if (container == null) {
                this.setDone(true);
                return;
            }

            container.add(query);
            scene.removeActor(query);
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
