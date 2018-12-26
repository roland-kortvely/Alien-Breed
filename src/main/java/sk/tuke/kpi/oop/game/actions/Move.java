/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Action;

import sk.tuke.kpi.oop.game.Gameplay;
import sk.tuke.kpi.oop.game.characters.Movable;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.items.Obstacle;

/**
 * The type Move.
 *
 * @param <M> the type parameter
 */
public class Move<M extends Movable> implements Action<M> {

    private M actor;

    private boolean firstCall;
    private boolean done;

    private Direction direction;

    private float duration;
    private float total;

    /**
     * Instantiates a new Move.
     *
     * @param direction the direction
     */
    public Move(Direction direction)
    {
        this(direction, 0.0f);
    }

    /**
     * Instantiates a new Move.
     *
     * @param direction the direction
     * @param duration  the duration
     */
    public Move(Direction direction, float duration)
    {
        this.setFirstCall(true);
        this.setDone(false);
        this.setDirection(direction);
        this.setDuration(duration);
        this.setTotal(0.0f);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            return;
        }

        Scene scene = Gameplay.getScene();

        //Decrease duration..
        this.setTotal(this.getTotal() + deltaTime);

        //Timeout -> stop()
        if (this.getTotal() >= this.getDuration()) {
            this.stop();
        }

        //If it's first actors call, start animation
        if (this.isFirstCall()) {
            this.getActor().startedMoving(this.getDirection());
            this.setFirstCall(false);
        }

        //Save actor positions, in case of intersections with walls
        int[] position = new int[]{
            getActor().getPosX(),
            getActor().getPosY()
        };

        this.setActorNewPosition(this.getActor());

        //Check whether actor intersects with walls
        if (scene.getMap().intersectsWithWall(this.getActor())) {
            this.getActor().setPosition(position[0], position[1]);
            this.getActor().collidedWithWall();
            this.stop();
        }

        //Check whether actor intersects with obstacles
        if (this.getActor() != null) {
            scene.getActors().stream()
                .filter(Obstacle.class::isInstance)
                .filter(actor -> !(actor.equals(this.getActor())))
                .filter(this.getActor()::intersects)
                .findFirst()
                .ifPresent(obstacle -> {
                    this.getActor().setPosition(position[0], position[1]);
                    this.getActor().collidedWithObstacle((Obstacle) obstacle);
                    this.stop();
                });
        }
    }

    private void setActorNewPosition(@NotNull Movable actor)
    {
        //Calc new position by direction
        switch (this.getDirection()) {
            case NORTH:
                actor.setPosition(actor.getPosX(), actor.getPosY() + actor.getSpeed());
                break;
            case EAST:
                actor.setPosition(actor.getPosX() + actor.getSpeed(), actor.getPosY());
                break;
            case SOUTH:
                actor.setPosition(actor.getPosX(), actor.getPosY() - actor.getSpeed());
                break;
            case WEST:
                actor.setPosition(actor.getPosX() - actor.getSpeed(), actor.getPosY());
                break;

            case NORTHEAST:
                actor.setPosition(actor.getPosX() + actor.getSpeed(), actor.getPosY() + actor.getSpeed());
                break;
            case NORTHWEST:
                actor.setPosition(actor.getPosX() - actor.getSpeed(), actor.getPosY() + actor.getSpeed());
                break;
            case SOUTHEAST:
                actor.setPosition(actor.getPosX() + actor.getSpeed(), actor.getPosY() - actor.getSpeed());
                break;
            case SOUTHWEST:
                actor.setPosition(actor.getPosX() - actor.getSpeed(), actor.getPosY() - actor.getSpeed());
                break;

            default:
                break;
        }
    }

    @Override
    public void reset()
    {
        this.setFirstCall(true);
        this.setDone(false);
    }

    /**
     * Stop.
     */
    public void stop()
    {
        if (this.isDone()) {
            return;
        }

        if (this.getActor() == null) {
            return;
        }

        this.getActor().stoppedMoving();
        this.setDone(true);
    }

    @Nullable
    @Override
    public M getActor()
    {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable M actor)
    {
        this.actor = actor;
    }

    /**
     * Is first call boolean.
     *
     * @return the boolean
     */
    public boolean isFirstCall()
    {
        return firstCall;
    }

    /**
     * Sets first call.
     *
     * @param firstCall the first call
     */
    public void setFirstCall(boolean firstCall)
    {
        this.firstCall = firstCall;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Direction getDirection()
    {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public float getDuration()
    {
        return duration;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public float getTotal()
    {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(float total)
    {
        this.total = total;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(float duration)
    {
        this.duration = duration;
    }

    @Override
    public boolean isDone()
    {
        return this.done;
    }

    /**
     * Sets done.
     *
     * @param done the done
     */
    public void setDone(boolean done)
    {
        this.done = done;
    }
}
