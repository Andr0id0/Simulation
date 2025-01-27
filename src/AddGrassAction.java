public class AddGrassAction {

    public void addGrassOnMap(Map map) {
        int minimumGrassOnMap = (map.getSizeX() * map.getSizeY()) / map.getGrassCount();
        if (minimumGrassOnMap > map.getGrassCount()) {
            int needToAddGrass = minimumGrassOnMap - map.getGrassCount();

            Grass newGras = new Grass();
            putEntityCountTimes(newGras, needToAddGrass, map);
            System.out.println("На карте выросла новая трава");
        }
    }

    public void putEntityCountTimes(Entity entity, int countEntity, Map map) {
        while (countEntity > 0) {
            int x = (int) (Math.random() * map.getSizeX());
            int y = (int) (Math.random() * map.getSizeY());
            if (!map.containsEntity(new Coordinates(x, y))) {
                map.putEntity(new Coordinates(x, y), entity);
                countEntity--;
            }
        }
    }


}
