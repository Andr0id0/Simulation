package actions;

import entitys.alive.Creature;

public class MoveCreatureAction {

    public void moveCreature(Creature creature) {
        creature.makeMove();
    }

}
