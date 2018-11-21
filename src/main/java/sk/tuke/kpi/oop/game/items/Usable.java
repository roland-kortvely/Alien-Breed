/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;

/**
 * Interface Usable
 *
 * @param <U> the type parameter
 */
public interface Usable<U extends Actor> extends Actor {

    /**
     * Use with.
     *
     * @param actor the actor
     */
    void useWith(U actor);

    /**
     * Gets using actor class.
     *
     * @return the using actor class
     */
    Class<U> getUsingActorClass();
}
