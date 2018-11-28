/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;

import sk.tuke.kpi.oop.game.weapons.Firearm;

/**
 * The interface Armed.
 */
public interface Armed extends Actor {

    /**
     * Gets firearm.
     *
     * @return the firearm
     */
    Firearm getFirearm();

    /**
     * Sets firearm.
     *
     * @param weapon the weapon
     */
    void setFirearm(Firearm weapon);
}
