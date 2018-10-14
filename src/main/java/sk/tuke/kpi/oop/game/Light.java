package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor {

    private Animation animationOn;
    private Animation animationOff;

    private boolean on;
    private boolean electricityFlow;

    public Light()
    {

        this.on = false;
        this.electricityFlow = false;

        this.animationOn = new Animation("sprites/light_on.png", 16, 16);
        this.animationOff = new Animation("sprites/light_off.png", 16, 16);

        this.updateAnimation();
    }

    private void updateAnimation()
    {
        if (this.on && this.electricityFlow) {
            setAnimation(this.animationOn);
            return;
        }

        setAnimation(this.animationOff);
    }

    public void toggle()
    {
        this.on = !this.on;
        this.updateAnimation();
    }

    public void setElectricityFlow(boolean electricityFlow)
    {
        this.electricityFlow = electricityFlow;
        this.updateAnimation();
    }
}
