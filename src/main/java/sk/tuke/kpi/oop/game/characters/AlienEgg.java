/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.commands.AddActor;
import sk.tuke.kpi.oop.game.interfaces.Enemy;
import sk.tuke.kpi.oop.game.interfaces.Player;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * The type Alien egg.
 */
public class AlienEgg extends AbstractAliveEnemy {

    private Enemy enemy;

    /**
     * Instantiates a new Alien egg.
     *
     * @param enemy the enemy
     */
    public AlienEgg(Enemy enemy)
    {
        super(10);

        setAnimation(new Animation("sprites/alien_egg.png", 32, 32, 0.2f, Animation.PlayMode.ONCE));
        getAnimation().stop();

        this.setEnemy(enemy);
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        new When<>(
            action -> intersection(scene),
            new Invoke<>(this::drop)
        ).scheduleOn(this);
    }

    private boolean intersection(@NotNull Scene scene)
    {
        Ellipse2D.Float ellipse = new Ellipse2D.Float(
            (this.getPosX() + this.getWidth() / 2.0f) - 15,
            (this.getPosY() + this.getHeight() / 2.0f) - 15,
            100, 100
        );

        Player player = scene.getFirstActorByType(Player.class);
        if (player == null) {
            return false;
        }

        Rectangle2D.Float rectangle = new Rectangle2D.Float(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        return ellipse.intersects(rectangle);
    }

    private void drop()
    {
        getAnimation().play();

        new When<>(
            (action) -> this.getAnimation().getCurrentFrameIndex() >= (this.getAnimation().getFrameCount() - 1),
            new Invoke<>(() -> {
                new AddActor(this.getEnemy(), this.getPosX(), this.getPosY()).execute(this);
                //new Destroy().execute(this);
            })
        ).scheduleOn(this);
    }

    @Contract(pure = true)
    private Enemy getEnemy()
    {
        return enemy;
    }

    private void setEnemy(Enemy enemy)
    {
        this.enemy = enemy;
    }
}
