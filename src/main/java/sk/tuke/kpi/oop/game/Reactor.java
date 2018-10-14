package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {

    private boolean running;

    private int temperature;
    private int damage;

    private Animation offAnimation;
    private Animation normalAnimation;
    private Animation overheatedAnimation;
    private Animation brokenAnimation;

    public Reactor()
    {
        this.setTemperature(0);
        this.setDamage(0);

        this.setOffAnimation(new Animation("sprites/reactor.png", 80, 80));
        this.setNormalAnimation(new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.setOverheatedAnimation(new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG));
        this.setBrokenAnimation(new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG));

        this.setRunning(false);
    }

    public void increaseTemperature(int increment)
    {
        if (!this.isRunning()) {
            return;
        }

        if (increment <= 0) {
            return;
        }

        if (this.getDamage() >= 100) {
            this.turnOff();
            return;
        }

        this.setTemperature(
            (int) (this.getTemperature() + increment *
                (
                    (this.getDamage() >= 33.0f && this.getDamage() <= 66.0f)
                        ? 1.5f
                        : (this.getDamage() >= 66.0f)
                        ? 2.0f
                        : 1.0f
                )
            )
        );

        if (this.getTemperature() <= 2000) {
            return;
        }

        this.updateAnimation();

        float damage = (100.0f / 4000.0f) * (this.getTemperature() - 2000);

        if (damage > this.getDamage()) {
            this.setDamage((int) damage);
        }
    }

    public void decreaseTemperature(int decrement)
    {
        if (!this.isRunning()) {
            return;
        }

        if (decrement <= 0) {
            return;
        }

        if (this.getDamage() >= 100) {
            return;
        }

        if (this.getDamage() >= 50) {
            decrement /= 2;
        }

        this.setTemperature(this.getTemperature() - decrement);

        this.updateAnimation();
    }

    public void turnOn()
    {
        this.setRunning(true);
    }

    public void turnOff()
    {
        this.setRunning(false);
    }

    private void updateAnimation()
    {
        if (this.getDamage() >= 100) {
            setAnimation(this.getBrokenAnimation());
            return;
        }

        if (!this.isRunning()) {
            setAnimation(this.getOffAnimation());
            return;
        }

        if (this.getTemperature() >= 4000) {
            setAnimation(this.getOverheatedAnimation());
            return;
        }

        setAnimation(this.getNormalAnimation());
    }

    public void repairWith(Hammer hammer)
    {
        if (hammer == null) {
            return;
        }

        if (this.getDamage() <= 0) {
            return;
        }

        hammer.use();

        this.setDamage(this.getDamage() - 50);
        this.decreaseTemperature(1000);
    }

    private int getTemperature()
    {
        return temperature;
    }

    private void setTemperature(int temperature)
    {
        this.temperature = (temperature <= 6000) ? temperature : 6000;
    }

    private int getDamage()
    {
        return damage;
    }

    private void setDamage(int damage)
    {
        if (damage <= 0) {
            this.damage = 0;
            return;
        }

        if (damage >= 100) {
            this.damage = 100;
            this.turnOff();
            return;
        }

        this.damage = damage;
    }

    private Animation getOffAnimation()
    {
        return offAnimation;
    }

    private void setOffAnimation(Animation offAnimation)
    {
        this.offAnimation = offAnimation;
    }

    private Animation getNormalAnimation()
    {
        return normalAnimation;
    }

    private void setNormalAnimation(Animation normalAnimation)
    {
        this.normalAnimation = normalAnimation;
    }

    private Animation getOverheatedAnimation()
    {
        this.overheatedAnimation.setFrameDuration(0.05f - ((0.03f / 100.0f) * this.getDamage()));
        return overheatedAnimation;
    }

    private void setOverheatedAnimation(Animation overheatedAnimation)
    {
        this.overheatedAnimation = overheatedAnimation;
    }

    private Animation getBrokenAnimation()
    {
        return brokenAnimation;
    }

    private void setBrokenAnimation(Animation brokenAnimation)
    {
        this.brokenAnimation = brokenAnimation;
    }

    public boolean isRunning()
    {
        return running;
    }

    private void setRunning(boolean running)
    {
        if (this.getDamage() >= 100) {
            return;
        }

        this.running = running;

        this.updateAnimation();
    }
}
