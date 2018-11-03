package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool<T extends AbstractActor> extends AbstractActor implements Usable<T> {

    private int remainingUses;

    public BreakableTool(int uses)
    {
        this.setRemainingUses(uses);
    }

    @Override
    public void useWith(T actor)
    {
        if (this.getRemainingUses() <= 0) {
            return;
        }

        this.setRemainingUses(this.getRemainingUses() - 1);
    }

    public int getRemainingUses()
    {
        Scene scene = this.getScene();

        if (this.remainingUses <= 0 && scene != null) {
            scene.removeActor(this);
        }

        return remainingUses;
    }

    public void setRemainingUses(int remainingUses)
    {
        Scene scene = this.getScene();

        this.remainingUses = remainingUses;

        if (this.remainingUses <= 0 && scene != null) {
            scene.removeActor(this);
        }
    }
}
