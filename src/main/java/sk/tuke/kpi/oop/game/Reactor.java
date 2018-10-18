package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;

public class Reactor extends AbstractActor {

    private boolean running;
    private boolean extinguished;

    private int temperature;
    private int damage;

    private Animation offAnimation;
    private Animation normalAnimation;
    private Animation overheatedAnimation;
    private Animation brokenAnimation;
    private Animation extinguishedAnimation;

    private Light light;

    public Reactor()
    {
        this.setTemperature(0);
        this.setDamage(0);

        this.setOffAnimation(new Animation("sprites/reactor.png", 80, 80));
        this.setNormalAnimation(new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.setOverheatedAnimation(new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG));
        this.setBrokenAnimation(new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.setExtinguishedAnimation(new Animation("sprites/reactor_extinguished.png", 80, 80));

        this.setExtinguished(false);
        this.setRunning(false);
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        //Perpetual Heating ---> BOOM
        new PerpetualReactorHeating(1).scheduleOn(this);
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

        this.setTemperature((int) (this.getTemperature() + increment * ((this.getDamage() >= 33.0f && this.getDamage() <= 66.0f) ? 1.5f : (this.getDamage() >= 66.0f) ? 2.0f : 1.0f)));

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
        if (this.isExtinguished()) {
            setAnimation(this.getExtinguishedAnimation());
            return;
        }

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

    public void repairWith(Hammer tool)
    {
        if (tool == null) {
            return;
        }

        if (this.getDamage() <= 0 || this.getDamage() >= 100) {
            return;
        }

        tool.use();

        float temperature = ((100.0f / this.getDamage()) * (this.getDamage() - 50)) / 100.0f;
        temperature *= this.getTemperature();

        this.setDamage(this.getDamage() - 50);

        if (this.getTemperature() > temperature) {
            this.setTemperature((int) temperature);
        }

        this.updateAnimation();
    }

    public void extinguishWith(FireExtinguisher extinguisher)
    {
        if (extinguisher == null) {
            return;
        }

        if (this.getDamage() < 100) {
            return;
        }

        extinguisher.use();
        this.setExtinguished(true);

        this.setTemperature(4000);
    }

    public void addLight(Light light)
    {
        if (light == null) {
            return;
        }

        this.setLight(light);
        this.getLight().setElectricityFlow(this.isRunning());
    }

    public void removeLight()
    {
        if (this.getLight() == null) {
            return;
        }

        this.getLight().setElectricityFlow(false);
        this.setLight(null);
    }

    public int getTemperature()
    {
        return temperature;
    }

    private void setTemperature(int temperature)
    {
        if (temperature <= 0) {
            this.temperature = 0;
            return;
        }

        if (temperature >= 6000) {
            this.temperature = 6000;
            this.turnOff();
            return;
        }

        this.temperature = temperature;
    }

    public int getDamage()
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

    private Animation getExtinguishedAnimation()
    {
        return extinguishedAnimation;
    }

    private void setExtinguishedAnimation(Animation extinguishedAnimation)
    {
        this.extinguishedAnimation = extinguishedAnimation;
    }

    public boolean isRunning()
    {
        if (this.getDamage() >= 100) {
            return false;
        }

        return running;
    }

    private void setRunning(boolean running)
    {
        this.running = running;
        this.powerAppliance();
        this.updateAnimation();
    }

    public boolean isExtinguished()
    {
        return extinguished;
    }

    private void setExtinguished(boolean extinguished)
    {
        this.extinguished = extinguished;
        this.updateAnimation();
    }

    public Light getLight()
    {
        return light;
    }

    public void setLight(Light light)
    {
        this.light = light;
    }

    private void powerAppliance()
    {
        if (this.getLight() == null) {
            return;
        }

        this.getLight().setElectricityFlow(this.isRunning());
    }
}
