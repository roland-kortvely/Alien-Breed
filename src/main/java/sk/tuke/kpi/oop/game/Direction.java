/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;

import java.util.Random;

/**
 * The enum Direction.
 */
public enum Direction {

    /**
     * North direction.
     */
    NORTH(0, 1),
    /**
     * East direction.
     */
    EAST(1, 0),
    /**
     * South direction.
     */
    SOUTH(0, -1),
    /**
     * West direction.
     */
    WEST(-1, 0),

    /**
     * Northeast direction.
     */
    NORTHEAST(1, 1),
    /**
     * Northwest direction.
     */
    NORTHWEST(-1, 1),

    /**
     * Southeast direction.
     */
    SOUTHEAST(1, -1),
    /**
     * Southwest direction.
     */
    SOUTHWEST(-1, -1),

    /**
     * None direction.
     */
    NONE(0, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    @Contract(pure = true)
    public int getDx()
    {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    @Contract(pure = true)
    public int getDy()
    {
        return this.dy;
    }

    /**
     * Gets angle.
     *
     * @return the angle
     */
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

    /**
     * From angle direction.
     *
     * @param angle the angle
     *
     * @return the direction
     */
    @Contract(pure = true)
    public Direction fromAngle(float angle)
    {
        switch ((int) angle) {
            case 0:
                return NORTH;
            case 270:
                return EAST;
            case 180:
                return SOUTH;
            case 90:
                return WEST;

            case 315:
                return NORTHEAST;
            case 45:
                return NORTHWEST;
            case 225:
                return SOUTHEAST;
            case 135:
                return SOUTHWEST;

            default:
                return NONE;
        }
    }

    /**
     * Combine direction.
     *
     * @param other the other
     *
     * @return the direction
     */
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

    public Direction random()
    {
        return (Direction.values())[(new Random()).nextInt(Direction.values().length)];
    }
}
