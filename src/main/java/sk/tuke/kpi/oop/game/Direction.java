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
    public static Direction fromAngle(float angle)
    {
        if (angle == 0.0f) {
            return NORTH;
        } else if (angle == 270.0f) {
            return EAST;
        } else if (angle == 180.0f) {
            return SOUTH;
        } else if (angle == 90.0f) {
            return WEST;
        } else if (angle == 315.0f) {
            return NORTHEAST;
        } else if (angle == 45.0f) {
            return NORTHWEST;
        } else if (angle == 225.0f) {
            return SOUTHEAST;
        } else if (angle == 135.0f) {
            return SOUTHWEST;
        }

        return NONE;
    }

    /**
     * Combine direction.
     *
     * @param other the other
     *
     * @return the direction
     */
    @Contract("null -> this")
    public Direction combine(Direction other)
    {
        if (other == null) {
            return this;
        }

        int nDx = (this.getDx() + other.getDx() > 1) ? 1 : this.getDx() + other.getDx();
        nDx = (nDx < -1) ? -1 : nDx;

        int nDy = (this.getDy() + other.getDy() > 1) ? 1 : this.getDy() + other.getDy();
        nDy = (nDy < -1) ? -1 : nDy;

        for (Direction direction : Direction.values()) {
            if (nDx != direction.getDx()) {
                continue;
            }

            if (nDy != direction.getDy()) {
                continue;
            }

            return direction;
        }

        return Direction.NONE;
    }

    /**
     * Random direction.
     *
     * @return the direction
     */
    public static Direction random()
    {
        return (Direction.values())[(new Random()).nextInt(Direction.values().length)];
    }
}
