package Entitys;

import Utils.Coordinates;
import Utils.Map;
import Utils.SimulationParameters;


public class Herbivore extends Creature {

    int healAfterEat;
    final String herbivoreTarget = "Grass";

    @Override
    void performAction(Coordinates coordinates, Map map) {
        eat(coordinates, map);
    }

    @Override
    void performActionTwo(Coordinates coordinates, Map map) {
        heal(coordinates, map);
    }

    @Override
    boolean isTargetOrVoid(Coordinates coordinates, Map map) {
        return map.isGrassOrVoid(coordinates);
    }

    public void eat(Coordinates coordinates, Map map) {
        map.deleteEntity(coordinates);
        System.out.println("растение скушали + x:" + coordinates.x() + " y:" + coordinates.y());
    }

    public void heal(Coordinates coordinates, Map map) {
        SimulationParameters parameters = new SimulationParameters();
        Herbivore herbivore = (Herbivore) map.getCreature(coordinates);
        int newHp = herbivore.getHealth();
        if (herbivore.getHealth() < parameters.getHerbivoreHealAfterEat()) {
            newHp += healAfterEat;
            System.out.println("Коза x:" + coordinates.x() + " y:" + coordinates.y() + " восстановила здоровье на: " + healAfterEat + " текущее здоровье: " + newHp);
        }
        herbivore.setHealth(newHp);
        map.putEntity(coordinates, herbivore);
    }

    public Herbivore(int speed, int health) {
        this.speed = speed;
        this.health = health;
        this.target = herbivoreTarget;
    }

}
