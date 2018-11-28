/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Optional;

/**
 * The type Alien.
 */
public class Alien extends AbstractActor implements Alive, Enemy, Movable {

    private Health health;

    /**
     * Instantiates a new Alien.
     */
    public Alien()
    {
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();

        this.setHealth(new Health(100));

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
            .filter(actor -> !actor.getClass().isInstance(Enemy.class))
            .filter(actor -> actor.intersects(this))
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

    public void setHealth(Health health)
    {
        this.health = health;
    }
}