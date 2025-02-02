package entities;

import utils.Coordinates;
import utils.GamePlace;
import utils.SimulationParameters;


public class Predator extends Creature {

    int damage;

    final String predatorTarget = "Herbivore";

    public Predator(int speed, int health, int damage) {
        super(speed, health);
        this.damage = damage;
        target = predatorTarget;
    }

    @Override
    void entityAction(Coordinates coordinates, GamePlace gamePlace) {
        attack(coordinates, gamePlace);
    }

    @Override
    void performActionTwo(Coordinates coordinates, GamePlace gamePlace) {}

    @Override
    boolean isTargetOrVoid(Coordinates coordinates, GamePlace gamePlace) {
        return gamePlace.isHerbivoreOrVoid(coordinates);
    }

    public void attack(Coordinates coordinates, GamePlace gamePlace) {
        SimulationParameters parameters = new SimulationParameters();
        Herbivore herbivore = (Herbivore) gamePlace.getCreature(coordinates);
        int healthAfterAttack = herbivore.getHealth() - parameters.getPredatorDamage();
        if (healthAfterAttack <= 0) {
            gamePlace.deleteEntity(coordinates);
            return;
        }
        Herbivore newHerbivore = new Herbivore(herbivore.getSpeed(), healthAfterAttack);
        gamePlace.putEntity(coordinates, newHerbivore);
    }

}
