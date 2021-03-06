/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.objects;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

/**
 * The type Smart cooler.
 */
public class SmartCooler extends Cooler {

    /**
     * Instantiates a new Smart cooler.
     *
     * @param reactor the reactor
     */
    public SmartCooler(Reactor reactor)
    {
        super(reactor);
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        this.setDecrement(2);

        new Loop<>(new Invoke<>(this::smartCooler)).scheduleOn(this);
    }

    private void smartCooler()
    {
        if (this.getReactor() == null) {
            return;
        }

        if (this.isOn()) {
            if (this.getReactor().getTemperature() < 1500) {
                this.turnOff();
            }
        } else {
            if (this.getReactor().getTemperature() > 2500) {
                this.turnOn();
            }
        }
    }
}
