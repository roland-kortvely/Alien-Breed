/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

/**
 * The type Abstract command.
 *
 * @param <T> the type parameter
 */
public abstract class AbstractCommand<T extends Actor> implements Command<T> {

    private Scene scene;

    @Override
    public boolean execute(T actor)
    {
        if (actor == null) {
            return false;
        }

        this.setScene(actor.getScene());

        if (this.getScene() == null) {
            return false;
        }

        return this.command(actor, getScene());
    }

    /**
     * Command boolean.
     *
     * @param actor the actor
     * @param scene the scene
     *
     * @return the boolean
     */
    protected abstract boolean command(@NotNull T actor, @NotNull Scene scene);

    /**
     * Gets scene.
     *
     * @return the scene
     */
    @Contract(pure = true)
    private Scene getScene()
    {
        return scene;
    }

    private void setScene(Scene scene)
    {
        this.scene = scene;
    }
}
