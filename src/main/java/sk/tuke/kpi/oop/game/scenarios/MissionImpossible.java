/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;

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


/**
 * The type Mission impossible.
 */
public class MissionImpossible implements SceneListener {

    private boolean decreaseEnergyState = true;

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

    private void decreasePlayerEnergy(Ripley ripley)
    {
        if (!this.decreaseEnergyState) {
            return;
        }

        if (ripley.getHealth().getValue() <= 0) {
            return;
        }

        //Repeat this action, till player dies
        new ActionSequence<>(
            new Wait<>(0.2f),
            new Invoke<>(() -> ripley.getHealth().drain(1)),
            new Invoke<>(() -> decreasePlayerEnergy(ripley))
        ).scheduleOn(ripley);
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
        Disposable disposableMovableController = scene.getInput().registerListener(movableController);

        //Collector controller
        CollectorController<Ripley> collectorController = new CollectorController<>(ripley);
        Disposable disposableCollectorController = scene.getInput().registerListener(collectorController);

        //Render player backpack
        scene.getGame().pushActorContainer(ripley.getContainer());

        //Decrease player energy once door are open, stop decreasing once ventilator is fixed
        scene.getMessageBus().subscribeOnce(Door.DOOR_OPENED, door -> decreasePlayerEnergy(ripley));
        scene.getMessageBus().subscribeOnce(Ventilator.VENTILATOR_REPAIRED, ventilator -> decreaseEnergyState = false);

        //Discard controllers after player died
        scene.getMessageBus().subscribeOnce(Ripley.RIPLEY_DIED, action -> {
            disposableMovableController.dispose();
            disposableCollectorController.dispose();
        });
    }
}
