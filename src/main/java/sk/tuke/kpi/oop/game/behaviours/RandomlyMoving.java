/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.behaviours;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.oop.game.Movable;

/**
 * The type Randomly moving.
 */
public class RandomlyMoving implements Behaviour<Movable> {

    private Movable movable;

    private void randomness()
    {
        if (this.getMovable() == null) {
            return;
        }

        //TODO:: Random movement
        System.out.println("Randomness");
    }

    @Override
    public void setUp(Movable actor)
    {
        this.setMovable(actor);
        this.randomness();
    }

    @Contract(pure = true)
    private Movable getMovable()
    {
        return movable;
    }

    private void setMovable(Movable movable)
    {
        this.movable = movable;
    }
}
