/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.objects;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.characters.Player;
import sk.tuke.kpi.oop.game.commands.AddActor;
import sk.tuke.kpi.oop.game.items.Item;
import sk.tuke.kpi.oop.game.items.Usable;

/**
 * The type Locker.
 */
public class Locker extends AbstractActor implements Usable<Player> {

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

    private Item content;

    private Orientation orientation;


    /**
     * Instantiates a new Locker.
     *
     * @param content     the content
     * @param orientation the orientation
     */
    public Locker(Item content, Orientation orientation)
    {
        setAnimation(new Animation("sprites/locker.png", 16, 16));

        this.setUsed(false);
        this.setContent(content);
        this.setOrientation(orientation);

        getAnimation().setRotation(getOrientation().getDeg());
    }

    @Override
    public void useWith(Player actor)
    {
        if (actor == null) {
            return;
        }

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
    public Class<Player> getUsingActorClass()
    {
        return Player.class;
    }

    @Contract(pure = true)
    private Item getContent()
    {
        return content;
    }

    private void setContent(Item content)
    {
        this.content = content;
    }

    @Contract(pure = true)
    private Orientation getOrientation()
    {
        return orientation;
    }

    private void setOrientation(Orientation orientation)
    {
        this.orientation = orientation;
    }
}
