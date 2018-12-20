/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.oop.game.characters.Alive;

/**
 * The interface Explosive.
 */
public interface Explosive extends Alive {

    /**
     * Explode.
     */
    void explode();
}
