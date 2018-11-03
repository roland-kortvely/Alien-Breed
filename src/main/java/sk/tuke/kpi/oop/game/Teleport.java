package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {

    private Teleport destination;

    public Teleport()
    {
        this(null);
    }

    public Teleport(Teleport destination)
    {
        this.setDestination(destination);
        setAnimation(new Animation("sprites/lift.png", 48, 48));
    }

    public void teleportPlayer(Player player)
    {
        if (player == null) {
            return;
        }

        if (this.getDestination() == null) {
            return;
        }

        player.setPosition(this.getDestination().getPosX(), this.getDestination().getPosY());
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
}
