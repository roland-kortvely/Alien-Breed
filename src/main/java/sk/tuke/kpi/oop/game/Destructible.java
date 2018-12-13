/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.characters.Health;
import sk.tuke.kpi.oop.game.items.Fragile;

public abstract class Destructible extends AbstractActor implements Fragile {

    private Animation explosionAnimation;

    private Health health;

    public Destructible()
    {
        this(10);
    }

    public Destructible(int health)
    {
        this.health = new Health(health);

        this.health.onExhaustion(this::explode);

        this.explosionAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);
    }

    @Override
    public void explode()
    {
        setAnimation(this.explosionAnimation);

        new When<>(
            (action) -> this.getAnimation().getCurrentFrameIndex() >= (this.getAnimation().getFrameCount() - 1),
            new Invoke<>(() -> {
                this.onDestruction();
                Scene scene = this.getScene();
                if (scene != null) {
                    scene.removeActor(this);
                }
            })
        ).scheduleOn(this);
    }

    protected abstract void onDestruction();

    @Override
    public Health getHealth()
    {
        return this.health;
    }
}
