/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.commands.ReloadFirearm;

/**
 * The type Ammo.
 */
public class Ammo extends AbstractActor implements Usable<Armed> {

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
        if (!(new ReloadFirearm()).execute(actor)) {
            return;
        }

        Scene scene = this.getScene();
        if (scene != null) {
            scene.removeActor(this);
        }
    }

    @Override
    public Class<Armed> getUsingActorClass()
    {
        return Armed.class;
    }
}
