public class Predator extends Creature {

    int damage;

    String predatorTarget = "Herbivore";

    @Override
    void performAction(Coordinates coordinates, Map map) {
        attack(coordinates, map);
    }

    @Override
    boolean isTargetOrVoid(Coordinates coordinates, Map map) {
        return map.isHerbivoreOrVoid(coordinates);
    }

    public void attack(Coordinates coordinates, Map map) {
        Creature creature = (Creature) map.getEntity(coordinates);
        int healthAfterAttack = creature.getHealth() - damage;
        if (healthAfterAttack <= 0) {
            map.deleteEntity(coordinates);
            System.out.println("Убили травоядного на координатах x:" + coordinates.getX() + " y:" + coordinates.getY());
            return;
        }
        creature.setHealth(healthAfterAttack);
        map.putEntity(coordinates, creature);
        System.out.println("Ранили травоядного на координатах x:" + coordinates.getX() + " y:" + coordinates.getY());

    }

    public Predator(int speed, int health, int damage) {
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.target = predatorTarget;
    }

}
