package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;
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

        this.setNumber1(0);
        this.setNumber2(0.0f);
    }

    @Override
    public void setPowered(boolean powered)
    {
        this.setOn(powered);
    }

    public void add(int n1, int n2)
    {
        this.setNumber1(n1 + n2);
    }

    public void sub(int n1, int n2)
    {
        this.setNumber1(n1 - n2);
    }

    public void add(float n1, float n2)
    {
        this.setNumber2(n1 + n2);
    }

    public void sub(float n1, float n2)
    {
        this.setNumber2(n1 - n2);
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

            this.setNumber1(0);
            this.setNumber2(0.0f);
        }
    }

    public int getNumber1()
    {
        return number1;
    }

    public void setNumber1(int number1)
    {
        this.number1 = number1;
    }

    public float getNumber2()
    {
        return number2;
    }

    public void setNumber2(float number2)
    {
        this.number2 = number2;
    }
}
