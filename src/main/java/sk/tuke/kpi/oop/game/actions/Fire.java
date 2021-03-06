/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Gameplay;
import sk.tuke.kpi.oop.game.interfaces.Armed;
import sk.tuke.kpi.oop.game.interfaces.Fireable;

/**
 * The type Fire.
 *
 * @param <A> the type parameter
 */
public class Fire<A extends Armed> extends AbstractAction<A> {

    @Override
    public void execute(float deltaTime)
    {
        if (this.getActor() == null) {
            this.setDone(true);
            return;
        }

        Scene scene = Gameplay.getScene();

        Fireable fireable = this.getActor().getFirearm().fire();
        if (fireable == null) {
            this.setDone(true);
            return;
        }

        Direction direction = Direction.fromAngle(this.getActor().getAnimation().getRotation());

        scene.addActor(fireable, this.getActor().getPosX() + (this.getActor().getWidth() * direction.getDx()), this.getActor().getPosY() + (this.getActor().getHeight() * direction.getDy()));

        new Move<>(direction, Integer.MAX_VALUE).scheduleOn(fireable);

        this.setDone(true);
    }
}
