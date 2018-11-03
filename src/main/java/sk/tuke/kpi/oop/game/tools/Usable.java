package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

public interface Usable<T extends AbstractActor> {

    void useWith(T actor);
}
