/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.commands.Destroy;
import sk.tuke.kpi.oop.game.commands.ReloadFirearm;

/**
 * The type Ammo.
 */
public class Ammo extends AbstractActor implements Item<Armed> {

    /**
     * Instantiates a new Ammo.
     */
    public Ammo()
    {
        setAnimation(new Animation("sprites/ammo.png", 16, 16));
    }

    @Override
    public void useWith(Armed actor)
    {
        if (!(new ReloadFirearm(50)).execute(actor)) {
            return;
        }

        new Destroy().execute(this);
    }

    @Override
    public Class<Armed> getUsingActorClass()
    {
        return Armed.class;
    }
}
