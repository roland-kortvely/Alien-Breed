package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.List;

public class Teleport extends AbstractActor {

    private Teleport destination;

    private boolean active;

    public Teleport()
    {
        this(null);
    }

    public Teleport(Teleport destination)
    {
        this.setActive(true);
        this.setDestination(destination);
        setAnimation(new Animation("sprites/lift.png", 48, 48));
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);
        scene.setActorRenderOrder(Player.class);
        this.teleport();
    }

    private void teleport()
    {
        if (this.getDestination() == null) {
            return;
        }

        Scene scene = this.getScene();

        if (scene == null) {
            return;
        }

        Player player = scene.getFirstActorByType(Player.class);
        if (player == null) {
            return;
        }

        new When<>(
            action -> this.intersects(player)
                && !this.isActive()
                && ((((2 * player.getPosX() + player.getWidth()) / 2) >= this.getPosX())
                && (((2 * player.getPosX() + player.getWidth()) / 2) <= this.getPosX() + this.getWidth())
                && (((2 * player.getPosY() + player.getHeight()) / 2) >= this.getPosY())
                && (((2 * player.getPosY() + player.getHeight()) / 2) <= this.getPosY() + this.getHeight())
            ),
            new Invoke(() -> {
                destination.teleportPlayer(player);
                this.teleport();
                destination.teleport();
            })
        ).scheduleOn(scene);

        new When<>(
            action -> !this.intersects(player)
                && !((((2 * player.getPosX() + player.getWidth()) / 2) >= this.getPosX())
                && (((2 * player.getPosX() + player.getWidth()) / 2) <= this.getPosX() + this.getWidth())
                && (((2 * player.getPosY() + player.getHeight()) / 2) >= this.getPosY())
                && (((2 * player.getPosY() + player.getHeight()) / 2) <= this.getPosY() + this.getHeight())
            ),
            new Invoke(() -> this.setActive(false))
        ).scheduleOn(scene);
    }

    public void teleportPlayer(Player player)
    {
        if (player == null) {
            return;
        }

        if (this.getDestination() == null) {
            return;
        }

        this.setActive(true);

        player.setPosition(
            ((2 * this.getPosX() + this.getWidth()) / 2) - (player.getWidth() / 2),
            ((2 * this.getPosY() + this.getHeight()) / 2) - (player.getHeight() / 2)
        );
    }

    public Teleport getDestination()
    {
        return destination;
    }

    public void setDestination(Teleport destination)
    {
        if (this.equals(destination)) {
            return;
        }

        this.destination = destination;
    }

    @Contract(pure = true)
    private boolean isActive()
    {
        return active;
    }

    private void setActive(boolean active)
    {
        this.active = active;
    }
}
