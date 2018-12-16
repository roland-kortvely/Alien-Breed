/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Corpse extends AbstractActor {

    public Corpse()
    {
        setAnimation(new Animation("sprites/body.png", 64, 48));
    }
}
