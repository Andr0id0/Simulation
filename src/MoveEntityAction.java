import java.util.ArrayList;
import java.util.List;

public class MoveEntityAction extends Action<Void> {



    ///  метод не поддерживается без передачи координат
    ///  если кто подскажет как быть в такой ситуации буду благодарен
    ///  нужно переопределять метод который не содержит нужного количества передаваемых параметров (как по мне это не красиво???)
    ///  мб есть выход из такой ситуации?
    @Override
    public Void doAction(Map map) {
        moveEntity(map);
        return null;
    }

    ///  если кто подскажет как быть в такой ситуации буду благодарен
    ///  нужно переопределять метод с без нужного (как по мне это не красиво???)
    ///  мб есть выход из такой ситуации?
    @Override
    public Void doAction() {
        return null;
    }

    ///  я так понимаю что если просто итерироваться по HashMap то допустим после хода существа его координаты поменяются
    ///  а так как координаты являются ключом HashMap то измениться хэшкод и уже походившее существо может оказаться в позиции правее чем было до этого
    ///  и получается оно может походить несколько раз, а какое-то существо наоборот не походит
    ///  я создал лист из координат объектов которые должны выполнить метод makeMove
    ///  но перед этим всё равно проверяю еть ли там существо (вдруг во время хода предыдущего существа (Predator)  существо уже было убито (Herbivore))
    private void moveEntity(Map map) {
        List<Coordinates> creaturesCoordinates = new ArrayList<>();
        for (Coordinates coord : map.map.keySet()) {
            if (map.isCreature(coord)) {
                creaturesCoordinates.add(coord);
            }
        }
        for (Coordinates coord : creaturesCoordinates) {
            if (map.isCreature(coord)) {
                Creature creature = map.getCreature(coord);
                creature.makeMove(coord, map);
            }
        }
    }

}
