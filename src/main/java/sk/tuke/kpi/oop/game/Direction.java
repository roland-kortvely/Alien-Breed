/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;

public enum Direction {

    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),

    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),

    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),

    NONE(0, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    @Contract(pure = true)
    public int getDx()
    {
        return this.dx;
    }

    @Contract(pure = true)
    public int getDy()
    {
        return this.dy;
    }

    @Contract(pure = true)
    public float getAngle()
    {
        switch (this) {
            case NORTH:
                return 0;
            case EAST:
                return 270;
            case SOUTH:
                return 180;
            case WEST:
                return 90;

            case NORTHEAST:
                return 315;
            case NORTHWEST:
                return 45;
            case SOUTHEAST:
                return 225;
            case SOUTHWEST:
                return 135;

            default:
                return 0;
        }
    }

    public Direction combine(Direction other)
    {
        for (Direction direction : Direction.values()) {
            if (this.getDx() + other.getDx() != direction.getDx()) {
                continue;
            }

            if (this.getDy() + other.getDy() != direction.getDy()) {
                continue;
            }

            return direction;
        }

        return Direction.NONE;
    }
}
