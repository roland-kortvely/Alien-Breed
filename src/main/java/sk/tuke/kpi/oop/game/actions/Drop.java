package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;
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
            ActorContainer<Actor> container = this.getActor().getContainer();
            if (container == null) {
                this.setDone(true);
                return;
            }

            Actor actor = container.peek();
            if (actor == null) {
                this.setDone(true);
                return;
            }

            scene.addActor(actor, this.getActor().getPosX(), this.getActor().getPosY());
            container.remove(actor);
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
