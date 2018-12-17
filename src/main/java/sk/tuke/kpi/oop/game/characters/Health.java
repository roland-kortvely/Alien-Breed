/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Health.
 */
public class Health {

    private int value;
    private int maxValue;

    private List<ExhaustionEffect> exhaustionEffects;

    private boolean alive;

    /**
     * The interface Exhaustion effect.
     */
    @FunctionalInterface
    public interface ExhaustionEffect {

        /**
         * Apply.
         */
        void apply();
    }

    /**
     * Instantiates a new Health.
     *
     * @param value the value
     */
    public Health(int value)
    {
        this(value, value);
    }

    /**
     * Instantiates a new Health.
     *
     * @param value    the value
     * @param maxValue the max value
     */
    public Health(int value, int maxValue)
    {
        this.setAlive(true);
        this.setValue(value);
        this.setMaxValue(maxValue);
        this.setExhaustionEffects(new ArrayList<>());
    }

    /**
     * On exhaustion.
     *
     * @param effect the effect
     */
    public void onExhaustion(ExhaustionEffect effect)
    {
        this.getExhaustionEffects().add(effect);
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Contract(pure = true)
    public int getValue()
    {
        return value;
    }

    private void setValue(int value)
    {
        this.value = value;
    }

    /**
     * Gets max value.
     *
     * @return the max value
     */
    @Contract(pure = true)
    public int getMaxValue()
    {
        return maxValue;
    }

    private void setMaxValue(int maxValue)
    {
        this.maxValue = maxValue;
    }

    /**
     * Health is full boolean.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return this.getValue() == this.getMaxValue();
    }

    /**
     * Refill.
     *
     * @param amount the amount
     */
    public void refill(int amount)
    {
        this.setValue((this.getValue() + amount <= this.getMaxValue()) ? this.getValue() + amount : this.getMaxValue());
        this.setAlive(true);
    }

    /**
     * Restore.
     */
    public void restore()
    {
        this.setValue(this.getMaxValue());
        this.setAlive(true);
    }

    /**
     * Drain.
     *
     * @param amount the amount
     */
    public void drain(int amount)
    {
        this.setValue((this.getValue() - amount >= 0) ? this.getValue() - amount : 0);

        if (this.getValue() <= 0) {
            this.die();
        }
    }

    /**
     * Exhaust.
     */
    public void exhaust()
    {
        this.setValue(0);
        this.die();
    }

    private void die()
    {
        if (!this.isAlive()) {
            return;
        }

        this.setAlive(false);

        if (this.getExhaustionEffects().isEmpty()) {
            return;
        }

        for (ExhaustionEffect exhaustionEffect : this.getExhaustionEffects()) {
            exhaustionEffect.apply();
        }
    }

    @Contract(pure = true)
    private List<ExhaustionEffect> getExhaustionEffects()
    {
        return exhaustionEffects;
    }

    private void setExhaustionEffects(List<ExhaustionEffect> exhaustionEffects)
    {
        this.exhaustionEffects = exhaustionEffects;
    }

    @Contract(pure = true)
    private boolean isAlive()
    {
        return alive;
    }

    private void setAlive(boolean alive)
    {
        this.alive = alive;
    }
}
