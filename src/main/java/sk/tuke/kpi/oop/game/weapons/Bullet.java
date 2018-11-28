/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;

import java.util.Optional;

/**
 * The type Bullet.
 */
public class Bullet extends AbstractActor implements Fireable {

    /**
     * Instantiates a new Bullet.
     */
    public Bullet()
    {
        setAnimation(new Animation("sprites/bullet.png", 16, 16));
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        new When<>(
            action -> scene.getActors().stream()
                .filter(Alive.class::isInstance)
                .filter(Enemy.class::isInstance)
                .anyMatch(actor -> actor.intersects(this)),
            new Invoke<>(() -> {
                Optional<?> actor = scene.getActors().stream()
                    .filter(Alive.class::isInstance)
                    .filter(Enemy.class::isInstance)
                    .filter(a -> a.intersects(this))
                    .findFirst();

                if (!actor.isPresent()) {
                    return;
                }

                ((Alive) actor.get()).getHealth().drain(25);
                scene.removeActor(this);
            })
        ).scheduleOn(this);
    }

    @Override
    public int getSpeed()
    {
        return 4;
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
    public void collidedWithWall()
    {
        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }

        scene.removeActor(this);
    }
}
