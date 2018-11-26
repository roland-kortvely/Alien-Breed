/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

/**
 * The type Alien.
 */
public class Alien extends AbstractActor implements Alive, Enemy, Movable {

    private Health health;

    /**
     * Instantiates a new Alien.
     */
    public Alien()
    {
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();

        this.setHealth(new Health(100));
    }

    @Override
    public int getSpeed()
    {
        return 2;
    }

    @Override
    public void startedMoving(Direction direction)
    {
        //Rotate animation, and play it
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving()
    {
        getAnimation().stop();
    }

    @Override
    public Health getHealth()
    {
        return this.health;
    }

    private void setHealth(Health health)
    {
        this.health = health;
    }
}
