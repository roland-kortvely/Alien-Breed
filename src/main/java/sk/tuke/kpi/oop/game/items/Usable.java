/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;

/**
 * Interface Usable
 */
public interface Usable<U extends Actor> extends Actor {

    void useWith(U actor);

    Class<U> getUsingActorClass();
}
