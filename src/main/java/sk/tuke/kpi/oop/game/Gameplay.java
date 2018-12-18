/*
 * Copyright (c) 2018  Roland Körtvely <roland.kortvely@gmail.com>
 */

package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.scenarios.Problemset;

/**
 * The type Gameplay.
 */
public class Gameplay {

    //SINGLETON
    private static Gameplay GAMEPLAY;

    //Gamelib
    private static Game game;

    //Globalize access to Scene
    private static Scene scene;

    private Gameplay()
    {
        //Setup Game, window size, name, ...
        setGame(new GameApplication(new WindowSetup("Project Ellen", 800, 600)));

        //Preload map, instantiate actors
        Scene scene = new World("world", "maps/map.tmx", new Problemset.Factory());

        //Exit game after pressing ESC
        scene.getInput().onKeyPressed(key -> {
            if (key == Input.Key.ESCAPE) {
                scene.getGame().stop();
            }
        });

        //Listen to Scene events
        scene.addListener(new Problemset());

        //Save scene reference
        setScene(scene);

        //Load instantiated scene
        getGame().addScene(scene);

        //Execute game
        getGame().start();
    }

    /**
     * Initialize Game.
     */
    public static void initialize()
    {
        if (Gameplay.GAMEPLAY == null) {
            Gameplay.GAMEPLAY = new Gameplay();
        }
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

    private static void setGame(Game game)
    {
        Gameplay.game = game;
    }

    /**
     * Gets scene.
     *
     * @return the scene
     */
    @Contract(pure = true)
    public static Scene getScene()
    {
        return scene;
    }

    private static void setScene(@NotNull Scene scene)
    {
        Gameplay.scene = scene;
    }
}
