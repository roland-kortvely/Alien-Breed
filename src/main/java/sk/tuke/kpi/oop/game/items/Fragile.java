/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.oop.game.characters.Alive;

public interface Fragile extends Alive {

    void explode();
}
