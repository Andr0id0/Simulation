import java.util.HashMap;

public class Map {

    public HashMap<Coordinates, Entity> map;

    private final int length = 10;
    private final int height = 10;

    private final int TreeCount = 5;
    private final int RockCount = 5;
    private final int GrassCount = 5;
    private final int HerbivoreCount = 5;
    private final int PredatorCount = 5;


    public void deleteEntity(Coordinates coordinates) {
        map.remove(coordinates);
    }

    public void putEntity(Coordinates coordinates, Entity entity) {
        map.put(coordinates, entity);
    }

    public void moveEntity(Coordinates from, Coordinates to, Entity entity) {
        map.put(to, entity);
        map.remove(from);
    }

    public void initEntityInMap() {
        putEntityCountTimes(new Rock(), RockCount);
        putEntityCountTimes(new Tree(), TreeCount);
        putEntityCountTimes(new Grass(), GrassCount);
        putEntityCountTimes(new Herbivore(1 ,6), HerbivoreCount);
        putEntityCountTimes(new Predator(1, 1, 1), PredatorCount);
    }

    private void putEntityCountTimes(Entity entity, int countEntity) {
        while (countEntity > 0) {
            int x = (int) (Math.random() * length);
            int y = (int) (Math.random() * height);
            if (!map.containsKey(new Coordinates(x, y))) {
                map.put(new Coordinates(x, y), entity);
                countEntity--;
            }
        }
    }


    public Entity getEntity(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !map.containsKey(coordinates);
    }

    public boolean isGrassOrVoid(Coordinates coordinates) {
        if (map.get(coordinates) == null) {
            return true;
        }
        return map.get(coordinates).getClass().getSimpleName().equals("Grass");
    }

    public boolean isHerbivoreOrVoid(Coordinates coordinates) {
        if (map.get(coordinates) == null) {
            return true;
        }
        return map.get(coordinates).getClass().getSimpleName().equals("Herbivore");
    }

    public boolean isCordsInMapArea(Coordinates coordinates) {
        return (coordinates.getX() >= 0 && coordinates.getX() < length
                && coordinates.getY() >= 0 &&  coordinates.getY() < height);
    }

    public Map(HashMap<Coordinates, Entity> map) {
        this.map = map;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }
}
