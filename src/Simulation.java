import actions.*;
import entitys.Entity;

import java.util.HashMap;

public class Simulation {


    HashMap<int[], Entity> gameMap;

    int moveCounter;


    Actions action = new Actions();
    public void nextTurn() {
        action.createClearMap(action.mapSize());
        System.out.println(action.getMap().toString());
        action.putAllEntity();
        System.out.println(action.getMap().toString());
        action.printMap();


    }

}
