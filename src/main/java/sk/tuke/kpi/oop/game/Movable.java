/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;

/**
 * Interface Movable
 */
public interface Movable extends Actor {

    /**
     * Gets speed.
     *
     * @return the speed
     */
    int getSpeed();

    /**
     * Started moving.
     *
     * @param direction the direction
     */
    default void startedMoving(Direction direction)
    {
    }

    /**
     * Stopped moving.
     */
    default void stoppedMoving()
    {
    }

    /**
     * Collided with wall.
     */
    default void collidedWithWall() {}
}
