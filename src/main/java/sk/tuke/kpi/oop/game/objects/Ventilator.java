/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.objects;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Gameplay;
import sk.tuke.kpi.oop.game.interfaces.Repairable;

/**
 * The type Ventilator.
 */
public class Ventilator extends AbstractActor implements Repairable {

    private boolean broken;

    /**
     * The constant VENTILATOR_REPAIRED.
     */
    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("ventilator repaired", Ventilator.class);

    /**
     * Instantiates a new Ventilator.
     */
    public Ventilator()
    {
        setAnimation(new Animation("sprites/ventilator.png", 32, 32, 0.1f, Animation.PlayMode.LOOP));
        getAnimation().stop();

        this.setBroken(true);
    }

    @Override
    public boolean repair()
    {
        if (!this.isBroken()) {
            return false;
        }

        getAnimation().play();
        this.setBroken(false);

        Scene scene = Gameplay.getScene();

        scene.getMessageBus().publish(VENTILATOR_REPAIRED, this);

        return true;
    }

    @Contract(pure = true)
    private boolean isBroken()
    {
        return broken;
    }

    private void setBroken(boolean broken)
    {
        this.broken = broken;
    }
}
