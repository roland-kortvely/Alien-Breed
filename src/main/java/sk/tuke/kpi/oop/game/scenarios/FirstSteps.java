package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.KeyboardController;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class FirstSteps implements SceneListener {

    @Override
    public void sceneInitialized(@NotNull Scene scene)
    {

        Ripley ripley = new Ripley();

        scene.addActor(ripley, 0, 0);

        KeyboardController keyboardController = new KeyboardController<Ripley>(ripley);

        scene.getInput().registerListener(keyboardController);
    }
}
