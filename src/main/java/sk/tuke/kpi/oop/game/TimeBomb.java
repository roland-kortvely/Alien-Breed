package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {

    private Animation inactiveAnimation;
    private Animation activeAnimation;
    private Animation explosionAnimation;

    private float time;

    private boolean activated;

    public TimeBomb(float time)
    {
        this.setInactiveAnimation(new Animation("sprites/bomb.png", 16, 16));
        this.setActiveAnimation(new Animation("sprites/bomb_activated.png", 16, 16, 0.02f, Animation.PlayMode.LOOP_PINGPONG));
        this.setExplosionAnimation(new Animation("sprites/small_explosion.png", 16, 16, 0.05f, Animation.PlayMode.ONCE));

        this.setTime(time);

        setAnimation(this.getInactiveAnimation());
    }

    public void activate()
    {
        setAnimation(this.getActiveAnimation());
        this.activated = true;

        new ActionSequence<>(new Wait(this.getTime()), new Invoke(this::detonate)).scheduleOn(this);
    }

    private void detonate()
    {

        setAnimation(this.getExplosionAnimation());

        new ActionSequence<>(
            new Invoke(() -> setAnimation(this.getExplosionAnimation())),
            new Wait(2.0f),
            new Invoke(() -> {
                Scene scene = this.getScene();
                if (scene != null) {
                    scene.removeActor(this);
                }
            })
        ).scheduleOn(this);
    }

    private float getTime()
    {
        return time;
    }

    private void setTime(float time)
    {
        this.time = time;
    }

    public boolean isActivated()
    {
        return activated;
    }

    private Animation getInactiveAnimation()
    {
        return inactiveAnimation;
    }

    private void setInactiveAnimation(Animation inactiveAnimation)
    {
        this.inactiveAnimation = inactiveAnimation;
    }

    private Animation getActiveAnimation()
    {
        return activeAnimation;
    }

    private void setActiveAnimation(Animation activeAnimation)
    {
        this.activeAnimation = activeAnimation;
    }

    private Animation getExplosionAnimation()
    {
        return explosionAnimation;
    }

    private void setExplosionAnimation(Animation explosionAnimation)
    {
        this.explosionAnimation = explosionAnimation;
    }
}
