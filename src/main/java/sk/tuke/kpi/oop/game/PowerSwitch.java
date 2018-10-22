package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {

    private Switchable device;

    private boolean on;

    public PowerSwitch(Switchable device)
    {
        setAnimation(new Animation("sprites/switch.png", 16, 16));
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
        this.getDevice().turnOn();
        getAnimation().setTint(Color.WHITE);
    }

    public void switchOff()
    {
        this.getDevice().turnOff();
        getAnimation().setTint(Color.GRAY);
    }
}
