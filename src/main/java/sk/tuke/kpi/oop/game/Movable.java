/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;

/**
 * Interface Movable
 */
public interface Movable extends Actor {

    int getSpeed();

    default void startedMoving(Direction direction)
    {
    }

    default void stoppedMoving()
    {
    }
}
