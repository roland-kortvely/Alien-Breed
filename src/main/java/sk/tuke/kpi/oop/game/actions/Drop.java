package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Drop<A extends Keeper> extends AbstractAction<A> {

    private A actor;

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            this.setDone(true);
            return;
        }

        Scene scene = this.getActor().getScene();

        if (scene == null) {
            this.setDone(true);
            return;
        }

        try {
            scene.addActor(this.getActor().getContainer().peek(), this.getActor().getPosX(), this.getActor().getPosY());
            this.getActor().getContainer().remove(this.getActor().getContainer().peek());
        } catch (Exception ex) {
            scene.getGame().getOverlay().drawText(ex.getMessage(), 0, 0).showFor(2);
        }

        this.setDone(true);
    }

    @Nullable
    @Override
    public A getActor()
    {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable A actor)
    {
        this.actor = actor;
    }
}
