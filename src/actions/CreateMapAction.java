package actions;

import entitys.lifeless.Air;

public class CreateMapAction extends Action {

    public void createClearMap(int length, int height) {
        Air air = new Air();
        for (int l = 0; l < length; l++) {
            for (int h = 0; h < height; h++) {
                map.put(new int[]{l, h}, air);
            }
        }
    }
}
