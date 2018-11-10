package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KeyboardController<T extends Movable> implements KeyboardListener {

    private Movable actor;

    private Move<T> action;

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

    private Set<Direction> keys;

    public KeyboardController()
    {
        this(null);
    }

    public KeyboardController(Movable actor)
    {
        if (actor == null) {
            return;
        }

        this.setKeys(new HashSet<>());
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

        this.getKeys().add(this.getKeyDirectionMap().get(key));

        this.update();
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

        this.getKeys().remove(this.getKeyDirectionMap().get(key));

        this.update();
    }

    private Direction direction()
    {
        Direction direction = Direction.NONE;

        for (Direction dir : this.getKeys()) {
            direction = direction.combine(dir);
        }

        return direction;
    }

    private void update()
    {
        if (this.getActor() == null) {
            return;
        }

        if (this.getAction() != null) {
            this.getAction().stop();
        }

        if (this.direction() != Direction.NONE) {
            this.setAction(new Move<>(this.direction(), 2));
            this.getAction().scheduleOn(this.getActor());
        } else {
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

    public void setAction(Move<T> action)
    {
        this.action = action;
    }

    private Map<Input.Key, Direction> getKeyDirectionMap()
    {
        return keyDirectionMap;
    }

    private void setKeyDirectionMap(Map<Input.Key, Direction> keyDirectionMap)
    {
        this.keyDirectionMap = keyDirectionMap;
    }

    private Set<Direction> getKeys()
    {
        return keys;
    }

    private void setKeys(Set<Direction> keys)
    {
        this.keys = keys;
    }
}
