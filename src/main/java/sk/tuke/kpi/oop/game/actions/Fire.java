/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

/**
 * The type Fire.
 *
 * @param <A> the type parameter
 */
public class Fire<A extends Armed> extends AbstractAction<A> {

    private A armed;

    @Override
    public void execute(float deltaTime)
    {
        if (this.getArmed() == null) {
            this.setDone(true);
            return;
        }

        Scene scene = this.getArmed().getScene();
        if (scene == null) {
            this.setDone(true);
            return;
        }

        Fireable fireable = this.getArmed().getFirearm().fire();
        if (fireable == null) {
            this.setDone(true);
            return;
        }

        Direction direction = Direction.NONE.fromAngle(this.getArmed().getAnimation().getRotation());

        scene.addActor(fireable, this.getArmed().getPosX() + (this.getArmed().getWidth() * direction.getDx()), this.getArmed().getPosY() + (this.getArmed().getHeight() * direction.getDy()));

        new Move<>(direction, 999).scheduleOn(fireable);

        this.setDone(true);
    }

    @NotNull
    @Override
    public Disposable scheduleOn(@NotNull A actor)
    {
        this.setArmed(actor);
        return super.scheduleOn(actor);
    }

    @Contract(pure = true)
    private A getArmed()
    {
        return armed;
    }

    private void setArmed(A armed)
    {
        this.armed = armed;
    }
}
