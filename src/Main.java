public class Main {

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.initSimulation();

        ///  для бесконечной симуляции (в теории)
        simulation.startInfinitySimulation();

//        /// симуляция конкретного числа циклов
//        simulation.startCountSimulation(10);

//        /// для симуляции одного хода
//        simulation.nextTurn();

    }

}
