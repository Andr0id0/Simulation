package entitys.alive;

public class Predator extends Creature {
    public final String VIEW = "2";

    int damage;

    @Override
    public void makeMove() {

    }

    public void attack() {

    }

    public Predator(int speed, int health, int damage) {
        this.speed = speed;
        this.health = health;
        this.damage = damage;
    }

    public String getVIEW() {
        return VIEW;
    }
}
