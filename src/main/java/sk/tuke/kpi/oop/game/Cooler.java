package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor {

    private Animation onAnimation;

    private Reactor reactor;

    private boolean on;

    public Cooler(Reactor reactor)
    {
        this.setOnAnimation(new Animation("sprites/fan.png", 32, 32, 0f, Animation.PlayMode.LOOP_PINGPONG));
        this.setReactor(reactor);
        this.turnOff();
    }

    private void coolReactor()
    {

        if (this.getReactor() == null) {
            return;
        }

        if (!this.isOn()) {
            return;
        }

        this.getReactor().decreaseTemperature(1);
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        new Loop<>(new Invoke(this::coolReactor)).scheduleOn(this);
    }

    private Reactor getReactor()
    {
        return reactor;
    }

    private void setReactor(Reactor reactor)
    {
        this.reactor = reactor;
    }

    public boolean isOn()
    {
        return on;
    }

    public void turnOn()
    {
        this.on = true;
        this.onAnimation.setFrameDuration(0.2f);
        setAnimation(this.onAnimation);
    }

    public void turnOff()
    {
        this.on = false;
        this.onAnimation.setFrameDuration(0);
        setAnimation(this.onAnimation);
    }

    private Animation getOnAnimation()
    {
        return onAnimation;
    }

    private void setOnAnimation(Animation onAnimation)
    {
        this.onAnimation = onAnimation;
        setAnimation(this.onAnimation);
    }
}
