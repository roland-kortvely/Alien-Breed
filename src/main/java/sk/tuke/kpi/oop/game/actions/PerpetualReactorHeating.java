/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

/**
 * The type Perpetual reactor heating.
 */
public class PerpetualReactorHeating extends AbstractAction<Reactor> {

    private int increment;

    /**
     * Instantiates a new Perpetual reactor heating.
     *
     * @param increment the increment
     */
    public PerpetualReactorHeating(int increment)
    {
        this.setIncrement(increment);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            return;
        }

        this.getActor().increaseTemperature(this.getIncrement());
    }

    @Contract(pure = true)
    private int getIncrement()
    {
        return increment;
    }

    private void setIncrement(int increment)
    {
        this.increment = increment;
    }
}
