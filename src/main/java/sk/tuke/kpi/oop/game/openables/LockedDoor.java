/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.interfaces.Lockable;

/**
 * The type Locked door.
 */
public class LockedDoor extends Door implements Lockable {

    private boolean locked;

    /**
     * Instantiates a new Locked door.
     */
    public LockedDoor(String name, Orientation orientation)
    {
        super(name, orientation);

        this.lock(null);
    }

    @Override
    public void useWith(Actor actor)
    {
        if (actor == null) {
            return;
        }

        if (!(actor instanceof AccessCard)) {
            return;
        }

        if (!this.isLocked()) {
            return;
        }

        this.unlock(null);
    }

    /**
     * Lock.
     */
    public void lock(Actor actor)
    {
        this.setLocked(true);
        this.close();
    }

    /**
     * Unlock.
     */
    public void unlock(Actor actor)
    {
        this.setLocked(false);
        this.open();
    }

    /**
     * Is locked boolean.
     *
     * @return the boolean
     */
    public boolean isLocked()
    {
        return locked;
    }

    private void setLocked(boolean locked)
    {
        this.locked = locked;
    }
}
