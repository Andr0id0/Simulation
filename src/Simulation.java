import Actions.*;
import Utils.Map;


public class Simulation {

    Map map;
    boolean didEntityMove;

    InitMapAction initMapAction = new InitMapAction();
    InitEntityAction initEntityAction = new InitEntityAction();
    MapConsoleRenderAction mapConsoleRenderAction = new MapConsoleRenderAction();
    MoveEntityAction moveEntityAction = new MoveEntityAction();
    AddGrassAction addGrassAction = new AddGrassAction();

    ///
    Action[] initActions = new Action[]{new InitMapAction(), new InitEntityAction(), new MapConsoleRenderAction()};
    Action[] turnActions = new Action[]{new MoveEntityAction(),new AddGrassAction(), new MapConsoleRenderAction()};


    public void initSimulation() {
        System.out.println("Инициализируем симуляцию ...");
        map = initMapAction.init();
        initEntityAction.initEntity(map);
        mapConsoleRenderAction.render(map);
    }

    public void nextTurn() {
        System.out.println("Совершаем ход");
        addGrassAction.addGrass(map);
        didEntityMove = moveEntityAction.moveEntity(map);
        mapConsoleRenderAction.render(map);
    }

    public void startCountSimulation(int moveCounter) {
        System.out.println("Начинаем симуляцию");
        while (moveCounter > 0) {
            nextTurn();
            if (!didEntityMove) {
                System.out.println("Существам больше некуда двигаться");
                break;
            }
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
            if (!didEntityMove) {
                System.out.println("Существам больше некуда двигаться");
                break;
            }
            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
