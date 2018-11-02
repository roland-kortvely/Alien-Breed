package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {

    private boolean on;

    public Computer()
    {
        this.setAnimation(new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG));

        this.setOn(false);
    }

    @Override
    public void setPowered(boolean powered)
    {
        this.setOn(powered);
    }

    public int add(int n1, int n2)
    {
        return (this.isOn()) ? n1 + n2 : 0;
    }

    public int sub(int n1, int n2)
    {
        return (this.isOn()) ? n1 - n2 : 0;
    }

    public float add(float n1, float n2)
    {
        return (this.isOn()) ? n1 + n2 : 0.0f;
    }

    public float sub(float n1, float n2)
    {
        return (this.isOn()) ? n1 - n2 : 0.0f;
    }

    @Contract(pure = true)
    private boolean isOn()
    {
        return on;
    }

    private void setOn(boolean on)
    {
        this.on = on;

        if (this.isOn()) {
            this.getAnimation().play();
        } else {
            this.getAnimation().pause();
        }
    }
}
