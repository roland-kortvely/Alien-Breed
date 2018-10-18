package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.util.Random;

public class DefectiveLight extends Light {

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);

        new Loop<>(new Invoke(this::defective)).scheduleOn(this);
    }

    private void defective()
    {
        Random random = new Random();

        if ((random.nextInt((20 - 1) + 1) + 1) == 1) {
            this.toggle();
        }
    }
}
