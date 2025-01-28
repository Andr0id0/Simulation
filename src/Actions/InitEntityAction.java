package Actions;

import Entitys.Herbivore;
import Entitys.*;
import Utils.Map;
import Utils.SimulationParameters;


public class InitEntityAction extends Action {

    public void initEntity(Map map) {
        SimulationParameters parameters = new SimulationParameters();
        putEntityCountTimes(new Rock(), parameters.getRockCount(), map);
        putEntityCountTimes(new Tree(), parameters.getTreeCount(), map);
        putEntityCountTimes(new Grass(), parameters.getGrassCount(), map);
        putEntityCountTimes(new Herbivore(parameters.getHerbivoreSpeed() ,parameters.getHerbivoreHp()), parameters.getHerbivoreCount(), map);
        putEntityCountTimes(new Predator(parameters.getPredatorSpeed(), parameters.getPredatorHp(), parameters.getPredatorDamage()), parameters.getPredatorCount(), map);
    }

}
