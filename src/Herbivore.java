public class Herbivore extends Creature {

    String herbivoreTarget = "Grass";

    @Override
    void performAction(Coordinates coordinates, Map map) {
        eat(coordinates, map);
    }

    @Override
    boolean isTargetOrVoid(Coordinates coordinates, Map map) {
        return map.isGrassOrVoid(coordinates);
    }

    public void eat(Coordinates coordinates, Map map) {
        map.deleteEntity(coordinates);
        System.out.println("Кушаем растение по координатам + x:" + coordinates.getX() + " y:" + coordinates.getY());
    }

    public Herbivore(int speed, int health) {
        this.speed = speed;
        this.health = health;
        this.target = herbivoreTarget;
    }
}
