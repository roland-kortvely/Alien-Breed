/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.game.items.Collectible;

/**
 * The interface Player.
 */
public interface Player extends Actor, Alive, Movable, Armed, Keeper<Collectible> {
}
