package actions;

import entities.Herbivore;
import entities.*;
import utils.GamePlace;
import utils.SimulationParameters;


public class InitEntityAction extends Action {

    @Override
    public void perform(GamePlace gamePlace) {
        initEntity(gamePlace);
    }

    private void initEntity(GamePlace gamePlace) {
        SimulationParameters parameters = new SimulationParameters();
        addEntityCountTimes(new Rock(), parameters.getRockCount(), gamePlace);
        addEntityCountTimes(new Tree(), parameters.getTreeCount(), gamePlace);
        addEntityCountTimes(new Grass(), parameters.getGrassCount(), gamePlace);
        addEntityCountTimes(new Herbivore(parameters.getHerbivoreSpeed() ,parameters.getHerbivoreHp()), parameters.getHerbivoreCount(), gamePlace);
        addEntityCountTimes(new Predator(parameters.getPredatorSpeed(), parameters.getPredatorHp(), parameters.getPredatorDamage()), parameters.getPredatorCount(), gamePlace);
    }

}
