/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.behaviours.Behaviour;

/**
 * The type Alien mother.
 */
public class AlienMother extends Alien {

    /**
     * Instantiates a new Alien mother.
     */
    public AlienMother()
    {
        this(100, null);
    }

    /**
     * Instantiates a new Alien mother.
     *
     * @param behaviour the behaviour
     */
    public AlienMother(Behaviour<? super Alien> behaviour)
    {
        this(100, behaviour);
    }

    /**
     * Instantiates a new Alien mother.
     *
     * @param healthValue the health value
     * @param behaviour   the behaviour
     */
    public AlienMother(int healthValue, Behaviour<? super Alien> behaviour)
    {
        super(healthValue, behaviour);

        setAnimation(new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();
    }
}
