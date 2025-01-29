package Entitys;

import Utils.Coordinates;
import Utils.Map;
import Utils.SimulationParameters;


public class Herbivore extends Creature {

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
        System.out.println("Скушали растение на координатах x:" + coordinates.x() + " y:" + coordinates.y());
    }

    public void heal(Coordinates coordinates, Map map) {
        SimulationParameters parameters = new SimulationParameters();
        int healAfterEat = parameters.getHerbivoreHealAfterEat();
        Herbivore herbivore = (Herbivore) map.getCreature(coordinates);
        int newHp = herbivore.getHealth();
        boolean isHerbivoreHeal = false;
        if (herbivore.getHealth() < parameters.getHerbivoreHp() - healAfterEat) {
            newHp += healAfterEat;
            System.out.println("Herbivore x:" + coordinates.x() + " y:" + coordinates.y() + " полечилась на: "  + healAfterEat + " текущее hp " + newHp);
            isHerbivoreHeal = true;
        }
        Herbivore herbivoreAfterHeal = new Herbivore(herbivore.getSpeed(), newHp);
        map.putEntity(coordinates, herbivoreAfterHeal);
        if (!isHerbivoreHeal) System.out.println("Herbivore x:" + coordinates.x() + " y:" + coordinates.y() + " hp:" + newHp);
    }

    public Herbivore(int speed, int health) {
        this.speed = speed;
        this.health = health;
        this.target = herbivoreTarget;
    }

}
