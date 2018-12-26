/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.objects;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Gameplay;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.commands.DrainHealth;
import sk.tuke.kpi.oop.game.items.Obstacle;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * The type Barrel.
 */
public class Barrel extends Destructible implements Obstacle {

    /**
     * Instantiates a new Barrel.
     */
    public Barrel()
    {
        setAnimation(new Animation("sprites/barrel.png", 16, 16));

        this.onDestruction(this::destroyed);
    }

    private void destroyed()
    {
        Scene scene = Gameplay.getScene();

        Ellipse2D.Float ellipse = new Ellipse2D.Float(
            (this.getPosX() + this.getWidth() / 2.0f) - 50,
            (this.getPosY() + this.getHeight() / 2.0f) - 50,
            100, 100
        );

        for (Actor actor : scene.getActors()) {

            if (actor instanceof Alive) {
                Rectangle2D.Float rectangle = new Rectangle2D.Float(actor.getPosX(), actor.getPosY(), actor.getWidth(), actor.getHeight());

                if (ellipse.intersects(rectangle)) {
                    new DrainHealth(10).execute((Alive) actor);
                }
            }

            if (actor instanceof Barrel && !this.equals(actor)) {

                Rectangle2D.Float rectangle = new Rectangle2D.Float(actor.getPosX(), actor.getPosY(), actor.getWidth(), actor.getHeight());

                if (ellipse.intersects(rectangle)) {
                    ((Barrel) actor).explode();
                }
            }
        }
    }
}
