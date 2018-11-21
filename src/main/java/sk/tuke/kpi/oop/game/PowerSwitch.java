/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

/**
 * The type Power switch.
 */
public class PowerSwitch extends AbstractActor {

    private Switchable device;

    /**
     * Instantiates a new Power switch.
     */
    public PowerSwitch()
    {
        this(null);
    }

    /**
     * Instantiates a new Power switch.
     *
     * @param device the device
     */
    public PowerSwitch(Switchable device)
    {
        setAnimation(new Animation("sprites/switch.png", 16, 16));

        if (device == null) {
            return;
        }

        this.setDevice(device);

        if (!device.isOn()) {
            getAnimation().setTint(Color.GRAY);
        }
    }

    /**
     * Gets device.
     *
     * @return the device
     */
    public Switchable getDevice()
    {
        return device;
    }

    private void setDevice(Switchable device)
    {
        this.device = device;
    }

    /**
     * Switch on.
     */
    public void switchOn()
    {
        getAnimation().setTint(Color.WHITE);

        if (this.getDevice() != null) {
            this.getDevice().turnOn();
        }
    }

    /**
     * Switch off.
     */
    public void switchOff()
    {
        getAnimation().setTint(Color.GRAY);

        if (this.getDevice() != null) {
            this.getDevice().turnOff();
        }
    }
}
