package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {

    private Switchable device;

    public PowerSwitch()
    {
        this(null);
    }

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

    public Switchable getDevice()
    {
        return device;
    }

    private void setDevice(Switchable device)
    {
        this.device = device;
    }

    public void switchOn()
    {
        getAnimation().setTint(Color.WHITE);

        if (this.getDevice() != null) {
            this.getDevice().turnOn();
        }
    }

    public void switchOff()
    {
        getAnimation().setTint(Color.GRAY);

        if (this.getDevice() != null) {
            this.getDevice().turnOff();
        }
    }
}
