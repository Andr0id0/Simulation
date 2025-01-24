package entitys.alive;

public class Herbivore extends Creature {

    public final String VIEW = "1";

    @Override
    public void makeMove() {

    }

    public void eat() {

    }

    public Herbivore(int seed, int health) {
        this.speed = seed;
        this.health = health;
    }

    public String getVIEW() {
        return VIEW;
    }
}
