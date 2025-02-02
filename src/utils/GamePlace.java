package utils;

import entities.Creature;
import entities.Entity;
import entities.Grass;
import entities.Herbivore;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class GamePlace {

    private final Map<Coordinates, Entity> entities;

    SimulationParameters simulationParameters = new SimulationParameters();

    private final int xSize = simulationParameters.getMapSizeX();
    private final int ySize = simulationParameters.getMapSizeY();

    public GamePlace() {
        this.entities = new HashMap<>();
    }

    public int getGrassCount() {
        int grassCount = 0;
        for (Coordinates coordinates : entities.keySet()) {
            if (getEntity(coordinates) instanceof Grass) {
                grassCount++;
            }
        }
        return grassCount;
    }

    public Set<Coordinates> getAllEntitiesCoordinates() {
        return entities.keySet();
    }


    public boolean isHaveFreePlaceOnMap() {
        return entities.size() < xSize * ySize;
    }

    public boolean containsEntity(Coordinates coordinates) {
        return entities.containsKey(coordinates);
    }

    public void deleteEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void putEntity(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public boolean isCreature(Coordinates coordinates) {
        return getEntity(coordinates) instanceof Creature;
    }

    public Creature getCreature(Coordinates coordinates) {
        return (Creature) getEntity(coordinates);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
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
        return entities.get(coordinates) instanceof Herbivore;
    }

    public int getSizeY() {
        return ySize;
    }

    public int getSizeX() {
        return xSize;
    }

}
