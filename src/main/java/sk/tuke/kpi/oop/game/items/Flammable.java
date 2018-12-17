/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;

/**
 * The interface Flammable.
 */
public interface Flammable extends Actor {


    /**
     * Extinguish boolean.
     *
     * @return the boolean
     */
    boolean extinguish();
}
