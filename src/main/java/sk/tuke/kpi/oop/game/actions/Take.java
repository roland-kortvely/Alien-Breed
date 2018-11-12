package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Optional;

public class Take<K extends Collectible, A extends Keeper> extends AbstractAction<A> {

    private A actor;

    private Class<K> takeableActorsClass;

    public Take(Class<K> takeableActorsClass)
    {
        this.takeableActorsClass = takeableActorsClass;
    }

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

        Optional<Actor> query = scene.getActors().stream()
            .filter(actor -> this.takeableActorsClass.isInstance(actor))
            .filter(actor -> actor.intersects(this.getActor()))
            .findFirst();

        if (!query.isPresent()) {
            this.setDone(true);
            return;
        }

        try {
            this.getActor().getContainer().add(query.get());
            scene.removeActor(query.get());
        } catch (Exception ex) {
            scene.getGame().getOverlay().drawText(ex.getMessage(), 0, 0).showFor(2);
        }

        this.setDone(true);
    }

    @Nullable
    @Override
    public A getActor()
    {
        return actor;
    }

    @Override
    public void setActor(A actor)
    {
        this.actor = actor;
    }
}
