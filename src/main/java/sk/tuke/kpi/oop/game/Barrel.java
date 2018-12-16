/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;

/**
 * The type Barrel.
 */
public class Barrel extends Destructible {

    /**
     * Instantiates a new Barrel.
     */
    public Barrel()
    {
        setAnimation(new Animation("sprites/barrel.png", 16, 16));
    }

    @Override
    protected void onDestruction()
    {
        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }

        scene.getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.CLEAR);
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        scene.getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
    }
}
