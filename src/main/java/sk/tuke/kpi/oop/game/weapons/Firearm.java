/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.Contract;

/**
 * The type Firearm.
 */
public abstract class Firearm {

    private int ammo;
    private int maxAmmo;

    /**
     * Instantiates a new Firearm.
     *
     * @param ammo the ammo
     */
    public Firearm(int ammo)
    {
        this(ammo, ammo);
    }

    /**
     * Instantiates a new Firearm.
     *
     * @param ammo    the ammo
     * @param maxAmmo the max ammo
     */
    public Firearm(int ammo, int maxAmmo)
    {
        this.setAmmo(ammo);
        this.setMaxAmmo(maxAmmo);
    }

    /**
     * Fire fireable.
     *
     * @return the fireable
     */
    public Fireable fire()
    {
        if (this.getAmmo() <= 0) {
            return null;
        }

        this.setAmmo(this.getAmmo() - 1);

        return this.createBullet();
    }

    /**
     * Create fireable bullet.
     *
     * @return the fireable
     */
    protected abstract Fireable createBullet();

    /**
     * Reload.
     *
     * @param newAmmo the new ammo
     */
    public void reload(int newAmmo)
    {
        this.setAmmo((this.getAmmo() + newAmmo <= this.getMaxAmmo()) ? this.getAmmo() + newAmmo : this.getMaxAmmo());
    }

    /**
     * Gets ammo.
     *
     * @return the ammo
     */
    public int getAmmo()
    {
        return ammo;
    }

    private void setAmmo(int ammo)
    {
        this.ammo = ammo;
    }

    @Contract(pure = true)
    private int getMaxAmmo()
    {
        return maxAmmo;
    }

    private void setMaxAmmo(int maxAmmo)
    {
        this.maxAmmo = maxAmmo;
    }

    /**
     * Ammo is full boolean.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return this.getAmmo() == this.getMaxAmmo();
    }
}
