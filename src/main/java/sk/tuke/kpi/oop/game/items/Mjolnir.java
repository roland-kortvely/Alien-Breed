/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;

/**
 * The type Mjolnir.
 */
public class Mjolnir extends Hammer {

    /**
     * Instantiates a new Mjolnir.
     */
    public Mjolnir()
    {
        this.setRemainingUses(4);
        setAnimation(new Animation("sprites/hammer.png", 16, 16));
    }
}
