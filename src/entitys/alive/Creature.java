package entitys.alive;

import entitys.Entity;

public abstract class Creature extends Entity {

    int speed;
    int health;

    public abstract void makeMove();



}
