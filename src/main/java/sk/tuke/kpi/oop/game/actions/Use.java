package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<T extends Usable> extends AbstractAction<T> {

    private T actor;
    private T target;

    private boolean done;

    public Use(T actor)
    {
        this.setActor(actor);
        this.setDone(false);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            return;
        }

        if (this.getTarget() == null) {
            return;
        }

        this.getActor().useWith(this.getTarget());
        this.setDone(true);
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull T actor)
    {
        this.setTarget(actor);
        return super.scheduleOn(actor);
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

    public T getTarget()
    {
        return target;
    }

    public void setTarget(T target)
    {
        this.target = target;
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
