/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;

import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.CollectorController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.List;

public class MissionImpossible implements SceneListener {

    //Factory
    public static class Factory implements ActorFactory {

        @Nullable
        @Override
        public Actor create(@Nullable String type, @Nullable String name)
        {
            if (name == null) {
                return null;
            }

            //Instantiate actors from Map file
            switch (name) {
                case "ellen":
                    return new Ripley();
                case "energy":
                    return new Energy();
                case "door":
                    return new LockedDoor();
                case "locker":
                    return new Locker();
                case "ventilator":
                    return new Ventilator();
                case "access card":
                    return new AccessCard();
                default:
                    return null;
            }
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene)
    {
        //Player should be at top of the scene
        scene.setActorRenderOrder(List.of(Ripley.class));

        //Player's reference
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        if (ripley == null) {
            return;
        }

        //Camera should follow player through the map
        scene.follow(ripley);

        //Keyboard controller
        MovableController movableController = new MovableController<>(ripley);
        scene.getInput().registerListener(movableController);

        //Collector controller
        CollectorController<Ripley> collectorController = new CollectorController<>(ripley);
        scene.getInput().registerListener(collectorController);

        //Render player's backpack
        scene.getGame().pushActorContainer(ripley.getContainer());
    }
}
