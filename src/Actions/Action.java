package Actions;

import Entitys.Entity;
import Utils.Coordinates;
import Utils.Map;


public abstract class Action {

     public void putEntityCountTimes(Entity entity, int countEntity, Map map) {
        while (countEntity > 0) {
            int x = (int) (Math.random() * map.getSizeX());
            int y = (int) (Math.random() * map.getSizeY());
            if (!map.containsEntity(new Coordinates(x, y))) {
                if (map.isHaveFreePlaceOnMap()) {
                    map.putEntity(new Coordinates(x, y), entity);
                    countEntity--;
                }
            }
        }
    }

}
