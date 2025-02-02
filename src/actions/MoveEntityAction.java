package actions;

import entities.Creature;
import utils.Coordinates;
import utils.GamePlace;
import java.util.ArrayList;
import java.util.List;


public class MoveEntityAction extends Action {

    @Override
    public void perform(GamePlace gamePlace)  {
        moveEntity(gamePlace);
    }

    ///  я так понимаю что если просто итерироваться по HashMap то допустим после хода существа его координаты поменяются
    ///  а так как координаты являются ключом HashMap то измениться хэшкод и уже походившее существо может оказаться в позиции правее чем было до этого
    ///  и получается оно может походить несколько раз, а какое-то существо наоборот не походит
    ///  я создал лист из координат объектов которые должны выполнить метод makeMove
    ///  но перед этим всё равно проверяю еть ли там существо (вдруг во время хода предыдущего существа (Predator)  существо уже было убито (Herbivore))
    public void moveEntity(GamePlace gamePlace) {
        List<Coordinates> creaturesCoordinates = new ArrayList<>();
        for (Coordinates coord : gamePlace.getAllEntitiesCoordinates()) {
            if (gamePlace.isCreature(coord)) {
                creaturesCoordinates.add(coord);
            }
        }

        for (Coordinates coord : creaturesCoordinates) {
            if (gamePlace.isCreature(coord)) {
                Creature creature = gamePlace.getCreature(coord);
                if (creature.makeMove(coord, gamePlace)) {

                }
            }
        }
    }

}
