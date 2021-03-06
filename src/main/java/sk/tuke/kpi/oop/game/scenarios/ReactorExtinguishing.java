/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.*;

import sk.tuke.kpi.oop.game.behaviours.*;
import sk.tuke.kpi.oop.game.characters.*;
import sk.tuke.kpi.oop.game.commands.*;
import sk.tuke.kpi.oop.game.controllers.*;
import sk.tuke.kpi.oop.game.interfaces.Player;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.objects.*;
import sk.tuke.kpi.oop.game.openables.*;

import java.util.List;

/**
 * The type ReactorExtinguishing.
 */
public class ReactorExtinguishing implements SceneListener {

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
        scene.setActorRenderOrder(List.of(Alien.class, Player.class));

        //Player's reference
        Player player = scene.getFirstActorByType(Player.class);
        if (player == null) {
            return;
        }

        //Camera should follow player through the map
        scene.follow(player);

        //Keyboard controller
        MovableController movableController = new MovableController(player);
        Disposable disposableMovableController = scene.getInput().registerListener(movableController);

        //Collector controller
        CollectorController collectorController = new CollectorController(player);
        Disposable disposableCollectorController = scene.getInput().registerListener(collectorController);

        //Shooter Controller
        ShooterController shooterController = new ShooterController(player);
        Disposable disposableShooterController = scene.getInput().registerListener(shooterController);

        //Render player backpack
        scene.getGame().pushActorContainer(player.getContainer());

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
