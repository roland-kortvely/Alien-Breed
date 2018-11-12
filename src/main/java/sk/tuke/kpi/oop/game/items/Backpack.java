package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer {

    @Override
    public int getCapacity()
    {
        return 0;
    }

    @NotNull
    @Override
    public List getContent()
    {
        return null;
    }

    @NotNull
    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public int getSize()
    {
        return 0;
    }

    @Override
    public void add(@NotNull Actor actor)
    {

    }

    @Nullable
    @Override
    public Actor peek()
    {
        return null;
    }

    @Override
    public void remove(@NotNull Actor actor)
    {

    }

    @Override
    public void shift()
    {

    }

    @NotNull
    @Override
    public Iterator iterator()
    {
        return null;
    }
}
