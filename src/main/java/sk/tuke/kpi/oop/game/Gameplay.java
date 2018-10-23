package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;

import java.util.Map;

public class Gameplay extends Scenario {

    @Override
    public void setupPlay(@NotNull Scene scene)
    {

        Map<String, MapMarker> markers = scene.getMap().getMarkers();

        Reactor reactor = new Reactor();
        MapMarker reactorArea1 = markers.get("reactor-area-1");
        scene.addActor(reactor, reactorArea1.getPosX(), reactorArea1.getPosY());
        reactor.turnOn();

        Cooler cooler = new Cooler(reactor);
        MapMarker coolerArea1 = markers.get("cooler-area-1");
        scene.addActor(cooler, coolerArea1.getPosX(), coolerArea1.getPosY());

        DefectiveLight defectiveLight = new DefectiveLight();
        scene.addActor(defectiveLight, 120, 175);
        reactor.addDevice(defectiveLight);

        //-------------------------------------------------------------v
        new ActionSequence<>(new Wait(5), new Invoke(() -> {
            cooler.turnOn();
            //reactor.repairWith(new Hammer());
        }));
        //-------------------------------------------------------------^

        Reactor reactor2 = new Reactor();
        MapMarker reactorArea2 = markers.get("reactor-area-2");
        scene.addActor(reactor2, reactorArea2.getPosX(), reactorArea2.getPosY());

        Cooler cooler2 = new Cooler(reactor);
        MapMarker coolerArea2 = markers.get("cooler-area-2");
        scene.addActor(cooler2, coolerArea2.getPosX(), coolerArea2.getPosY());

        Cooler cooler3 = new Cooler(reactor);
        MapMarker coolerArea3 = markers.get("cooler-area-3");
        scene.addActor(cooler3, coolerArea3.getPosX(), coolerArea3.getPosY());

        Computer computer = new Computer();
        MapMarker computerArea = markers.get("computer-area");
        scene.addActor(computer, computerArea.getPosX(), computerArea.getPosY());
    }
}
