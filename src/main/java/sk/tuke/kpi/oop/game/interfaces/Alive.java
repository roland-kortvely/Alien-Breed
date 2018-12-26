/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.interfaces;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.game.characters.Health;

/**
 * The interface Alive.
 */
public interface Alive extends Actor {

    /**
     * Gets health.
     *
     * @return the health
     */
    Health getHealth();
}
