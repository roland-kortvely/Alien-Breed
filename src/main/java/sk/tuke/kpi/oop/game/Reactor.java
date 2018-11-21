/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Reactor.
 */
public class Reactor extends AbstractActor implements Switchable, Repairable {

    private boolean running;
    private boolean extinguished;

    private int temperature;
    private int damage;

    private Animation offAnimation;
    private Animation normalAnimation;
    private Animation overheatedAnimation;
    private Animation brokenAnimation;
    private Animation extinguishedAnimation;

    private Set<EnergyConsumer> devices;

    /**
     * Instantiates a new Reactor.
     */
    public Reactor()
    {
        this.setDevices(new HashSet<>());

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

    /**
     * Increase temperature.
     *
     * @param increment the increment
     */
    public void increaseTemperature(int increment)
    {
        if (!this.isOn()) {
            return;
        }

        if (increment <= 0) {
            return;
        }

        if (this.getDamage() >= 100) {
            this.turnOff();
            return;
        }

        float temperature = ((float) this.getTemperature() + (float) increment * ((this.getDamage() >= 33.0f && this.getDamage() <= 66.0f) ? 1.5f : (this.getDamage() >= 66.0f) ? 2.0f : 1.0f));

        this.setTemperature((int) Math.floor(temperature));

        if (this.getTemperature() <= 2000) {
            return;
        }

        this.updateAnimation();

        float damage = (100.0f / 4000.0f) * (this.getTemperature() - 2000);

        if (damage > this.getDamage()) {
            this.setDamage((int) damage);
        }
    }

    /**
     * Decrease temperature.
     *
     * @param decrement the decrement
     */
    public void decreaseTemperature(int decrement)
    {
        if (!this.isOn()) {
            return;
        }

        if (decrement <= 0) {
            return;
        }

        if (this.getDamage() >= 100) {
            return;
        }

        this.setTemperature(this.getTemperature() - ((this.getDamage() >= 50) ? decrement / 2 : decrement));

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

        if (!this.isOn()) {
            setAnimation(this.getOffAnimation());
            return;
        }

        if (this.getTemperature() >= 4000) {
            setAnimation(this.getOverheatedAnimation());
            return;
        }

        setAnimation(this.getNormalAnimation());
    }

    @Override
    public boolean repair()
    {
        if (this.getDamage() <= 0 || this.getDamage() >= 100) {
            return false;
        }

        this.setDamage(this.getDamage() - 50);
        this.setTemperature(this.getDamage() * 40 + 2000);

        this.updateAnimation();

        return true;
    }

    /**
     * Extinguish boolean.
     *
     * @return the boolean
     */
    public boolean extinguish()
    {
        if (this.getDamage() < 100) {
            return false;
        }

        this.setExtinguished(true);
        this.setTemperature(4000);

        return true;
    }

    /**
     * Gets temperature.
     *
     * @return the temperature
     */
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
            //this.temperature = 6000;
            this.turnOff();
            //return;
        }

        this.temperature = temperature;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
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

    @Contract(pure = true)
    private Animation getOffAnimation()
    {
        return offAnimation;
    }

    private void setOffAnimation(Animation offAnimation)
    {
        this.offAnimation = offAnimation;
    }

    @Contract(pure = true)
    private Animation getNormalAnimation()
    {
        return normalAnimation;
    }

    private void setNormalAnimation(Animation normalAnimation)
    {
        this.normalAnimation = normalAnimation;
    }

    @Contract(pure = true)
    private Animation getOverheatedAnimation()
    {
        this.overheatedAnimation.setFrameDuration(0.05f - ((0.03f / 100.0f) * this.getDamage()));
        return overheatedAnimation;
    }

    private void setOverheatedAnimation(Animation overheatedAnimation)
    {
        this.overheatedAnimation = overheatedAnimation;
    }

    @Contract(pure = true)
    private Animation getBrokenAnimation()
    {
        return brokenAnimation;
    }

    private void setBrokenAnimation(Animation brokenAnimation)
    {
        this.brokenAnimation = brokenAnimation;
    }

    @Contract(pure = true)
    private Animation getExtinguishedAnimation()
    {
        return extinguishedAnimation;
    }

    private void setExtinguishedAnimation(Animation extinguishedAnimation)
    {
        this.extinguishedAnimation = extinguishedAnimation;
    }

    @Override
    public boolean isOn()
    {
        if (this.getDamage() >= 100) {
            return false;
        }

        return running;
    }

    private void setRunning(boolean running)
    {
        this.running = running;
        this.powerDevice();
        this.updateAnimation();
    }

    /**
     * Is extinguished boolean.
     *
     * @return the boolean
     */
    public boolean isExtinguished()
    {
        return extinguished;
    }

    private void setExtinguished(boolean extinguished)
    {
        this.extinguished = extinguished;
        this.updateAnimation();
    }

    /**
     * Add device.
     *
     * @param device the device
     */
    public void addDevice(EnergyConsumer device)
    {
        if (device == null) {
            return;
        }

        this.getDevices().add(device);
        device.setPowered(this.isOn());
    }

    /**
     * Remove device.
     *
     * @param device the device
     */
    public void removeDevice(EnergyConsumer device)
    {
        if (device == null) {
            return;
        }

        device.setPowered(false);
        this.getDevices().remove(device);
    }

    private void powerDevice()
    {
        for (EnergyConsumer device : this.getDevices()) {
            device.setPowered(this.isOn());
        }
    }

    /**
     * Gets devices.
     *
     * @return the devices
     */
    public Set<EnergyConsumer> getDevices()
    {
        return devices;
    }

    /**
     * Sets devices.
     *
     * @param devices the devices
     */
    public void setDevices(Set<EnergyConsumer> devices)
    {
        this.devices = devices;
    }
}
