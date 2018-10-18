package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool extends AbstractActor {

    private int remainingUses;

    public BreakableTool(int uses)
    {
        this.setRemainingUses(uses);
    }

    public void use()
    {
        if (this.getRemainingUses() <= 0) {
            this.getScene().removeActor(this);
            return;
        }

        this.setRemainingUses(this.getRemainingUses() - 1);

        if (this.getRemainingUses() <= 0) {
            this.getScene().removeActor(this);
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
