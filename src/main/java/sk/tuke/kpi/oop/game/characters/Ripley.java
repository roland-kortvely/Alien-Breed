package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Ripley extends AbstractActor implements Movable {

    private Animation normalAnimation;

    private int speed;
    private int energy;
    private int ammo;

    public Ripley()
    {
        super("Ellen");

        this.setNormalAnimation(new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));

        this.setSpeed(2);
        this.setEnergy(50);
        this.setAmmo(450);

        this.getNormalAnimation().stop();

        setAnimation(this.getNormalAnimation());
    }

    @Override
    public int getSpeed()
    {
        return this.speed;
    }

    private void setSpeed(int speed)
    {
        this.speed = speed;
    }

    @Override
    public void startedMoving(Direction direction)
    {
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving()
    {
        getAnimation().stop();
    }

    @Contract(pure = true)
    private Animation getNormalAnimation()
    {
        return normalAnimation;
    }

    private void setNormalAnimation(Animation normalAnimation)
    {
        this.normalAnimation = normalAnimation;
    }

    public int getEnergy()
    {
        return energy;
    }

    @Contract(pure = true)
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    public int getAmmo()
    {
        return ammo;
    }

    public void setAmmo(int ammo)
    {
        this.ammo = ammo;
    }

    public void increaseAmmo(int ammo)
    {
        this.ammo = (this.ammo + ammo) > 500 ? 500 : (this.ammo + ammo);
    }
}
