import java.util.*;

public abstract class Creature extends Entity {

    int speed;
    int health;

    public String target;

    abstract void performAction(Coordinates coordinates, Map map);

    abstract boolean isTargetOrVoid(Coordinates coordinates, Map map);

    public void makeMove(Coordinates from, Map map) {
        Creature creature = (Creature) map.getEntity(from);
        int speed = creature.getSpeed();
        Deque<Coordinates> coordinates = bfsToTarget(from, map);

        /// Если путь пустой, не делаем движения
        if (coordinates.isEmpty()) {
            return;
        }
        /// Проверка, что creature уже рядом с target
        Coordinates targetCoordinates = isTargetNear(from, map);
        if (targetCoordinates != null) {
            /// тогда выполняем действие creature
            performAction(coordinates.pollLast(), map);

            return;
        }

        if (coordinates.size() - 1 <= speed) {
            ///  перемещаем creature на ближайшую позицию перед target
            coordinates.pollLast();  /// убирая при этом из очереди координаты target
            map.moveEntity(from, coordinates.pollLast(), map.getEntity(from));
        } else {
            ///  перемещаем creature ближе к target на максимальное количество шагов
            for (int step = 0; step < speed; step++) {
                coordinates.pollFirst();
            }
            map.moveEntity(from, coordinates.pollFirst(), map.getEntity(from));
        }
    }


    private Deque<Coordinates> bfsToTarget(Coordinates start, Map map) {
        /// Очередь для хранения текущих узлов и путей до них
        Queue<Deque<Coordinates>> queue = new LinkedList<>();
        Deque<Coordinates> startPath = new LinkedList<>();
        startPath.add(start);
        queue.add(startPath);

        Set<Coordinates> visited = new HashSet<>();
        visited.add(start);

        // to do --> replace form method coordinatesNear(Coordinates coordinates, Map map)
        ///Соседние направления для перемещения (вверх, вниз, влево, вправо)
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            /// Извлекаем путь из очереди
            Deque<Coordinates> path = queue.poll();
            Coordinates current = path.getLast();

            /// Если текущая сущность это target то возвращаем путь
            Entity entity = map.getEntity(current);
            if (entity != null && entity.getClass().getSimpleName().equals(target)) {
                return path;
            }

            /// Проходим по соседям текущего узла
            for (int i = 0; i < 4; i++) {
                int newX = current.getX() + dx[i];
                int newY = current.getY() + dy[i];
                Coordinates neighbor = new Coordinates(newX, newY);

                /// Проверяем что сосед в пределах карты, не был посещен, и является target или пустой клеткой
                if (map.isCordsInMapArea(neighbor) && !visited.contains(neighbor) && isTargetOrVoid(neighbor, map)) {
                    visited.add(neighbor);

                    /// Создаем новый путь с добавлением соседа
                    Deque<Coordinates> newPath = new LinkedList<>(path);
                    newPath.add(neighbor);

                    /// Добавляем новый путь в очередь
                    queue.add(newPath);
                }
            }
        }
        /// Если путь не найден, возвращаем пустую очередь
        return new LinkedList<>();
    }


    private Coordinates isTargetNear(Coordinates coordinates, Map map) {
        for (Coordinates c : cellsToCheck(coordinates)) {
            if (!map.isSquareEmpty(c) && map.isCordsInMapArea(coordinates)) {
                Entity entity = map.getEntity(c);
                if (entity.getClass().getSimpleName().equals(target)) {
                    return c;
                }
            }
        }
        return null;
    }

    private List<Coordinates> coordinatesNear(Coordinates coordinates, Map map) {
        List<Coordinates> coordinatesToCheck = new ArrayList<>();
        for (Coordinates c : cellsToCheck(coordinates)) {
            if (map.isCordsInMapArea(c)) {
                coordinatesToCheck.add(c);
            }
        }
        return coordinatesToCheck;
    }


    private Set<Coordinates> cellsToCheck(Coordinates coordinates) {
        return Set.of(
                new Coordinates(coordinates.getX() - 1, coordinates.getY()),
                new Coordinates(coordinates.getX() + 1, coordinates.getY()),
                new Coordinates(coordinates.getX(), coordinates.getY() - 1),
                new Coordinates(coordinates.getX(), coordinates.getY() + 1)
        );
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

//public Deque<Coordinates> findPathToTarget(Coordinates coordinates, Map map)  {
//    Deque<Coordinates> path = new ArrayDeque<>();
//
//    Deque<Coordinates> toVisit = new ArrayDeque<>
//            (coordinatesNear(coordinates, map));
//    Set<Coordinates> visited = new HashSet<>();
//    visited.add(coordinates);
//    while (!toVisit.isEmpty()) {
//        Coordinates visiting = toVisit.pollFirst();
//        path.add(visiting);
//
//        Coordinates targetCoordinates = isTargetNear(visiting, map);
//        if (targetCoordinates != null) {
//            path.add(visiting);
//            break;
//        }
//
//        for (Coordinates c : coordinatesNear(visiting, map)) {
//            if (!visited.contains(c)) {
//                visited.add(c);
//                toVisit.add(c);
//            }
//        }
//    }
//    return path;
//}