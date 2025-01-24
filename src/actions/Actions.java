package actions;

import entitys.Entity;
import entitys.alive.Creature;
import entitys.alive.Herbivore;
import entitys.alive.Predator;
import entitys.lifeless.Air;
import entitys.lifeless.Grass;
import entitys.lifeless.Rock;
import entitys.lifeless.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Actions {

    private HashMap<ArrayList<Integer>, Entity> map;
    static int length;
    static int height;

    public HashMap<ArrayList<Integer>, Entity> getMap() {
        return map;
    }

    public HashMap<ArrayList<Integer>, Entity> createClearMap(int[] size) {
        length = size[0];
        height = size[1];
        Air air = new Air();
        map = new HashMap<>();
        for (int l = 0; l < length; l++) {
            for (int h = 0; h < height; h++) {
                ArrayList<Integer> cords = new ArrayList<>();
                cords.add(l);
                cords.add(h);
                map.put(cords, air);
            }
        }
        return map;
    }

    public  List<List<Integer>> findAirCords() {
        List<List<Integer>> airCords = new ArrayList<>();
         for (Map.Entry<ArrayList<Integer>, Entity> entry : map.entrySet()) {
             if (entry.getValue().getClass().equals(Air.class)) {
                 ArrayList<Integer> cords = new ArrayList<>();
                 cords.add(entry.getKey().get(0));
                 cords.add(entry.getKey().get(1));
                 airCords.add(cords);
             }
         }
         return airCords;
    }

    public  int choseTreeCount() {
        return length * height / 20;
    }

    public  int choseRockCount() {
        return length * height / 20;
    }

    public  int choseGrassCount() {
        return length * height / 5;
    }

    public  int choseHerbivoreCount() {
        return length * height / 20;
    }

    public  int chosePredatorCount() {
        return length * height / 20;
    }

    public  void putTrees() {
        int treeCount = choseTreeCount();
        Tree tree = new Tree();
        fillMap(treeCount, tree);
    }

    public  void putGrass() {
        int grassCount = choseGrassCount();
        Grass grass = new Grass();
        fillMap(grassCount, grass);
    }

    public  void putRocks() {
        int rockCount = choseRockCount();
        Rock rock = new Rock();
        fillMap(rockCount, rock);
    }

    public  void putHerbivore() {
        int herbivoreCount = choseHerbivoreCount();
        Herbivore herbivore = new Herbivore(1, 1);
        fillMap(herbivoreCount, herbivore);
    }

    public  void putPredator() {
        int predatorCount = chosePredatorCount();
        Predator predator = new Predator(1, 1, 1);
        fillMap(predatorCount, predator);
    }


    private  void fillMap(int numObjects, Entity entity) {
        List<List<Integer>> airCords = findAirCords();
        if (airCords.isEmpty()) {
            System.out.println("Места на карте нету");
        }
        for (int c = 0; c < numObjects; c++) {
            int index = (int) (Math.random() * airCords.size());
            List<Integer> cords = airCords.get(index);
            ArrayList<Integer> key = new ArrayList<>();
            key.add(cords.get(0));
            key.add(cords.get(1));
            map.put(key, entity);
            airCords.remove(index);
        }
    }

    public  void printMap() {
        HashMap<String, String> view = new HashMap<>();
        view.put("entitys.lifeless.Air", "--");
        view.put("entitys.lifeless.Grass", "..");
        view.put("entitys.lifeless.Rock", "0");
        view.put("entitys.lifeless.Tree", "||");
        view.put("entitys.alive.Herbivore", "|1");
        view.put("entitys.alive.Predator", "|2");

        for (int h = 0; h < height; h++) {
            System.out.println(" ");
            for (int l = 0; l < length; l++) {
                List<Integer> cords = new ArrayList<>();
                cords.add(h);
                cords.add(l);
                System.out.print(view.get(map.get(cords).getClass().getName()));
            }
        }
    }

    public  void putAllEntity() {
        putTrees();
        putRocks();
        putGrass();
        putHerbivore();
        putPredator();
    }


    public  void moveCreature(Creature creature) {
        creature.makeMove();
    }


    public  int[] mapSize() {
        return new int[]{10, 10};
    }

}
