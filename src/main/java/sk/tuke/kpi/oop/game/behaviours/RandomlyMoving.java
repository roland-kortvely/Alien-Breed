/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.behaviours;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

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

        new Loop<>(
            new ActionSequence<>(
                new Invoke<>(() -> new Move<>((Direction.random()), 0.5f).scheduleOn(this.getMovable())),
                new Wait<>(0.5f)
            )
        ).scheduleOn(this.getMovable());
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
