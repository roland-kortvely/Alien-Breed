/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Barrel extends Destructible {

    public Barrel()
    {
        setAnimation(new Animation("sprites/barrel.png", 16, 16));
    }
}