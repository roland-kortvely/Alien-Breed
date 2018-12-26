/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.commands.Destroy;
import sk.tuke.kpi.oop.game.interfaces.Alive;
import sk.tuke.kpi.oop.game.interfaces.Enemy;
import sk.tuke.kpi.oop.game.interfaces.Obstacle;

/**
 * The type Abstract alive enemy.
 */
public abstract class AbstractAliveEnemy extends AbstractActor implements Alive, Enemy, Obstacle {

    /**
     * The Health.
     */
    private Health health;

    /**
     * Instantiates a new Abstract alive enemy.
     *
     * @param health the health
     */
    public AbstractAliveEnemy(int health)
    {
        this.health = new Health(health);
        this.health.onExhaustion(this::die);
    }

    /**
     * Die.
     */
    protected void die()
    {
        getAnimation().stop();
        new Destroy().execute(this);
    }

    @Override
    public Health getHealth()
    {
        return this.health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(Health health)
    {
        this.health = health;
    }
}
