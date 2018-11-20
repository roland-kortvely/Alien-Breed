/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.items.Usable;

public class Use<A extends Actor> extends AbstractAction<A> {

    private A actor;

    private Usable<A> usable;

    private boolean done;

    public Use(Usable<A> usable)
    {
        this.setUsable(usable);
        this.setDone(false);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            return;
        }

        if (this.getUsable() == null) {
            return;
        }

        this.usable.useWith(this.getActor());
        this.setDone(true);
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull A actor)
    {
        this.setActor(actor);
        return super.scheduleOn(actor);
    }

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

    public Usable getUsable()
    {
        return usable;
    }

    public void setUsable(Usable usable)
    {
        this.usable = usable;
    }

    @Override
    public boolean isDone()
    {
        return this.done;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }
}
