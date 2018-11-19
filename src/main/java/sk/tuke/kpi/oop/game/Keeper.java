/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;

/**
 * Interface Keeper
 */
public interface Keeper<A extends Actor> extends Actor {

    ActorContainer<A> getContainer();
}
