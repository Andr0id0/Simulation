package Utils;

import Entitys.Creature;
import Entitys.Entity;
import Entitys.Grass;
import Entitys.Herbivore;
import java.util.HashMap;


public class Map {

    public HashMap<Coordinates, Entity> map;

    SimulationParameters parameters = new SimulationParameters();

    private final int xSize = parameters.getMapSizeX();
    private final int ySize = parameters.getMapSizeY();


    public int getGrassCount() {
        int grassCount = 0;
        for (Coordinates coordinates : map.keySet()) {
            if (getEntity(coordinates) instanceof Grass) {
                grassCount++;
            }
        }
        return grassCount;
    }

    public boolean isHaveFreePlaceOnMap() {
        int square = xSize * ySize;
        for (Coordinates c : map.keySet()) {
            square--;
        }
        return square != 0;
    }

    public boolean containsEntity(Coordinates coordinates) {
        return map.containsKey(coordinates);
    }

    public void deleteEntity(Coordinates coordinates) {
        map.remove(coordinates);
    }

    public void putEntity(Coordinates coordinates, Entity entity) {
        map.put(coordinates, entity);
    }

    public void moveEntity(Coordinates from, Coordinates to, Entity entity) {
        map.remove(from);
        map.put(to, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public boolean isCreature(Coordinates coordinates) {
        return getEntity(coordinates) instanceof Creature;
    }

    public Creature getCreature(Coordinates coordinates) {
        return (Creature) getEntity(coordinates);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !map.containsKey(coordinates);
    }

    public boolean isCordsInMapArea(Coordinates coordinates) {
        return (coordinates.x() >= 0 && coordinates.x() < xSize
                && coordinates.y() >= 0 &&  coordinates.y() < ySize);
    }

    public boolean isGrassOrVoid(Coordinates coordinates) {
        if (getEntity(coordinates) == null) {
            return true;
        }
        return getEntity(coordinates) instanceof Grass;
    }

    public boolean isHerbivoreOrVoid(Coordinates coordinates) {
        if (getEntity(coordinates) == null) {
            return true;
        }
        return map.get(coordinates) instanceof Herbivore;
    }

    public Map() {
        this.map = new HashMap<>();
    }

    public int getSizeY() {
        return ySize;
    }

    public int getSizeX() {
        return xSize;
    }

}
