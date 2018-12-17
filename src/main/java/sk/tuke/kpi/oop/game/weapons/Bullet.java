/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.commands.Destroy;
import sk.tuke.kpi.oop.game.commands.DrainHealth;
import sk.tuke.kpi.oop.game.items.Obstacle;

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

            //Search for any Alive actor which intersects bullet
            action -> scene.getActors().stream()
                .filter(Alive.class::isInstance)
                .filter(actor -> !(actor instanceof Obstacle))      //Ignore obstacles --> solved in collidedWithObstacle
                .anyMatch(this::intersects),

            //Search for any Alive actor which intersects bullet
            new Invoke<>(() -> scene.getActors().stream()
                .filter(Alive.class::isInstance)
                .filter(actor -> !(actor instanceof Obstacle))      //Ignore obstacles --> solved in collidedWithObstacle
                .filter(this::intersects)
                .findFirst()
                .ifPresent(
                    actor -> {

                        //Drain Health
                        new DrainHealth(25).execute((Alive) actor);

                        //Remove bullet
                        new Destroy().execute(this);
                    }
                ))
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
        this.collided();
    }

    @Override
    public void collidedWithObstacle(Obstacle obstacle)
    {
        if (obstacle instanceof Alive) {
            //Drain Health
            new DrainHealth(25).execute((Alive) obstacle);
        }

        this.collided();
    }

    private void collided()
    {
        new Destroy().execute(this);
    }
}
