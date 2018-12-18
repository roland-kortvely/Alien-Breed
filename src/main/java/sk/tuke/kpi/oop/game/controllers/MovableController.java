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

/**
 * The type Movable controller.
 */
public class MovableController implements KeyboardListener {

    private Movable actor;

    private Move<Movable> action;

    private Map<Input.Key, Direction> keyDirectionMap;

    private Set<Direction> keys;

    /**
     * Instantiates a new Movable controller.
     *
     * @param actor the actor
     */
    public MovableController(Movable actor)
    {
        this.setKeyDirectionMap(Map.ofEntries(
            Map.entry(Input.Key.UP, Direction.NORTH),
            Map.entry(Input.Key.DOWN, Direction.SOUTH),
            Map.entry(Input.Key.RIGHT, Direction.EAST),
            Map.entry(Input.Key.LEFT, Direction.WEST)
        ));

        this.setKeys(new HashSet<>());
        this.setActor(actor);
    }

    @Override
    public void keyPressed(@NotNull Input.Key key)
    {
        //Validate pressed key
        if (!this.isValidKey(key)) {
            return;
        }

        if (this.getKeys().contains(this.getKeyDirectionMap().get(key))) {
            return;
        }

        this.getKeys().add(this.getKeyDirectionMap().get(key));

        //Update movement
        this.update();
    }

    @Override
    public void keyReleased(@NotNull Input.Key key)
    {
        //Validate released key
        if (!this.isValidKey(key)) {
            return;
        }

        if (!this.getKeys().contains(this.getKeyDirectionMap().get(key))) {
            return;
        }

        this.getKeys().remove(this.getKeyDirectionMap().get(key));

        //Update movement
        this.update();
    }

    private void update()
    {
        if (this.getActor() == null) {
            return;
        }

        //Stop current move
        if (this.getAction() != null) {
            this.getAction().stop();
        }

        //Retrieve new direction
        Direction direction = this.direction();
        if (direction == Direction.NONE) {
            return;
        }

        //Schedule move
        this.setAction(new Move<>(direction, 999));
        action.scheduleOn(this.getActor());
    }

    private Direction direction()
    {
        Direction direction = Direction.NONE;

        for (Direction dir : this.getKeys()) {
            direction = direction.combine(dir);
        }

        return direction;
    }

    private boolean isValidKey(@NotNull Input.Key key)
    {
        if (this.getActor() == null) {
            return false;
        }

        return this.getKeyDirectionMap().containsKey(key);
    }

    /**
     * Gets actor.
     *
     * @return the actor
     */
    @Contract(pure = true)
    public Movable getActor()
    {
        return actor;
    }

    /**
     * Sets actor.
     *
     * @param actor the actor
     */
    public void setActor(Movable actor)
    {
        this.actor = actor;
    }

    @Contract(pure = true)
    private Move<?> getAction()
    {
        return action;
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(Move<Movable> action)
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
