/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;

import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.*;
import sk.tuke.kpi.oop.game.commands.Message;
import sk.tuke.kpi.oop.game.controllers.CollectorController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.items.FireExtinguisher;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Wrench;
import sk.tuke.kpi.oop.game.objects.Barrel;
import sk.tuke.kpi.oop.game.objects.Computer;
import sk.tuke.kpi.oop.game.objects.Corpse;
import sk.tuke.kpi.oop.game.objects.Locker;
import sk.tuke.kpi.oop.game.objects.Reactor;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

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
                    return new Alien(100, new RandomlyMoving());
                case "alien mother":
                    return new AlienMother();
                case "alien egg":
                    return new AlienEgg(new Alien(new RandomlyMoving()));
                case "spitter":
                    return new AlienSpitter(100, new RandomlyMoving());
                case "locked door":
                    return new LockedDoor("front door", (type.equals("vertical")) ? Door.Orientation.VERTICAL : Door.Orientation.HORIZONTAL);
                case "door":
                    return new Door("back door", (type.equals("vertical")) ? Door.Orientation.VERTICAL : Door.Orientation.HORIZONTAL);
                case "hammer":
                    return new Hammer();
                case "wrench":
                    return new Wrench();
                case "card":
                    return new AccessCard();
                case "fire extinguisher":
                    return new FireExtinguisher();
                case "barrel":
                    return new Barrel();
                case "access card":
                    return new AccessCard();
                case "reactor":
                    Reactor reactor = new Reactor();
                    reactor.setDamage(100);
                    return reactor;
                case "corpse":
                    return new Corpse();
                case "extinguisher":
                    return new FireExtinguisher();
                case "computer":
                    return new Computer();
                case "locker":
                    switch (type) {
                        case "ammo":
                            return new Locker.LockerBuilder(new Ammo(), Locker.Orientation.DOWN).build();
                        case "extinguisher":
                            return new Locker.LockerBuilder(new FireExtinguisher(), Locker.Orientation.DOWN).build();
                        case "hammer":
                            return new Locker.LockerBuilder(new Hammer(), Locker.Orientation.DOWN)
                                .setLocked()
                                .build();
                        default:
                            return null;
                    }
                default:
                    return null;
            }
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene)
    {
        //Player should be at the top of the scene
        scene.setActorRenderOrder(List.of(Ripley.class, Alien.class));

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

        //Finish the game
        scene.getMessageBus().subscribeOnce(Reactor.REACTOR_EXTINGUISHED, action -> new Message("You won!", 10).execute(action));
    }
}
