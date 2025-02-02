package actions;

import entities.Entity;
import utils.Coordinates;
import utils.GamePlace;


public abstract class Action {

    public abstract void perform(GamePlace gamePlace);

     public void addEntityCountTimes(Entity entity, int countEntity, GamePlace gamePlace) {
        while (countEntity > 0) {
            int x = (int) (Math.random() * gamePlace.getSizeX());
            int y = (int) (Math.random() * gamePlace.getSizeY());
            if (!gamePlace.containsEntity(new Coordinates(x, y))) {
                if (gamePlace.isHaveFreePlaceOnMap()) {
                    gamePlace.putEntity(new Coordinates(x, y), entity);
                    countEntity--;
                }
            }
        }
    }

}
