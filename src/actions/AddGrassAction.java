package actions;

import entities.*;
import utils.GamePlace;
import utils.SimulationParameters;


public class AddGrassAction extends Action {

    @Override
    public void perform(GamePlace gamePlace) {
        addGrass(gamePlace);
    }

    private void addGrass(GamePlace gamePlace) {
        SimulationParameters parameters = new SimulationParameters();
        int maximumGrassLimit = parameters.getMaxGrassLimit();
        int minimumGrassOnMap = parameters.getMiniGrassLimit();

        if (minimumGrassOnMap > gamePlace.getGrassCount()) {
            int needToAddGrass = maximumGrassLimit - gamePlace.getGrassCount();

            Grass newGras = new Grass();
            addEntityCountTimes(newGras, needToAddGrass, gamePlace);
        }
    }

}
