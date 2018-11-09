package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.Contract;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Ripley extends AbstractActor implements Movable {

    private Animation normalAnimation;

    private int speed;

    public Ripley()
    {
        super("Ellen");

        this.setNormalAnimation(new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));

        this.getNormalAnimation().stop();

        setAnimation(this.getNormalAnimation());

        this.setSpeed(2);
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
}
