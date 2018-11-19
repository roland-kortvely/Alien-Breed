/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController<M extends Movable> implements KeyboardListener {

    private M actor;

    private Move<M> action;

    private Map<Input.Key, Direction> keyDirectionMap;

    private Set<Direction> keys;

    public MovableController(M actor)
    {
        if (actor == null) {
            return;
        }

        this.setKeyDirectionMap(Map.ofEntries(
            Map.entry(Input.Key.UP, Direction.NORTH),
            Map.entry(Input.Key.DOWN, Direction.SOUTH),
            Map.entry(Input.Key.RIGHT, Direction.EAST),
            Map.entry(Input.Key.LEFT, Direction.WEST)

            /*
            Map.entry(Input.Key.W, Direction.NORTH),
            Map.entry(Input.Key.S, Direction.SOUTH),
            Map.entry(Input.Key.D, Direction.EAST),
            Map.entry(Input.Key.A, Direction.WEST)
            */
        ));

        this.setKeys(new HashSet<>());
        this.setActor(actor);
    }

    @Override
    public void keyPressed(@NotNull Input.Key key)
    {
        if (this.getActor() == null) {
            return;
        }

        if (!this.getKeyDirectionMap().containsKey(key)) {
            return;
        }

        this.getKeys().add(this.getKeyDirectionMap().get(key));

        this.update();
    }

    @Override
    public void keyReleased(@NotNull Input.Key key)
    {
        if (this.getActor() == null) {
            return;
        }

        if (!this.getKeyDirectionMap().containsKey(key)) {
            return;
        }

        this.getKeys().remove(this.getKeyDirectionMap().get(key));

        this.update();
    }

    private Direction direction()
    {
        Direction direction = Direction.NONE;

        for (Direction dir : this.getKeys()) {
            direction = direction.combine(dir);
        }

        return direction;
    }

    private void update()
    {
        if (this.getActor() == null) {
            return;
        }

        if (this.getAction() != null) {
            this.getAction().stop();
        }

        if (this.direction() != Direction.NONE) {
            this.setAction(new Move<>(this.direction(), 2));
            action.scheduleOn(this.getActor());
        } else {
            this.getAction().stop();
        }
    }

    @Contract(pure = true)
    private M getActor()
    {
        return actor;
    }

    private void setActor(M actor)
    {
        this.actor = actor;
    }

    @Contract(pure = true)
    private Move getAction()
    {
        return action;
    }

    public void setAction(Move<M> action)
    {
        this.action = action;
    }

    @Contract(pure = true)
    private Map<Input.Key, Direction> getKeyDirectionMap()
    {
        return keyDirectionMap;
    }

    private void setKeyDirectionMap(Map<Input.Key, Direction> keyDirectionMap)
    {
        this.keyDirectionMap = keyDirectionMap;
    }

    @Contract(pure = true)
    private Set<Direction> getKeys()
    {
        return keys;
    }

    private void setKeys(Set<Direction> keys)
    {
        this.keys = keys;
    }
}
