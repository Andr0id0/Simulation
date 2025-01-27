import java.util.*;

public abstract class Creature extends Entity {

    int speed;
    int health;

    ///  цель поиска для creature
    public String target;

    /// метод, выполняющий в дочернем классе, действие creature
    abstract void performAction(Coordinates coordinates, Map map);

    /// метод, определяющий в дочернем классе, является ли клетка target или void
    abstract boolean isTargetOrVoid(Coordinates coordinates, Map map);

    public void makeMove(Coordinates from, Map map) {
        Creature creature = (Creature) map.getEntity(from);
        int speed = creature.getSpeed();
        Deque<Coordinates> coordinates = bfsToTarget(from, map);

        /// Если путь пустой не движемся
        if (coordinates.isEmpty()) {
            return;
        }

        /// если creature может дойти до target за один ход или creature уже возле target
        if (coordinates.size() - 1 <= speed) {
             /// выполняем performAction убирая при этом из очереди координаты target
            performAction(coordinates.pollLast(), map);
            ///  перемещаем creature на ближайшую в пути позицию перед target
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
            for (Coordinates neighbor : cellsToCheck(current)) {

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

    /// возвращает все соседние клетки
    private Set<Coordinates> cellsToCheck(Coordinates coordinates) {
        return Set.of(
                new Coordinates(coordinates.x() - 1, coordinates.y()),
                new Coordinates(coordinates.x() + 1, coordinates.y()),
                new Coordinates(coordinates.x(), coordinates.y() - 1),
                new Coordinates(coordinates.x(), coordinates.y() + 1)
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
