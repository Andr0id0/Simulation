package Actions;

import Entitys.*;
import Utils.Map;
import Utils.SimulationParameters;


public class AddGrassAction extends Action {

    public void addGrass(Map map) {
        SimulationParameters parameters = new SimulationParameters();
        int maximumGrassLimit = parameters.getMaxGrassLimit();
        int minimumGrassOnMap = parameters.getMiniGrassLimit();

        if (minimumGrassOnMap > map.getGrassCount()) {
            int needToAddGrass = maximumGrassLimit - map.getGrassCount();

            Grass newGras = new Grass();
            putEntityCountTimes(newGras, needToAddGrass, map);
            System.out.println("На карте выросла новая трава");
        }
    }

}
