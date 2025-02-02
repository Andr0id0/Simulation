package actions;

import utils.GamePlace;
import utils.MapConsoleRenderer;


public class MapConsoleRenderAction extends Action {

    @Override
    public void perform(GamePlace gamePlace) {
        render(gamePlace);
    }

    private void render(GamePlace gamePlace) {
        MapConsoleRenderer consoleRenderer = new MapConsoleRenderer(gamePlace);
        consoleRenderer.render();
    }

}
