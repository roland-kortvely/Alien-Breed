/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;

/**
 * The interface Lockable.
 */
public interface Lockable extends Actor {

    /**
     * Is locked boolean.
     *
     * @return the boolean
     */
    default boolean isLocked()
    {
        return false;
    }

    /**
     * Lock.
     *
     * @param actor the actor
     */
    default void lock(@NotNull Actor actor)
    {
    }

    /**
     * Unlock.
     *
     * @param actor the actor
     */
    default void unlock(@NotNull Actor actor)
    {
    }
}
