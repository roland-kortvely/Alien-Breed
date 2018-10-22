package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool<T> extends AbstractActor implements Usable<T> {

    private int remainingUses;

    public BreakableTool(int uses)
    {
        this.setRemainingUses(uses);
    }

    @Override
    public void useWith(T actor)
    {
        Scene scene = this.getScene();

        if (this.getRemainingUses() <= 0) {
            if (scene != null) {
                scene.removeActor(this);
            }
            return;
        }

        this.setRemainingUses(this.getRemainingUses() - 1);

        if (this.getRemainingUses() <= 0) {
            if (scene != null) {
                scene.removeActor(this);
            }
        }
    }

    public int getRemainingUses()
    {
        return remainingUses;
    }

    public void setRemainingUses(int remainingUses)
    {
        this.remainingUses = remainingUses;
    }
}
