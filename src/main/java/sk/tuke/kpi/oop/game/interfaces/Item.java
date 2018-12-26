/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.interfaces;

import sk.tuke.kpi.gamelib.Actor;

/**
 * The interface Item.
 *
 * @param <U> the type parameter
 */
public interface Item<U extends Actor> extends Actor, Usable<U> {
}
