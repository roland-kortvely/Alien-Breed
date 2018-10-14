package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {

    private int uses;
    private Animation animation;

    public Hammer()
    {
        this.uses = 1;
        this.animation = new Animation("sprites/hammer.png", 16, 16);

        setAnimation(this.animation);
    }

    public int getUses()
    {
        return uses;
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
