package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.scenarios.FirstSteps;

public class Main {

    public static void main(String[] args)
    {
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);

        Game game = new GameApplication(windowSetup);

        Scene scene = new World("world");
        //Scene scene = new InspectableScene(new World("world"), List.of("sk.tuke.kpi"));

        scene.getInput().onKeyPressed(key -> {
            if (key == Input.Key.ESCAPE) {
                scene.getGame().stop();
            }
        });

        scene.addListener(new FirstSteps());

        game.addScene(scene);

        game.start();
    }
}
