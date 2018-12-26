/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.objects;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.Gameplay;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * The type Chain bomb.
 */
public class ChainBomb extends TimeBomb {

    /**
     * Instantiates a new Chain bomb.
     *
     * @param time the time
     */
    public ChainBomb(float time)
    {
        super(time);
    }

    @Override
    public void detonate()
    {
        Scene scene = Gameplay.getScene();

        Ellipse2D.Float ellipse = new Ellipse2D.Float(
            (this.getPosX() + this.getWidth() / 2.0f) - 50,
            (this.getPosY() + this.getHeight() / 2.0f) - 50,
            100, 100
        );

        for (Actor actor : scene.getActors()) {
            if (actor instanceof ChainBomb && !this.equals(actor)) {

                Rectangle2D.Float rectangle = new Rectangle2D.Float(actor.getPosX(), actor.getPosY(), actor.getWidth(), actor.getHeight());

                if (ellipse.intersects(rectangle)) {
                    ((ChainBomb) actor).activate();
                }
            }
        }

        super.detonate();
    }
}
