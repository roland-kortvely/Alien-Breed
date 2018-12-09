/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.*;

import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.CollectorController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;

import java.util.List;

/**
 * The type Problemset.
 */
public class Problemset implements SceneListener {

    /**
     * The type Factory.
     */
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
                case "ripley":
                    return new Ripley();
                case "energy":
                    return new Energy();
                case "ammo":
                    return new Ammo();
                case "alien":
                    //
                    //                    if (type.equals("running")) {
                    //                        return new Alien(new Observing<>(
                    //                            World.ACTOR_REMOVED_TOPIC,
                    //                            Ammo.class::isInstance,
                    //                            new RandomlyMoving()
                    //                        ));
                    //                    }

                    return new Alien(100, new RandomlyMoving());
                case "alien mother":
                    return new AlienMother();
                case "front door":
                    return new Door("front door", Door.Orientation.VERTICAL);
                case "back door":
                    return new Door("back door", Door.Orientation.HORIZONTAL);
                case "exit door":
                    return new Door("exit door", Door.Orientation.VERTICAL);
                default:
                    return null;
            }
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene)
    {
        //Player should be at the top of the scene
        scene.setActorRenderOrder(List.of(Ripley.class));

        //Player's reference
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        if (ripley == null) {
            return;
        }

        //Camera should follow player through the map
        scene.follow(ripley);

        //Keyboard controller
        MovableController movableController = new MovableController(ripley);
        Disposable disposableMovableController = scene.getInput().registerListener(movableController);

        //Collector controller
        CollectorController collectorController = new CollectorController(ripley);
        Disposable disposableCollectorController = scene.getInput().registerListener(collectorController);

        //Shooter Controller
        ShooterController shooterController = new ShooterController(ripley);
        Disposable disposableShooterController = scene.getInput().registerListener(shooterController);

        //Render player backpack
        scene.getGame().pushActorContainer(ripley.getContainer());

        //Discard controllers once player is dead
        scene.getMessageBus().subscribeOnce(Ripley.RIPLEY_DIED, action -> {
            disposableMovableController.dispose();
            disposableCollectorController.dispose();
            disposableShooterController.dispose();
        });
    }
}
