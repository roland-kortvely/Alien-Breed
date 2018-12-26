/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.objects;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.commands.AddActor;
import sk.tuke.kpi.oop.game.items.Item;
import sk.tuke.kpi.oop.game.items.Lockable;
import sk.tuke.kpi.oop.game.items.Usable;

/**
 * The type Locker.
 */
public class Locker extends AbstractActor implements Lockable, Usable<Actor> {

    /**
     * The enum Orientation.
     */
    public enum Orientation {
        /**
         * Up orientation.
         */
        UP(0),
        /**
         * Down orientation.
         */
        DOWN(270),
        /**
         * Left orientation.
         */
        LEFT(180),
        /**
         * Right orientation.
         */
        RIGHT(0);

        private final int deg;

        Orientation(int deg)
        {
            this.deg = deg;
        }

        /**
         * Gets deg.
         *
         * @return the deg
         */
        @Contract(pure = true)
        public int getDeg()
        {
            return deg;
        }}

    private boolean used;

    private Item<?> content;

    private Orientation orientation;

    private boolean locked;

    private Locker(@NotNull LockerBuilder lockerBuilder)
    {
        setAnimation(new Animation("sprites/locker.png", 16, 16));

        this.content = lockerBuilder.content;
        this.orientation = lockerBuilder.orientation;
        this.locked = lockerBuilder.locked;

        this.setUsed(false);

        getAnimation().setRotation(getOrientation().getDeg());
    }

    @Override
    public void unlock(@NotNull Actor actor)
    {
        this.drop(actor);
    }

    @Override
    public void useWith(Actor actor)
    {
        if (actor == null) {
            return;
        }

       this.drop(actor);
    }

    private void drop(@NotNull Actor actor)
    {
        if (this.isUsed()) {
            return;
        }

        if (new AddActor(this.getContent(), actor.getPosX(), actor.getPosY()).execute(this)) {
            this.setUsed(true);
        }
    }

    @Contract(pure = true)
    private boolean isUsed()
    {
        return used;
    }

    private void setUsed(boolean used)
    {
        this.used = used;
    }

    @Override
    public Class<Actor> getUsingActorClass()
    {
        return Actor.class;
    }

    @Contract(pure = true)
    private Item<?> getContent()
    {
        return content;
    }

    @Contract(pure = true)
    private Orientation getOrientation()
    {
        return orientation;
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

    /**
     * The type Locker builder.
     */
    public static class LockerBuilder {

        private Item<?> content;

        private Orientation orientation;

        private boolean locked;

        /**
         * Instantiates a new Locker builder.
         *
         * @param content     the content
         * @param orientation the orientation
         */
        public LockerBuilder(Item<?> content, Orientation orientation)
        {
            this.content = content;
            this.orientation = orientation;
            this.locked = false;
        }

        /**
         * Sets locked.
         *
         * @return the locked
         */
        public LockerBuilder setLocked()
        {
            this.locked = true;
            return this;
        }

        /**
         * Build locker.
         *
         * @return the locker
         */
        public Locker build()
        {
            return new Locker(this);
        }
    }
}
