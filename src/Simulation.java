public class Simulation {


    Map map;
    int moveCounter;
    /// to do fix architecture
    InitMapAction initMapAction = new InitMapAction();
    InitEntityAction initEntityAction = new InitEntityAction();
    MapConsoleRenderAction mapConsoleRenderAction = new MapConsoleRenderAction();
    MoveEntityAction moveEntityAction = new MoveEntityAction();
    AddGrassAction addGrassAction = new AddGrassAction();



    Action<?>[] initActions = new Action[]{new InitMapAction(), new InitEntityAction(), new MapConsoleRenderAction()};

    Action<?>[] turnActions = new Action[]{new MoveEntityAction(), new MapConsoleRenderAction()};


    public void initSimulation() {
        map = initMapAction.doAction();
        initEntityAction.doAction(map);
        mapConsoleRenderAction.doAction(map);
        System.out.println("Инициализируем симуляцию ...");
    }

    public void nextTurn() {
//        addGrassAction.addGrassOnMap(map); to fix
        System.out.println("Совершаем ход");
        moveEntityAction.doAction(map);
        mapConsoleRenderAction.doAction(map);
    }

    public void startSimulation(int moveCounter) {
        while (moveCounter > 0) {
            nextTurn();
            moveCounter--;
        }
    }



    public static void main(String[] args) {

    Simulation simulation = new Simulation();
    simulation.initSimulation();
    simulation.nextTurn();
    simulation.startSimulation(5);


    }

}
