package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {

    private List<Collectible> content;

    private String name;

    private int capacity;

    public Backpack(String name, int capacity)
    {
        this.content = new ArrayList<>();

        this.setName(name);
        this.setCapacity(capacity);
    }

    @Override
    public int getCapacity()
    {
        return this.capacity;
    }

    private void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    @NotNull
    @Override
    public List<Collectible> getContent()
    {
        return new ArrayList<>(this.content);
    }

    @NotNull
    @Override
    public String getName()
    {
        return this.name;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int getSize()
    {
        return this.content.size();
    }

    @Override
    public void add(@NotNull Collectible actor)
    {
        if (this.getSize() >= this.getCapacity()) {
            throw new IllegalStateException(this.getName() + " is full");
        }

        this.content.add(actor);
    }

    @Override
    public void remove(@NotNull Collectible actor)
    {
        this.content.remove(actor);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator()
    {
        return this.content.iterator();
    }

    @Nullable
    @Override
    public Collectible peek()
    {
        return this.content.get(this.content.size() - 1);
    }

    @Override
    public void shift()
    {
        this.content.add(this.content.remove(0));
    }
}
