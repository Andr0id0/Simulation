package Actions;

import Utils.Map;
import Utils.MapConsoleRenderer;


public class MapConsoleRenderAction extends Action {

    public void render(Map map) {
        MapConsoleRenderer consoleRenderer = new MapConsoleRenderer(map);
        consoleRenderer.render();
    }

}
