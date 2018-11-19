/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Mjolnir extends Hammer {

    public Mjolnir()
    {
        this.setRemainingUses(4);
        setAnimation(new Animation("sprites/hammer.png", 16, 16));
    }
}
