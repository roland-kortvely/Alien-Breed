/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;

import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Collectible;

/**
 * The interface Player.
 */
public interface Player extends Actor, Movable, Alive, Armed, Keeper<Collectible> {
}
