/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.ActorContainer;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;

import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.items.Backpack;

public class Ripley extends AbstractActor implements Movable, Keeper {

    private Animation normalAnimation;

    private int speed;
    private int energy;
    private int ammo;

    private Backpack backpack;

    public Ripley()
    {
        //Override Actor's name
        super("Ellen");

        this.setNormalAnimation(new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.getNormalAnimation().stop();

        //Default stats
        this.setSpeed(2);
        this.setEnergy(50);
        this.setAmmo(450);
        this.setBackpack(new Backpack("Ripley's backpack", 10));

        //Initialize animation
        setAnimation(this.getNormalAnimation());
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        //Render stats
        this.showRipleyState();
    }

    private void showRipleyState()
    {
        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }

        int topLine = scene.getGame().getWindowSetup().getHeight() - GameApplication.STATUS_LINE_OFFSET;

        Overlay overlay = scene.getGame().getOverlay();

        //Redraw stats on every frame
        new Loop<>(
            new Invoke<>(() -> {
                overlay.drawText(" | ENERGY: " + this.getEnergy(), 100, topLine);
                overlay.drawText(" | AMMO: " + this.getAmmo(), 250, topLine);
            })
        ).scheduleOn(scene);
    }

    @Override
    public int getSpeed()
    {
        return this.speed;
    }

    private void setSpeed(int speed)
    {
        this.speed = speed;
    }

    @Override
    public void startedMoving(Direction direction)
    {
        //Rotate animation, and play it
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving()
    {
        getAnimation().stop();
    }

    @Contract(pure = true)
    private Animation getNormalAnimation()
    {
        return normalAnimation;
    }

    private void setNormalAnimation(Animation normalAnimation)
    {
        this.normalAnimation = normalAnimation;
    }

    public int getEnergy()
    {
        return energy;
    }

    @Contract(pure = true)
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    public int getAmmo()
    {
        return ammo;
    }

    @Contract(pure = true)
    public void setAmmo(int ammo)
    {
        this.ammo = ammo;
    }

    public void increaseAmmo(int ammo)
    {
        this.ammo = (this.ammo + ammo) > 500 ? 500 : (this.ammo + ammo);
    }

    @Contract(pure = true)
    private Backpack getBackpack()
    {
        return backpack;
    }

    private void setBackpack(Backpack backpack)
    {
        this.backpack = backpack;
    }

    @Override
    public ActorContainer getContainer()
    {
        return this.getBackpack();
    }
}
