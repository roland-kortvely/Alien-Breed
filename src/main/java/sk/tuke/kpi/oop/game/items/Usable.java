package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;

public interface Usable<U> extends Actor {

    void useWith(U actor);
}
