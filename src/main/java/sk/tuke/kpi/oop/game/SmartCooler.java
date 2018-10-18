package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler {

    public SmartCooler(Reactor reactor)
    {
        super(reactor);
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        this.setDecrement(2);

        new Loop<>(new Invoke(this::smartCooler)).scheduleOn(this);
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
