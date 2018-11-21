/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

/**
 * Interface Openable
 */
public interface Openable extends Actor {

    /**
     * Open.
     */
    void open();

    /**
     * Close.
     */
    void close();

    /**
     * Is open boolean.
     *
     * @return the boolean
     */
    boolean isOpen();
}
