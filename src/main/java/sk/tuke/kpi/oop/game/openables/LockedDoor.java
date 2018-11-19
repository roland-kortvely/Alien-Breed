/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.game.items.AccessCard;

public class LockedDoor extends Door {

    private boolean locked;

    public LockedDoor()
    {
        super();

        this.lock();
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

        this.unlock();
    }

    public void lock()
    {
        this.setLocked(true);
        this.close();
    }

    public void unlock()
    {
        this.setLocked(false);
        this.open();
    }

    public boolean isLocked()
    {
        return locked;
    }

    private void setLocked(boolean locked)
    {
        this.locked = locked;
    }
}
