package entities;

import utils.Coordinates;
import utils.GamePlace;
import utils.SimulationParameters;


public class Herbivore extends Creature {

    final String herbivoreTarget = "Grass";

    public Herbivore(int speed, int health) {
        super(speed, health);
        target = herbivoreTarget;
    }

    @Override
    void entityAction(Coordinates coordinates, GamePlace gamePlace) {
        eat(coordinates, gamePlace);
    }

    @Override
    void performActionTwo(Coordinates coordinates, GamePlace gamePlace) {
        heal(coordinates, gamePlace);
    }

    @Override
    boolean isTargetOrVoid(Coordinates coordinates, GamePlace gamePlace) {
        return gamePlace.isGrassOrVoid(coordinates);
    }

    public void eat(Coordinates coordinates, GamePlace gamePlace) {
        gamePlace.deleteEntity(coordinates);

    }

    public void heal(Coordinates coordinates, GamePlace gamePlace) {
        SimulationParameters parameters = new SimulationParameters();
        int healAfterEat = parameters.getHerbivoreHealAfterEat();
        int herbivoreMaxHp = parameters.getHerbivoreHp();
        Herbivore herbivore = (Herbivore) gamePlace.getCreature(coordinates);
        int newHp = herbivore.getHealth();
        if (isNeedToHeal(herbivore, herbivoreMaxHp, healAfterEat)) {
            newHp += healAfterEat;
        }
        Herbivore herbivoreAfterHeal = new Herbivore(herbivore.getSpeed(), newHp);
        gamePlace.putEntity(coordinates, herbivoreAfterHeal);
    }

    private boolean isNeedToHeal(Herbivore herbivore, int herbivoreMaxHp, int healAfterEat) {
        return herbivore.getHealth() < herbivoreMaxHp - healAfterEat;
    }

}
