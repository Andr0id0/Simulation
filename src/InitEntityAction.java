public class InitEntityAction extends Action<Void> {

    @Override
    public Void doAction(Map map) {
        initEntity(map);
        return null;
    }


    @Override
    public Void doAction() {
        return null;
    }

    private Map map;

    private int mapXSize;
    private int mapYSIze;

    public final double grassRefresh = 0.2d;

    private final int TreeCount = 5;
    private final int RockCount = 5;
    private final int GrassCount = 5;
    private final int HerbivoreCount = 5;
    private final int PredatorCount = 5;


    private void initEntity(Map map) {
        this.map = map;
        mapXSize = map.getSizeX();
        mapYSIze = map.getSizeY();
        putEntityCountTimes(new Rock(), RockCount);
        putEntityCountTimes(new Tree(), TreeCount);
        putEntityCountTimes(new Grass(), GrassCount);
        putEntityCountTimes(new Herbivore(3 ,6), HerbivoreCount);
        putEntityCountTimes(new Predator(3, 1, 1), PredatorCount);
    }

    public void putEntityCountTimes(Entity entity, int countEntity) {
        while (countEntity > 0) {
            int x = (int) (Math.random() * mapXSize);
            int y = (int) (Math.random() * mapYSIze);
            if (!map.containsEntity(new Coordinates(x, y))) {
                map.putEntity(new Coordinates(x, y), entity);
                countEntity--;
            }
        }
    }

}
