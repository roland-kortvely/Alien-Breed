/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.interfaces;

import sk.tuke.kpi.gamelib.Actor;

/**
 * The interface Executable.
 *
 * @param <T> the type parameter
 */
public interface Executable<T extends Actor> {

    /**
     * Execute boolean.
     *
     * @param actor the actor
     *
     * @return the boolean
     */
    boolean execute(T actor);
}
