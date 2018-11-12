package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<T extends AbstractActor> extends AbstractAction<T> {

    private T actor;

    private Usable usable;

    private boolean done;

    public Use(Usable usable)
    {
        this.setUsable(usable);
        this.setDone(false);
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            return;
        }

        if (this.getUsable() == null) {
            return;
        }

        this.getUsable().useWith(this.getActor());
        this.setDone(true);
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull T actor)
    {
        this.setActor(actor);
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

    public Usable getUsable()
    {
        return usable;
    }

    public void setUsable(Usable usable)
    {
        this.usable = usable;
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
