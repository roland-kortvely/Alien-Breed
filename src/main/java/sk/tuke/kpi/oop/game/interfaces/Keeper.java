/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.interfaces;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;

/**
 * Interface Keeper
 *
 * @param <A> the type parameter
 */
public interface Keeper<A extends Actor> extends Actor {

    /**
     * Gets container.
     *
     * @return the container
     */
    ActorContainer<A> getContainer();
}
