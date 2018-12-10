/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.items.Usable;

/**
 * The type Use.
 *
 * @param <A> the type parameter
 */
public class Use<A extends Actor> extends AbstractAction<A> {

    private Usable<A> usable;

    /**
     * Instantiates a new Use.
     *
     * @param usable the usable
     */
    public Use(Usable<A> usable)
    {
        this.setUsable(usable);
        this.setDone(false);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            this.setDone(true);
            return;
        }

        if (this.getUsable() == null) {
            this.setDone(true);
            return;
        }

        this.usable.useWith(this.getActor());
        this.setDone(true);
    }

    /**
     * Schedule on intersecting with disposable.
     *
     * @param mediatingActor the mediating actor
     *
     * @return the disposable
     */
    public Disposable scheduleOnIntersectingWith(Actor mediatingActor)
    {
        Scene scene = mediatingActor.getScene();
        if (scene == null) {
            return null;
        }

        Class<A> usingActorClass = usable.getUsingActorClass();
        return scene.getActors().stream()
            .filter(mediatingActor::intersects)
            .filter(usingActorClass::isInstance)
            .map(usingActorClass::cast)
            .findFirst()
            .map(this::scheduleOn)
            .orElse(null);
    }

    /**
     * Gets usable.
     *
     * @return the usable
     */
    public Usable<A> getUsable()
    {
        return usable;
    }

    /**
     * Sets usable.
     *
     * @param usable the usable
     */
    public void setUsable(Usable<A> usable)
    {
        this.usable = usable;
    }
}
