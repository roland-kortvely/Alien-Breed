/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.behaviours;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Gameplay;

import java.util.function.Predicate;

/**
 * The type Observing.
 *
 * @param <T> the type parameter
 * @param <A> the type parameter
 */
public class Observing<T, A extends Actor> implements Behaviour<A> {

    private A actor;

    private Topic<T>     topic;
    private Predicate<T> predicate;
    private Behaviour<A> delegate;


    /**
     * Instantiates a new Observing.
     *
     * @param topic     the topic
     * @param predicate the predicate
     * @param delegate  the delegate
     */
    public Observing(Topic<T> topic, Predicate<T> predicate, Behaviour<A> delegate)
    {
        this.setTopic(topic);
        this.setPredicate(predicate);
        this.setDelegate(delegate);
    }

    @Override
    public void setUp(A actor)
    {
        if (actor == null) {
            return;
        }

        this.setActor(actor);

        Scene scene = Gameplay.getScene();

        if (this.getTopic() == null) {
            return;
        }

        if (this.getPredicate() == null) {
            return;
        }

        if (this.getDelegate() == null) {
            return;
        }

        scene.getMessageBus().subscribe(topic, (e) -> {
            if (this.getPredicate().test(e)) {
                this.getDelegate().setUp(this.getActor());
            }
        });
    }

    @Contract(pure = true)
    private A getActor()
    {
        return actor;
    }

    private void setActor(A actor)
    {
        this.actor = actor;
    }

    @Contract(pure = true)
    private Topic<T> getTopic()
    {
        return topic;
    }

    private void setTopic(Topic<T> topic)
    {
        this.topic = topic;
    }

    @Contract(pure = true)
    private Predicate<T> getPredicate()
    {
        return predicate;
    }

    private void setPredicate(Predicate<T> predicate)
    {
        this.predicate = predicate;
    }

    @Contract(pure = true)
    private Behaviour<A> getDelegate()
    {
        return delegate;
    }

    private void setDelegate(Behaviour<A> delegate)
    {
        this.delegate = delegate;
    }
}
