package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends AbstractActor {

    private int uses;

    public FireExtinguisher()
    {
        this.setUses(1);
        setAnimation(new Animation("sprites/extinguisher.png", 16, 16));
    }

    public int getUses()
    {
        return uses;
    }

    public void setUses(int uses)
    {
        this.uses = uses;
    }

    public void use()
    {
        if (this.getUses() <= 0) {
            this.getScene().removeActor(this);
            return;
        }

        this.uses--;

        if (this.uses <= 0) {
            this.getScene().removeActor(this);
        }
    }
}
