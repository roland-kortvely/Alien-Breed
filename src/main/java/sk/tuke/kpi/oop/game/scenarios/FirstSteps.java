package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;

import java.util.List;

public class FirstSteps implements SceneListener {

    private Ripley ripley;

    private int topLine;

    @Override
    public void sceneInitialized(@NotNull Scene scene)
    {
        scene.setActorRenderOrder(List.of(Ripley.class));

        this.topLine = scene.getGame().getWindowSetup().getHeight() - GameApplication.STATUS_LINE_OFFSET;

        //Prepare player
        this.setRipley(new Ripley());
        scene.addActor(this.getRipley(), 0, 0);

        //Keyboard controller
        MovableController movableController = new MovableController<Ripley>(this.getRipley());
        scene.getInput().registerListener(movableController);

        //Energy
        Energy energy = new Energy();
        scene.addActor(energy, 50, 50);
        new When<>(
            (action) -> energy.intersects(this.getRipley()),
            new Invoke<>(() -> new Use<>(energy).scheduleOn(this.getRipley()))
        ).scheduleOn(this.getRipley());

        //Ammo
        Ammo ammo = new Ammo();
        scene.addActor(ammo, -50, 50);
        new When<>(
            (action) -> ammo.intersects(this.getRipley()),
            new Invoke<>(() -> new Use<>(ammo).scheduleOn(this.getRipley()))
        ).scheduleOn(this.getRipley());
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene)
    {
        Overlay overlay = scene.getGame().getOverlay();

        overlay.drawText(" | ENERGY: " + this.getRipley().getEnergy(), 100, this.topLine);
        overlay.drawText(" | AMMO: " + this.getRipley().getAmmo(), 250, this.topLine);
    }

    public Ripley getRipley()
    {
        return ripley;
    }

    public void setRipley(Ripley ripley)
    {
        this.ripley = ripley;
    }
}
