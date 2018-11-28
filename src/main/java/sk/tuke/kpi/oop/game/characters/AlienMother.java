/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

/**
 * The type Alien mother.
 */
public class AlienMother extends Alien {

    /**
     * Instantiates a new Alien mother.
     */
    public AlienMother()
    {
        super();
        setAnimation(new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();

        this.setHealth(new Health(200));
        this.getHealth().onExhaustion(this::die);
    }
}
