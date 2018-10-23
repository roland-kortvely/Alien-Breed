package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.util.Random;

public class DefectiveLight extends Light implements Repairable {

    private Disposable instance;

    private void defect()
    {
        System.out.println("Defective..");
        this.instance = new Loop<>(new Invoke(this::defective)).scheduleOn(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);
        this.defect();
    }

    private void defective()
    {
        Random random = new Random();

        if ((random.nextInt((20 - 1) + 1) + 1) == 1) {
            this.toggle();
        }
    }

    @Override
    public boolean repair()
    {
        if (this.instance == null) {
            return false;
        }

        this.instance.dispose();
        this.turnOn();

        new ActionSequence<>(new Wait(4), new Invoke(this::defect));

        return true;
    }
}
