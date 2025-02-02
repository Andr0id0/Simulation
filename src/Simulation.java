import actions.*;
import utils.GamePlace;


public class Simulation {

    private final GamePlace gamePlace = new GamePlace();

    private final Action[] initActions = new Action[]{new InitEntityAction(), new MapConsoleRenderAction()};
    private final Action[] turnActions = new Action[]{new AddGrassAction(), new MoveEntityAction(), new MapConsoleRenderAction()};

    public void initSimulation()  {
        for (Action action : initActions) {
            action.perform(gamePlace);
        }
    }

    public void nextTurn() {
        for (Action action : turnActions) {
            action.perform(gamePlace);
        }
    }

    public void startCountSimulation(int moveCounter) {
        while (moveCounter > 0) {
            nextTurn();
            moveCounter--;
            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void startInfinitySimulation() {
        while (true) {
            nextTurn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
