/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.interfaces.Keeper;
import sk.tuke.kpi.oop.game.interfaces.Collectible;

/**
 * The type Shift.
 */
public class Shift extends AbstractAction<Keeper<Collectible>> {

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            this.setDone(true);
            return;
        }

        this.getActor().getContainer().shift();

        this.setDone(true);
    }
}
