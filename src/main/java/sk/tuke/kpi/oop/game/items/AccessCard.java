/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

/**
 * The type Access card.
 */
public class AccessCard extends AbstractActor implements Item, Collectible, Usable<Lockable> {

    /**
     * Instantiates a new Access card.
     */
    public AccessCard()
    {
        setAnimation(new Animation("sprites/key.png", 16, 16));
    }

    @Override
    public void useWith(Lockable actor)
    {
        if (actor == null) {
            return;
        }

        if (!actor.isLocked()) {
            return;
        }

        actor.unlock(actor);
    }

    @Override
    public Class<Lockable> getUsingActorClass()
    {
        return Lockable.class;
    }
}
