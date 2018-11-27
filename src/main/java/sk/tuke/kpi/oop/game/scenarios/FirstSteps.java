/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;

import sk.tuke.kpi.oop.game.controllers.CollectorController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.*;

import java.util.List;

/**
 * The type First steps.
 */
public class FirstSteps implements SceneListener {

    private Ripley ripley;

    @Override
    public void sceneInitialized(@NotNull Scene scene)
    {
        scene.setActorRenderOrder(List.of(Ripley.class));

        //Prepare player
        this.setRipley(new Ripley());
        scene.addActor(this.getRipley(), 0, 0);

        //Keyboard controller
        MovableController movableController = new MovableController(this.getRipley());
        scene.getInput().registerListener(movableController);

        //Collector controller
        CollectorController collectorController = new CollectorController(this.getRipley());
        scene.getInput().registerListener(collectorController);

        //Energy
        Energy energy = new Energy();
        scene.addActor(energy, 50, 50);
        /*
        new When<>(
            (action) -> energy.intersects(this.getRipley()),
            new Invoke<>(() -> new Use<>(energy).scheduleOn(this.getRipley()))
        ).scheduleOn(this.getRipley());
        */

        //Ammo
        Ammo ammo = new Ammo();
        scene.addActor(ammo, -50, 50);
        /*
        new When<>(
            (action) -> ammo.intersects(this.getRipley()),
            new Invoke<>(() -> new Use<>(ammo).scheduleOn(this.getRipley()))
        ).scheduleOn(this.getRipley());
        */

        //Items
        scene.addActor(new FireExtinguisher(), 50, -50);
        scene.addActor(new Wrench(), -50, -50);

        //Render player's backpack
        scene.getGame().pushActorContainer(this.getRipley().getContainer());
    }

    /**
     * Gets ripley.
     *
     * @return the ripley
     */
    public Ripley getRipley()
    {
        return ripley;
    }

    /**
     * Sets ripley.
     *
     * @param ripley the ripley
     */
    public void setRipley(Ripley ripley)
    {
        this.ripley = ripley;
    }
}
