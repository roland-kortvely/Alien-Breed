/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

/**
 * Interface Openable
 */
public interface Openable extends Actor {

    void open();

    void close();

    boolean isOpen();
}
