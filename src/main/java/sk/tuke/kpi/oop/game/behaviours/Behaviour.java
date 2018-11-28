/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Actor;

/**
 * The interface Behaviour.
 *
 * @param <A> the type parameter
 */
public interface Behaviour<A extends Actor> {

    /**
     * Sets up.
     */
    void setUp(A actor);
}
