package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Map;

public class KeyboardController<T extends Movable> implements KeyboardListener {

    private Movable actor;

    private Move action;

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.LEFT, Direction.WEST),

        Map.entry(Input.Key.W, Direction.NORTH),
        Map.entry(Input.Key.S, Direction.SOUTH),
        Map.entry(Input.Key.D, Direction.EAST),
        Map.entry(Input.Key.A, Direction.WEST)
    );

    public KeyboardController()
    {
        this(null);
    }

    public KeyboardController(Movable actor)
    {
        if (actor == null) {
            return;
        }

        this.setActor(actor);
    }

    @Override
    public void keyPressed(@NotNull Input.Key key)
    {
        if (this.getActor() == null) {
            return;
        }

        if (!this.getKeyDirectionMap().containsKey(key)) {
            return;
        }

        if (this.getAction() != null) {
            this.getAction().stop();
        }

        this.setAction(new Move<T>(this.getKeyDirectionMap().get(key), 2));
        this.getAction().scheduleOn(this.getActor());
    }

    @Override
    public void keyReleased(@NotNull Input.Key key)
    {
        if (this.getActor() == null) {
            return;
        }

        if (!this.getKeyDirectionMap().containsKey(key)) {
            return;
        }

        if (this.getAction() != null) {
            this.getAction().stop();
        }
    }

    private Movable getActor()
    {
        return actor;
    }

    private void setActor(Movable actor)
    {
        this.actor = actor;
    }

    private Move getAction()
    {
        return action;
    }

    private void setAction(Move action)
    {
        this.action = action;
    }

    private Map<Input.Key, Direction> getKeyDirectionMap()
    {
        return keyDirectionMap;
    }
}
