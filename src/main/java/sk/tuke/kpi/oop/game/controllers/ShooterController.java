/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;

import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.interfaces.Armed;

/**
 * The type Shooter controller.
 */
public class ShooterController implements KeyboardListener {

    private Armed armed;

    /**
     * Instantiates a new Shooter controller.
     *
     * @param armed the armed
     */
    public ShooterController(Armed armed)
    {
        this.setArmed(armed);
    }

    @Override
    public void keyPressed(@NotNull Input.Key key)
    {
        if (this.getArmed() == null) {
            return;
        }

        if (key != Input.Key.SPACE) {
            return;
        }

        new Fire<>().scheduleOn(this.getArmed());
    }

    /**
     * Gets armed.
     *
     * @return the armed
     */
    public Armed getArmed()
    {
        return armed;
    }

    /**
     * Sets armed.
     *
     * @param armed the armed
     */
    public void setArmed(Armed armed)
    {
        this.armed = armed;
    }
}
