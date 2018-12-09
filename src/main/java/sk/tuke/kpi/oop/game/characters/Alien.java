/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.Direction;

import java.util.Optional;

/**
 * The type Alien.
 */
public class Alien extends AbstractActor implements Alive, Enemy, Movable {

    private Health health;

    private Behaviour<? super Alien> behaviour;

    /**
     * Instantiates a new Alien.
     */
    public Alien()
    {
        this(100, null);
    }

    /**
     * Instantiates a new Alien.
     *
     * @param behaviour the behaviour
     */
    public Alien(Behaviour<? super Alien> behaviour)
    {
        this(100, behaviour);
    }

    /**
     * Instantiates a new Alien.
     *
     * @param healthValue the health value
     * @param behaviour   the behaviour
     */
    public Alien(int healthValue, Behaviour<? super Alien> behaviour)
    {
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();

        this.setHealth(new Health(healthValue));
        this.setBehaviour(behaviour);

        this.getHealth().onExhaustion(this::die);
    }

    /**
     * Die.
     */
    public void die()
    {
        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }

        getAnimation().stop();

        scene.cancelActions(this);
        scene.removeActor(this);
    }


    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        this.deadly(scene);

        if (this.getBehaviour() != null) {
            this.getBehaviour().setUp(this);
        }
    }

    private void deadly(@NotNull Scene scene)
    {
        new When<>(
            action -> this.intersectsPlayer(scene).isPresent(),
            new Invoke<>(() -> {
                Optional<?> actor = this.intersectsPlayer(scene);
                if (!actor.isPresent()) {
                    return;
                }

                ((Alive) actor.get()).getHealth().drain(1);
                this.deadly(scene);
            })
        ).scheduleOn(this);
    }

    @NotNull
    private Optional<?> intersectsPlayer(@NotNull Scene scene)
    {
        return scene.getActors().stream()
            .filter(Alive.class::isInstance)
            .filter(this::intersects)

            .filter(actor -> !(actor instanceof Enemy))
            .filter(actor -> !actor.equals(this))

            .findFirst();
    }

    @Override
    public int getSpeed()
    {
        return 2;
    }

    @Override
    public void startedMoving(Direction direction)
    {
        //Rotate animation, and play it
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving()
    {
        getAnimation().stop();
    }

    @Override
    public Health getHealth()
    {
        return this.health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(Health health)
    {
        this.health = health;
    }

    @Contract(pure = true)
    private Behaviour<? super Alien> getBehaviour()
    {
        return behaviour;
    }

    private void setBehaviour(Behaviour<? super Alien> behaviour)
    {
        this.behaviour = behaviour;
    }
}
