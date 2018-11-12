package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.items.Collectible;

public class CollectorController<A extends Keeper> implements KeyboardListener {

    private A actor;

    public CollectorController(A actor)
    {
        this.setActor(actor);
    }

    @Override
    public void keyPressed(@NotNull Input.Key key)
    {
        if (this.getActor() == null) {
            return;
        }

        switch (key) {
            case ENTER:
                new Take<>(Collectible.class).scheduleOn(this.getActor());
                break;
            case BACKSPACE:
                new Drop<>().scheduleOn(this.getActor());
                break;
            case Q:
                new Shift<>().scheduleOn(this.getActor());
                break;
        }
    }

    @Contract(pure = true)
    private A getActor()
    {
        return actor;
    }

    private void setActor(A actor)
    {
        this.actor = actor;
    }

}
