/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.gamelib.messages.MessageBus;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Usable<Actor>, Openable {

    private boolean open;

    private MapTile[] mapTiles;

    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    public Door()
    {
        setAnimation(new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE_REVERSED));

        this.close();
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        this.mapTiles = new MapTile[]{
            scene.getMap().getTile(this.getPosX() / 16, this.getPosY() / 16),
            scene.getMap().getTile(this.getPosX() / 16, this.getPosY() / 16 + 1),
        };

        mapTiles[0].setType(MapTile.Type.WALL);
        mapTiles[1].setType(MapTile.Type.WALL);
    }

    @Override
    public void useWith(Actor actor)
    {
        if (this.isOpen()) {
            this.close();
        } else {
            this.open();
        }
    }

    @Override
    public Class<Actor> getUsingActorClass()
    {
        return Actor.class;
    }

    @Override
    public void open()
    {
        this.setOpen(true);
        this.getAnimation().setPlayMode(Animation.PlayMode.ONCE);
        this.getAnimation().play();

        if (this.mapTiles != null) {
            mapTiles[0].setType(MapTile.Type.CLEAR);
            mapTiles[1].setType(MapTile.Type.CLEAR);
        }

        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }

        scene.getMessageBus().publish(DOOR_OPENED, this);
    }

    @Override
    public void close()
    {
        this.setOpen(false);
        this.getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        this.getAnimation().play();

        if (this.mapTiles != null) {
            mapTiles[0].setType(MapTile.Type.WALL);
            mapTiles[1].setType(MapTile.Type.WALL);
        }

        Scene scene = this.getScene();
        if (scene == null) {
            return;
        }

        scene.getMessageBus().publish(DOOR_CLOSED, this);
    }

    @Override
    public boolean isOpen()
    {
        return this.open;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }
}
