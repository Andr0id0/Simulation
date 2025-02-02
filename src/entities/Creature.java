package entities;

import utils.Coordinates;
import utils.GamePlace;
import java.util.*;


public abstract class Creature extends Entity {

    private int speed;
    private int health;

    ///  цель поиска для creature
    public String target;

    public Creature(int health, int speed) {
        this.health = health;
        this.speed = speed;
    }

    /// метод, выполняющий в дочернем классе, действие creature
    abstract void entityAction(Coordinates coordinates, GamePlace gamePlace);
    ///  метод который будет переопределен только у Herbivore           это нормальная практика так делать????
    abstract void performActionTwo(Coordinates coordinates, GamePlace gamePlace);

    /// метод, определяющий в дочернем классе, является ли клетка target или void
    abstract boolean isTargetOrVoid(Coordinates coordinates, GamePlace gamePlace);

    public boolean makeMove(Coordinates from, GamePlace gamePlace) {
        Creature creature = (Creature) gamePlace.getEntity(from);
        int speed = creature.getSpeed();
        Deque<Coordinates> coordinates = bfsToTarget(from, gamePlace);

        /// Если путь пустой не движемся
        if (coordinates.isEmpty()) {
            return false;
        }

        /// если creature может дойти до target за один ход или creature уже возле target
        if (coordinates.size() - 1 <= speed) {
             /// выполняем performAction убирая при этом из очереди координаты target
            entityAction(coordinates.pollLast(), gamePlace);

            ///  перемещаем creature на ближайшую в пути позицию перед target
//            gamePlace.moveEntity(from, coordinates.peekLast(), gamePlace.getEntity(from));
            moveCreature(from, coordinates.peekLast(), gamePlace.getCreature(from), gamePlace);

            ///  выполняем performActionTow которое будет использовать только Herbivore
            performActionTwo(coordinates.peekLast(), gamePlace);

        } else {
            ///  перемещаем creature ближе к target на максимальное количество шагов
            for (int step = 0; step < speed; step++) {
                coordinates.pollFirst();
            }
            moveCreature(from, coordinates.pollFirst(), gamePlace.getCreature(from), gamePlace);
//            gamePlace.moveEntity(from, coordinates.pollFirst(), gamePlace.getEntity(from));
        }
        return true;
    }

    private Deque<Coordinates> bfsToTarget(Coordinates start, GamePlace gamePlace) {
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
            Entity entity = gamePlace.getEntity(current);
            if (entity != null && entity.getClass().getSimpleName().equals(target)) {
                return path;
            }

            /// Проходим по соседям текущего узла
            for (Coordinates neighbor : cellsToCheck(current)) {

                /// Проверяем что сосед в пределах карты, не был посещен, и является target или пустой клеткой
                if (gamePlace.isCordsInMapArea(neighbor) && !visited.contains(neighbor) && isTargetOrVoid(neighbor, gamePlace)) {
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

    private void moveCreature(Coordinates from, Coordinates to, Creature creature, GamePlace gamePlace) {
        gamePlace.deleteEntity(from);
        gamePlace.putEntity(to, creature);
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

}
