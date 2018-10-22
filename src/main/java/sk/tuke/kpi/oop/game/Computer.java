package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {

    private int   number1;
    private float number2;

    private boolean on;

    public Computer()
    {
        this.setAnimation(new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG));

        this.setOn(false);

        this.number1 = 0;
        this.number2 = 0.0f;
    }

    @Override
    public void setPowered(boolean powered)
    {
        this.setOn(powered);
    }

    public void add(int n1, int n2)
    {
        this.number1 = n1 + n2;
    }

    public void sub(int n1, int n2)
    {
        this.number1 = n1 - n2;
    }

    public void add(float n1, float n2)
    {
        this.number2 = n1 + n2;
    }

    public void sub(float n1, float n2)
    {
        this.number2 = n1 - n2;
    }

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

            this.number1 = 0;
            this.number2 = 0.0f;
        }
    }
}
