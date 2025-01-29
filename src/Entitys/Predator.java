package Entitys;

import Utils.Coordinates;
import Utils.Map;
import Utils.SimulationParameters;


public class Predator extends Creature {

    int damage;

    final String predatorTarget = "Herbivore";

    @Override
    void performAction(Coordinates coordinates, Map map) {
        attack(coordinates, map);
    }

    @Override
    void performActionTwo(Coordinates coordinates, Map map) {}

    @Override
    boolean isTargetOrVoid(Coordinates coordinates, Map map) {
        return map.isHerbivoreOrVoid(coordinates);
    }

    public void attack(Coordinates coordinates, Map map) {
        SimulationParameters parameters = new SimulationParameters();
        Herbivore herbivore = (Herbivore) map.getCreature(coordinates);
        int healthAfterAttack = herbivore.getHealth() - parameters.getPredatorDamage();
        if (healthAfterAttack <= 0) {
            map.deleteEntity(coordinates);
            System.out.println("Убили травоядного на координатах x:" + coordinates.x() + " y:" + coordinates.y());
            return;
        }

        System.out.println("Ранили травоядного на координатах x:" + coordinates.x() + " y:" + coordinates.y() + " hp травоядного :" + healthAfterAttack);
        Herbivore newHerbivore = new Herbivore(herbivore.getSpeed(), healthAfterAttack);
        map.putEntity(coordinates, newHerbivore);
    }

    public Predator(int speed, int health, int damage) {
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.target = predatorTarget;
    }

}
