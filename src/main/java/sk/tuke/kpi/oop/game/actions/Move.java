package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move<T extends Movable> implements Action<T> {

    private T actor;

    private boolean firstCall;
    private boolean done;

    private Direction direction;

    private float duration;

    public Move(Direction direction)
    {
        this(direction, 0.0f);
    }

    public Move(Direction direction, float duration)
    {
        this.setFirstCall(true);
        this.setDone(false);
        this.setDirection(direction);
        this.setDuration(duration);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            return;
        }

        this.setDuration(this.getDuration() - deltaTime);

        if (this.isFirstCall()) {
            this.getActor().startedMoving(this.getDirection());
            this.setFirstCall(false);
        }

        switch (this.getDirection()) {
            case NORTH:
                this.getActor().setPosition(this.getActor().getPosX(), this.getActor().getPosY() + this.getActor().getSpeed());
                break;
            case EAST:
                this.getActor().setPosition(this.getActor().getPosX() + this.getActor().getSpeed(), this.getActor().getPosY());
                break;
            case SOUTH:
                this.getActor().setPosition(this.getActor().getPosX(), this.getActor().getPosY() - this.getActor().getSpeed());
                break;
            case WEST:
                this.getActor().setPosition(this.getActor().getPosX() - this.getActor().getSpeed(), this.getActor().getPosY());
                break;
            default:
            case NONE:
                break;
        }

        if (this.getDuration() <= 0.00001f) {
            this.stop();
        }
    }

    @Override
    public void reset()
    {

    }

    public void stop()
    {
        if (this.getActor() == null) {
            return;
        }

        this.getActor().stoppedMoving();
        this.setDone(true);
    }

    @Nullable
    @Override
    public T getActor()
    {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable T actor)
    {
        this.actor = actor;
    }

    public boolean isFirstCall()
    {
        return firstCall;
    }

    public void setFirstCall(boolean firstCall)
    {
        this.firstCall = firstCall;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public float getDuration()
    {
        return duration;
    }

    public void setDuration(float duration)
    {
        this.duration = duration;
    }

    @Override
    public boolean isDone()
    {
        return this.done;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }
}
