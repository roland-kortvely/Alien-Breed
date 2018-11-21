/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Optional;

/**
 * The type Take.
 *
 * @param <K> the type parameter
 * @param <C> the type parameter
 */
public class Take<K extends Keeper<Collectible>, C extends Collectible> extends AbstractAction<K> {

    private K actor;

    private Class<C> takeableActorsClass;

    /**
     * Instantiates a new Take.
     *
     * @param takeableActorsClass the takeable actors class
     */
    public Take(Class<C> takeableActorsClass)
    {
        this.takeableActorsClass = takeableActorsClass;
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            this.setDone(true);
            return;
        }

        Scene scene = this.getActor().getScene();
        if (scene == null) {
            this.setDone(true);
            return;
        }

        Optional<Actor> query = scene.getActors().stream()
            .filter(actor -> this.takeableActorsClass.isInstance(actor))
            .filter(actor -> actor.intersects(this.getActor()))
            .findFirst();

        if (!query.isPresent()) {
            this.setDone(true);
            return;
        }

        try {
            ActorContainer<Collectible> container = this.actor.getContainer();
            if (container == null) {
                this.setDone(true);
                return;
            }

            container.add((Collectible) query.get());
            scene.removeActor(query.get());
        } catch (Exception ex) {
            scene.getGame().getOverlay().drawText(ex.getMessage(), 0, 0).showFor(2);
        }

        this.setDone(true);
    }

    @Nullable
    @Override
    public K getActor()
    {
        return actor;
    }

    @Override
    public void setActor(K actor)
    {
        this.actor = actor;
    }
}
