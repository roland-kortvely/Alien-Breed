/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;


/**
 * The type Alien spitter.
 */
public class AlienSpitter extends Alien {


    /**
     * Instantiates a new Alien spitter.
     */
    public AlienSpitter()
    {
        this(100, null);
    }


    /**
     * Instantiates a new Alien spitter.
     *
     * @param behaviour the behaviour
     */
    public AlienSpitter(Behaviour<? super Alien> behaviour)
    {
        this(100, behaviour);
    }


    /**
     * Instantiates a new Alien spitter.
     *
     * @param healthValue the health value
     * @param behaviour   the behaviour
     */
    public AlienSpitter(int healthValue, Behaviour<? super Alien> behaviour)
    {
        super(healthValue, behaviour);

        setAnimation(new Animation("sprites/spitter_alien.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();

        this.setHealth(new Health(200));
        this.getHealth().onExhaustion(this::die);
    }
}
