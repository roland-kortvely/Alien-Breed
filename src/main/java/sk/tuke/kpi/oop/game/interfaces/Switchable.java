/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.interfaces;

/**
 * Interface Switchable
 */
public interface Switchable {

    /**
     * Turn on.
     */
    void turnOn();

    /**
     * Turn off.
     */
    void turnOff();

    /**
     * Is on boolean.
     *
     * @return the boolean
     */
    boolean isOn();
}
