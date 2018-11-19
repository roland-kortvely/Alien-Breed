/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.scenarios.MissionImpossible;

/**
 * Starting point of App
 */
public class Main {

    private static Game game;

    //Initial program Game input
    public static void main(String[] args)
    {
        //Setup Game, window size, name, ...
        setGame(new GameApplication(new WindowSetup("Project Ellen", 800, 600)));

        //TODO:: Move away
        //-------------------------------------------------------------------------------------------------------------v
        Scene scene = new World("world", "maps/mission-impossible.tmx", new MissionImpossible.Factory());

        //Exit game after pressing ESC
        scene.getInput().onKeyPressed(key -> {
            if (key == Input.Key.ESCAPE) {
                scene.getGame().stop();
            }
        });

        scene.addListener(new MissionImpossible());
        //-------------------------------------------------------------------------------------------------------------^

        //Load instantiated scene
        getGame().addScene(scene);

        //Execute game
        getGame().start();
    }

    @Contract(pure = true)
    public static Game getGame()
    {
        return game;
    }

    public static void setGame(Game game)
    {
        Main.game = game;
    }
}
