/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.objects;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.characters.Health;
import sk.tuke.kpi.oop.game.commands.Destroy;
import sk.tuke.kpi.oop.game.items.Fragile;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Destructible.
 */
public abstract class Destructible extends AbstractActor implements Fragile {

    private Animation explosionAnimation;

    private Health health;

    private List<Destructible.DestructionEffect> destructionEffects;

    /**
     * Instantiates a new Destructible.
     */
    public Destructible()
    {
        this(10);
    }

    /**
     * Instantiates a new Destructible.
     *
     * @param health the health
     */
    public Destructible(int health)
    {
        this.health = new Health(health);
        this.health.onExhaustion(this::explode);

        this.explosionAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);

        this.setDestructionEffects(new ArrayList<>());
    }

    /**
     * The interface Destruction effect.
     */
    @FunctionalInterface
    public interface DestructionEffect {

        /**
         * Apply.
         */
        void apply();
    }


    /**
     * On destruction.
     *
     * @param effect the effect
     */
    public void onDestruction(DestructionEffect effect)
    {
        this.getDestructionEffects().add(effect);
    }

    @Override
    public void explode()
    {
        setAnimation(this.explosionAnimation);

        new When<>(
            (action) -> this.getAnimation().getCurrentFrameIndex() >= (this.getAnimation().getFrameCount() - 1),
            new Invoke<>(() -> {

                if (!this.getDestructionEffects().isEmpty()) {
                    for (DestructionEffect destructionEffect : this.getDestructionEffects()) {
                        destructionEffect.apply();
                    }
                }

                new Destroy().execute(this);
            })
        ).scheduleOn(this);
    }

    @Override
    public Health getHealth()
    {
        return this.health;
    }

    @Contract(pure = true)
    private List<DestructionEffect> getDestructionEffects()
    {
        return destructionEffects;
    }

    private void setDestructionEffects(List<DestructionEffect> destructionEffects)
    {
        this.destructionEffects = destructionEffects;
    }
}
