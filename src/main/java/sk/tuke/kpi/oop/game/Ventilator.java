/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Ventilator extends AbstractActor implements Repairable {

    private boolean broken;

    public Ventilator()
    {
        setAnimation(new Animation("sprites/ventilator.png", 32, 32, 0.1f, Animation.PlayMode.LOOP));
        getAnimation().stop();

        this.setBroken(true);
    }

    @Override
    public boolean repair()
    {
        if (!this.isBroken()) {
            return false;
        }

        getAnimation().play();
        this.setBroken(false);
        return true;
    }

    @Contract(pure = true)
    private boolean isBroken()
    {
        return broken;
    }

    private void setBroken(boolean broken)
    {
        this.broken = broken;
    }
}
