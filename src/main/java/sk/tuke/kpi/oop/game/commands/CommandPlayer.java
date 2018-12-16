/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.commands;

import sk.tuke.kpi.oop.game.characters.Ripley;

/**
 * The interface Command player.
 */
public interface CommandPlayer {

    /**
     * Execute boolean.
     *
     * @param ripley the ripley
     *
     * @return the boolean
     */
    boolean execute(Ripley ripley);
}
