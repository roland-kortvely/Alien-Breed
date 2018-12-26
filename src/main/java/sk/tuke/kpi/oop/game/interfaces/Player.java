/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.interfaces;

import sk.tuke.kpi.gamelib.Actor;

/**
 * The interface Player.
 */
public interface Player extends Actor, Alive, Movable, Armed, Keeper<Collectible> {
}
