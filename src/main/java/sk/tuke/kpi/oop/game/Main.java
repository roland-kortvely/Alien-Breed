/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;

import sk.tuke.kpi.gamelib.*;

import sk.tuke.kpi.oop.game.scenarios.Problemset;

/**
 * Starting point of App
 */
public class Main {

    private static Game game;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        //Setup Game, window size, name, ...
        setGame(new GameApplication(new WindowSetup("Project Ellen", 800, 600)));

        //-------------------------------------------------------------------------------------------------------------v
        Scene scene = new World("world", "maps/map.tmx", new Problemset.Factory());

        //Exit game after pressing ESC
        scene.getInput().onKeyPressed(key -> {
            if (key == Input.Key.ESCAPE) {
                scene.getGame().stop();
            }
        });

        scene.addListener(new Problemset());
        //-------------------------------------------------------------------------------------------------------------^

        //Load instantiated scene
        getGame().addScene(scene);

        //Execute game
        getGame().start();
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    @Contract(pure = true)
    public static Game getGame()
    {
        return game;
    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public static void setGame(Game game)
    {
        Main.game = game;
    }
}
