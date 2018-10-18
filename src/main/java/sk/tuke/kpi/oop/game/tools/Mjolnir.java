package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Mjolnir extends BreakableTool {

    public Mjolnir()
    {
        super(4);
        setAnimation(new Animation("sprites/hammer.png", 16, 16));
    }
}
