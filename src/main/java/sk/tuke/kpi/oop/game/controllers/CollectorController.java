/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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

/**
 * The type Collector controller.
 */
public class CollectorController implements KeyboardListener {

    private Keeper<Collectible> actor;

    /**
     * Instantiates a new Collector controller.
     *
     * @param actor the actor
     */
    public CollectorController(Keeper<Collectible> actor)
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
                keyENTER();
                break;
            case BACKSPACE: //Drop item from backpack
                keyBACKSPACE();
                break;
            case S: //Rotate items in the backpack
                keyS();
                break;
            case U: //Use item from the floor
                keyU(scene);
                break;
            case B: //Use item from backpack
                keyB();
                break;
            default:
                break;
        }
    }

    private void keyENTER()
    {
        new Take<>(Collectible.class).scheduleOn(this.getActor());
    }

    private void keyBACKSPACE()
    {
        new Drop<Collectible>().scheduleOn(this.getActor());
    }

    private void keyS()
    {
        new Shift().scheduleOn(this.getActor());
    }

    private void keyB()
    {
        if (this.getActor().getContainer().getSize() <= 0) {
            return;
        }

        if (!(this.getActor().getContainer().peek() instanceof Usable)) {
            return;
        }

        new Use<>((Usable<?>) this.getActor().getContainer().peek()).scheduleOnIntersectingWith(this.getActor());
    }

    private void keyU(@NotNull Scene scene)
    {
        Optional<?> query = scene.getActors().stream()
            .filter(Usable.class::isInstance)
            .filter(actor -> actor.intersects(this.getActor()))
            .findFirst();

        if (!query.isPresent()) {
            return;
        }

        new Use<>((Usable<?>) query.get()).scheduleOnIntersectingWith(this.getActor());
    }

    @Contract(pure = true)
    private Keeper<Collectible> getActor()
    {
        return actor;
    }

    private void setActor(Keeper<Collectible> actor)
    {
        this.actor = actor;
    }
}
