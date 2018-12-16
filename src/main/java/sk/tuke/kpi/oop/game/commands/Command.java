/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.gamelib.Actor;

/**
 * The interface Command.
 *
 * @param <T> the type parameter
 */
public interface Command<T extends Actor> {

    /**
     * Execute boolean.
     *
     * @param actor the actor
     *
     * @return the boolean
     */
    boolean execute(T actor);
}
