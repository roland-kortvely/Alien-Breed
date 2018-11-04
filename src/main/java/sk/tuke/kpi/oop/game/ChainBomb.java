package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ChainBomb extends TimeBomb {

    public ChainBomb(float time)
    {
        super(time);
    }

    @Override
    public void detonate()
    {
        Scene scene = this.getScene();

        if (scene == null) {
            return;
        }

        Ellipse2D.Float ellipse = new Ellipse2D.Float(this.getPosX() - 25, this.getPosY() - 25, 50, 50);

        for (Actor actor : scene.getActors()) {
            if (actor.getClass() == ChainBomb.class && !this.equals(actor)) {

                Rectangle2D.Float rectangle = new Rectangle2D.Float(actor.getPosX() - 25, actor.getPosY() - 25, 50, 50);

                if (ellipse.intersects(rectangle)) {
                    ((ChainBomb) actor).activate();
                }
            }
        }

        super.detonate();
    }
}
