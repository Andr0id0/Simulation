import java.util.*;

public class Simulation {

    Map map;

    int moveCounter;

    MapConsoleRenderer mapConsoleRenderer;

    public Simulation(Map map, MapConsoleRenderer mapConsoleRenderer, int moveCounter) {
        this.map = map;
        this.mapConsoleRenderer = mapConsoleRenderer;
        this.moveCounter = moveCounter;
    }

    public static void main(String[] args) {
        Map map = new Map(new HashMap<>());
        Simulation simulation = new Simulation(map, new MapConsoleRenderer(map), 3);
        simulation.map.initEntityInMap();
        simulation.mapConsoleRenderer.render();
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        for (Coordinates coordinates : map.map.keySet()) {
            if (map.getEntity(coordinates).getClass().getSimpleName().equals("Herbivore")) {
                Creature creature = (Creature) map.getEntity(coordinates);
                creature.makeMove(coordinates, map);
                simulation.mapConsoleRenderer.render();
                break;
            }
        }



    }

}


