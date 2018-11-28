/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.weapons;

/**
 * The type Gun.
 */
public class Gun extends Firearm {

    /**
     * Instantiates a new Gun.
     *
     * @param ammo the ammo
     */
    public Gun(int ammo)
    {
        super(ammo);
    }

    /**
     * Instantiates a new Gun.
     *
     * @param ammo    the ammo
     * @param maxAmmo the max ammo
     */
    public Gun(int ammo, int maxAmmo)
    {
        super(ammo, maxAmmo);
    }

    @Override
    protected Fireable createBullet()
    {
        return new Bullet();
    }
}
