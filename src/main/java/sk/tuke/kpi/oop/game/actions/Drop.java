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

/**
 * The type Drop.
 *
 * @param <A> the type parameter
 */
public class Drop<A extends Keeper<Collectible>> extends AbstractAction<A> {

    private A actor;

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

        try {
            ActorContainer<Collectible> container = this.getActor().getContainer();
            if (container == null) {
                this.setDone(true);
                return;
            }

            Collectible actor = container.peek();
            if (actor == null) {
                this.setDone(true);
                return;
            }

            scene.addActor(actor, this.getActor().getPosX(), this.getActor().getPosY());
            container.remove(actor);
        } catch (Exception ex) {
            scene.getGame().getOverlay().drawText(ex.getMessage(), 0, 0).showFor(2);
        }

        this.setDone(true);
    }

    @Nullable
    @Override
    public A getActor()
    {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable A actor)
    {
        this.actor = actor;
    }
}
