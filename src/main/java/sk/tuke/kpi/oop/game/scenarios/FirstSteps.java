package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Energy;

public class FirstSteps implements SceneListener {

    private Ripley ripley;

    @Override
    public void sceneInitialized(@NotNull Scene scene)
    {
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
            new Invoke<>(() -> energy.useWith(this.getRipley()))
        ).scheduleOn(this.getRipley());
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene)
    {
        Overlay overlay = scene.getGame().getOverlay();

        overlay.drawText("| ENERGY: " + this.getRipley().getEnergy(), 100, scene.getGame().getWindowSetup().getHeight() - GameApplication.STATUS_LINE_OFFSET);
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
