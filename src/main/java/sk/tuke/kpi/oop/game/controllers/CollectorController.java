/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.controllers;

import net.java.games.input.Usage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Optional;

public class CollectorController<A extends Keeper> implements KeyboardListener {

    private A actor;

    public CollectorController(A actor)
    {
        this.setActor(actor);
    }

    @Override
    public void keyPressed(@NotNull Input.Key key)
    {
        if (this.getActor() == null) {
            return;
        }

        Scene scene = this.getActor().getScene();
        if (scene == null) {
            return;
        }

        switch (key) {
            case ENTER: //Move item from the floor to backpack
                new Take<>(Collectible.class).scheduleOn(this.getActor());
                break;
            case BACKSPACE: //Drop item from backpack
                new Drop<>().scheduleOn(this.getActor());
                break;
            case S: //Rotate items in the backpack
                new Shift<>().scheduleOn(this.getActor());
                break;
            case U: //Use item on the floor
                Optional query = scene.getActors().stream()
                    .filter(Usable.class::isInstance)
                    .filter(actor -> actor.intersects(this.getActor()))
                    .findFirst();

                if (!query.isPresent()) {
                    break;
                }

                new Use<>((Usable<?>) query.get()).scheduleOnIntersectingWith(this.getActor());
                break;
            case B: //Use item from backpack
                if (this.getActor().getContainer().getSize() <= 0) {
                    return;
                }

                if (!(this.getActor().getContainer().peek() instanceof Usable)) {
                    return;
                }

                new Use<>((Usable<?>) this.getActor().getContainer().peek()).scheduleOnIntersectingWith(this.getActor());
                break;
        }
    }

    @Contract(pure = true)
    private A getActor()
    {
        return actor;
    }

    private void setActor(A actor)
    {
        this.actor = actor;
    }
}
