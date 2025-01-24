package actions;

import entitys.Entity;

public class PutEntityOnMapAction extends Action {

    public void put(int length, int height, Entity entity) {
        map.put(new int[]{length, height}, entity);
    }


}
