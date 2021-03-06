/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.interfaces;

import sk.tuke.kpi.gamelib.Actor;

/**
 * Interface Repairable
 */
public interface Repairable extends Actor {

    /**
     * Repair boolean.
     *
     * @return the boolean
     */
    boolean repair();
}
