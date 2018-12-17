/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Obstacle;

/**
 * The type Barrel.
 */
public class Barrel extends Destructible implements Obstacle {

    /**
     * Instantiates a new Barrel.
     */
    public Barrel()
    {
        setAnimation(new Animation("sprites/barrel.png", 16, 16));
    }
}
