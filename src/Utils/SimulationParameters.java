package Utils;

public class SimulationParameters {

    private int xSize = 25;
    private int ySize = 60;

    private final double grassRatio = 0.11d;

    private final int maxGrassLimit = (int) (xSize * ySize * grassRatio);
    private final int minGrassLimit = maxGrassLimit / 2;

    private final int TreeCount = 10;
    private final int RockCount = 30;
    private final int GrassCount = 30;
    private final int HerbivoreCount = 40;
    private final int PredatorCount = 20;

    private final int predatorHp = 1;
    private final int predatorSpeed = 2;
    private final int predatorDamage = 1;
    private final int herbivoreHp = 100;
    private final int herbivoreSpeed = 3;
    private final int herbivoreHealAfterEat = 100;



    public int getGrassCount() {
        return GrassCount;
    }

    public int getHerbivoreHealAfterEat() {
        return herbivoreHealAfterEat;
    }

    public int getHerbivoreCount() {
        return HerbivoreCount;
    }

    public int getMapSizeX() {
        return xSize;
    }

    public int getMapSizeY() {
        return ySize;
    }

    public int getMaxGrassLimit() {
        return maxGrassLimit;
    }

    public int getPredatorCount() {
        return PredatorCount;
    }

    public int getRockCount() {
        return RockCount;
    }

    public int getTreeCount() {
        return TreeCount;
    }

    public int getHerbivoreSpeed() {
        return herbivoreSpeed;
    }

    public int getHerbivoreHp() {
        return herbivoreHp;
    }

    public int getPredatorDamage() {
        return predatorDamage;
    }

    public int getPredatorHp() {
        return predatorHp;
    }

    public int getPredatorSpeed() {
        return predatorSpeed;
    }

    public int getMiniGrassLimit() {
        return minGrassLimit;
    }

}
