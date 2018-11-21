/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The type Backpack.
 *
 * @param <T> the type parameter
 */
public class Backpack<T extends Collectible> implements ActorContainer<T> {

    private List<T> content;

    private String name;

    private int capacity;

    /**
     * Instantiates a new Backpack.
     *
     * @param name     the name
     * @param capacity the capacity
     */
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
    public List<T> getContent()
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
    public void add(@NotNull T actor)
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
    public Iterator<T> iterator()
    {
        return this.content.iterator();
    }

    @Nullable
    @Override
    public T peek()
    {
        return this.content.get(this.content.size() - 1);
    }

    @Override
    public void shift()
    {
        if (this.content.size() == 0) {
            return;
        }

        Collections.rotate(this.content, -1);
    }
}
