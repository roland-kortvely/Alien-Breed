/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

/**
 * The type Ripley.
 */
public class Ripley extends AbstractActor implements Player, Alive, Movable, Armed, Keeper<Collectible> {

    private Animation normalAnimation;
    private Animation dieAnimation;

    private Backpack backpack;

    private Health health;

    private Firearm firearm;

    /**
     * The constant RIPLEY_DIED.
     */
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);

    /**
     * Instantiates a new Ripley.
     */
    public Ripley()
    {
        //Override Actor's name
        super("Ellen");

        this.setNormalAnimation(new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.getNormalAnimation().stop();

        this.setDieAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));

        //Default stats
        this.setHealth(new Health(100));
        this.setBackpack(new Backpack("Ripley's backpack", 10));
        this.setFirearm(new Gun(0, 500));

        //No Health -> Die
        this.getHealth().onExhaustion(this::die);

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

    /**
     * Die.
     */
    private void die()
    {
        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }

        setAnimation(this.getDieAnimation());

        scene.cancelActions(this);
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
                overlay.drawText(" | HEALTH: " + this.getHealth().getValue(), 100, topLine);
                overlay.drawText(" | AMMO: " + this.getFirearm().getAmmo(), 250, topLine);
            })
        ).scheduleOn(scene);
    }

    @Override
    public int getSpeed()
    {
        return 2;
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
    public Backpack getContainer()
    {
        return this.getBackpack();
    }

    @Override
    public Health getHealth()
    {
        return this.health;
    }

    private void setHealth(Health health)
    {
        this.health = health;
    }

    @Override
    public Firearm getFirearm()
    {
        return this.firearm;
    }

    @Override
    public void setFirearm(Firearm weapon)
    {
        this.firearm = weapon;
    }
}
