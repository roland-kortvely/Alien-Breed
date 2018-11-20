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
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;

public class Ripley extends AbstractActor implements Movable, Keeper<Collectible> {

    private Animation normalAnimation;
    private Animation dieAnimation;

    private int speed;
    private int energy;
    private int ammo;

    private Backpack backpack;

    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);

    public Ripley()
    {
        //Override Actor's name
        super("Ellen");

        this.setNormalAnimation(new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.getNormalAnimation().stop();

        this.setDieAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));

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

    private void die()
    {
        setAnimation(this.getDieAnimation());

        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }

        scene.getMessageBus().publish(RIPLEY_DIED, this);
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

    @Contract(pure = true)
    private Animation getDieAnimation()
    {
        return dieAnimation;
    }

    private void setDieAnimation(Animation dieAnimation)
    {
        this.dieAnimation = dieAnimation;
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

    public void decreaseEnergy(int energy)
    {
        this.energy = (this.energy - energy) < 0 ? 0 : (this.energy - energy);

        if (this.energy <= 0) {
            this.die();
        }
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
