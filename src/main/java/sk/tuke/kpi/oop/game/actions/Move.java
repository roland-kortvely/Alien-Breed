/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Action;

import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.Direction;

public class Move<M extends Movable> implements Action<M> {

    private M actor;

    private boolean firstCall;
    private boolean done;

    private Direction direction;

    private float duration;

    public Move(Direction direction)
    {
        this(direction, 0.0f);
    }

    public Move(Direction direction, float duration)
    {
        this.setFirstCall(true);
        this.setDone(false);
        this.setDirection(direction);
        this.setDuration(duration);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            this.stop();
            return;
        }

        Scene scene = this.getActor().getScene();
        if (scene == null) {
            this.stop();
            return;
        }

        //Decrease duration..
        this.setDuration(this.getDuration() - deltaTime);

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

        this.setActorNewPosition();

        //Check whether actor intersects with walls
        if (scene.getMap().intersectsWithWall(this.getActor())) {
            this.getActor().setPosition(position[0], position[1]);
            this.stop();
            return;
        }

        //Timeout -> stop()
        if (this.getDuration() <= 0.00001f) {
            this.stop();
        }
    }

    private void setActorNewPosition()
    {
        //Calc new position by direction
        switch (this.getDirection()) {
            case NORTH:
                this.getActor().setPosition(this.getActor().getPosX(), this.getActor().getPosY() + this.getActor().getSpeed());
                break;
            case EAST:
                this.getActor().setPosition(this.getActor().getPosX() + this.getActor().getSpeed(), this.getActor().getPosY());
                break;
            case SOUTH:
                this.getActor().setPosition(this.getActor().getPosX(), this.getActor().getPosY() - this.getActor().getSpeed());
                break;
            case WEST:
                this.getActor().setPosition(this.getActor().getPosX() - this.getActor().getSpeed(), this.getActor().getPosY());
                break;

            case NORTHEAST:
                this.getActor().setPosition(
                    this.getActor().getPosX() + this.getActor().getSpeed() / 2,
                    this.getActor().getPosY() + this.getActor().getSpeed() / 2
                );
                break;
            case NORTHWEST:
                this.getActor().setPosition(
                    this.getActor().getPosX() - this.getActor().getSpeed() / 2,
                    this.getActor().getPosY() + this.getActor().getSpeed() / 2
                );
                break;
            case SOUTHEAST:
                this.getActor().setPosition(
                    this.getActor().getPosX() + this.getActor().getSpeed() / 2,
                    this.getActor().getPosY() - this.getActor().getSpeed() / 2
                );
                break;
            case SOUTHWEST:
                this.getActor().setPosition(
                    this.getActor().getPosX() - this.getActor().getSpeed() / 2,
                    this.getActor().getPosY() - this.getActor().getSpeed() / 2
                );
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

    public void stop()
    {
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

    public boolean isFirstCall()
    {
        return firstCall;
    }

    public void setFirstCall(boolean firstCall)
    {
        this.firstCall = firstCall;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public float getDuration()
    {
        return duration;
    }

    public void setDuration(float duration)
    {
        this.duration = duration;
    }

    @Override
    public boolean isDone()
    {
        return this.done;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }
}
